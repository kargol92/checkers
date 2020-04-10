package warcaby;

public class Plansza {
    final static int szer = 8;
    final Pole pole[][];
    int WspX;
    int WspY;
    static int obecneX;
    static int obecneY;
    static int poprzednieX;
    static int poprzednieY;
    static int przeciwnikX;
    static int przeciwnikY;
    static boolean byloBicie = false;
    static boolean poprzednioZaznaczonyPionek = false;
    static boolean ustawionePionki = false;
    static boolean mozliweCofniecie = false;
    static boolean cofniecie = false;
    Plansza() {
        WspX = 0;
        WspY = 0;
        pole = new Pole[8][8];
        for( int y=0; y<8; y++ ) {
            for( int x=0; x<8; x++ ) {
                pole[y][x] = new Pole();
                if( x%2 == y%2 ) pole[y][x].setDozwolone(false);
                else pole[y][x].setDozwolone(true);
            }
        }
    }
    void wyczysc() {
        for( int y=0; y<8; y++ ) {
            for( int x=0; x<8; x++ ) {
                pole[y][x] = new Pole();
                if( x%2 == y%2 ) pole[y][x].setDozwolone(false);
                else pole[y][x].setDozwolone(true);
            }
        }
    }
    void ustawPionki() {
        Gracz.ruch = 1;
        for( int y=0; y<8; y++ ) {
            for( int x=0; x<8; x++ ) {
                pole[y][x].wyczysc();
                if( x%2 == y%2 ) pole[y][x].setStan('#');
                else {
                    if( y < 3 ) pole[y][x].setStan('x');
                    else if( y > 4 ) pole[y][x].setStan('o');
                    else pole[y][x].setStan('.');
                }
            }
        }
    }
    void wyswietl() {
        System.out.println("Stan pól\t\tZaznaczone pole\t\tPionek");
        for( int y=0; y<8; y++ ) {
            for( int x=0; x<8; x++) {
                System.out.print(pole[y][x].getStan() + " ");
            }
            System.out.print("\t");
            for( int x=0; x<8; x++) {
                if( pole[y][x].getZaznaczonePole() == true ) { System.out.print("@ "); }
                else { System.out.print(". "); }
            }
            System.out.print("\t");
            for( int x=0; x<8; x++) {
                if( pole[y][x].getZaznaczonyPionek() == true ) { System.out.print("@ "); }
                if( pole[y][x].getZaznaczonaDamka() == true ) { System.out.print("$ "); }
                else if( pole[y][x].getProponowane() == true ) { System.out.print("+ "); }
                else if( pole[y][x].getProponowaneBicie() == true ) { System.out.print("* "); }
                else { System.out.print(". "); }
            }
            System.out.print("\t");
            for( int x=0; x<8; x++) {
//                else if( pole[y][x].getProponowane() == true ) { System.out.print("+ "); }
                if( pole[y][x].getProponowaneBicie() == true ) { System.out.print("* "); }
                else { System.out.print(". "); }
            }
            System.out.println();
        }
        System.out.print("Gracz 1 (czarne): "+Gracz.lPionkow1+" pionkow");
        if( Gracz.ruch == 1) System.out.print("\tRUCH");
        System.out.println();
        System.out.print("Gracz 2 (biale):  "+Gracz.lPionkow2+" pionkow");
        if( Gracz.ruch == 2) System.out.print("\tRUCH");
        System.out.println();
    }
    boolean sprawdzZaznaczeniePionka() {
        for( int y=0; y<8; y++ ) {
            for( int x=0; x<8; x++) {
                if( pole[y][x].getZaznaczonyPionek() ) return true;
            }
        }
        return false;
    }
    void sprawdzCzyDamka() {
        for( int x=0; x<8; x++) {
            if( pole[7][x].getStan() =='x' ) pole[7][x].setStan('X');
            if( pole[0][x].getStan() =='o' ) pole[0][x].setStan('O');
        }
    }
    boolean zaznaczPionek(int myszX, int myszY) {
        for( int y=0; y<8; y++ ) {
            for( int x=0; x<8; x++) {
                if( ( myszX > (WspX+Pole.szer*x) ) && ( myszX < (WspX+Pole.szer*x+Pole.szer) ) ) {
                    if( ( myszY > (WspY+Pole.szer*y) ) && ( myszY < (WspY+Pole.szer*y+Pole.szer) ) ) {
                        pole[y][x].setZaznaczonePole(true);
                        if( Gracz.ruch == 1 && pole[y][x].getStan() == 'x' ) {
                            pole[y][x].setZaznaczonyPionek(true);
                            return true;
                        }
                        if( Gracz.ruch == 1 && pole[y][x].getStan() == 'X' ) {
                            pole[y][x].setZaznaczonaDamka(true);
                            return true;
                        }
                        else if( Gracz.ruch == 2 && pole[y][x].getStan() == 'o' ) {
                            pole[y][x].setZaznaczonyPionek(true);
                            return true;
                        }
                        else if( Gracz.ruch == 2 && pole[y][x].getStan() == 'O' ) {
                            pole[y][x].setZaznaczonaDamka(true);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    void zaproponujRuch() {
        char przeciwnik = '.';
        char przeciwnikDamka = '.';
        if( Gracz.ruch == 1 ) { przeciwnik = 'o'; przeciwnikDamka = 'O'; }
        else if( Gracz.ruch == 2 ) { przeciwnik = 'x'; przeciwnikDamka = 'X'; }
        for( int y=0; y<8; y++ ) {
            for( int x=0; x<8; x++) {
                if( pole[y][x].getZaznaczonyPionek() == true ) {
                    poprzednieY = y;
                    poprzednieX = x;
                    if( Gracz.ruch == 1 ) {
                        if( (y+1) < 8 ) {
                            if( (x-1) >= 0 ) {
                                if( pole[y+1][x-1].getStan() == '.' ) pole[y+1][x-1].setProponowane(true);
                            }
                            if( (x+1) < 8 ) {
                                if( pole[y+1][x+1].getStan() == '.' ) pole[y+1][x+1].setProponowane(true);
                            }
                        }
                    }
                    else if( Gracz.ruch == 2 ) {
                        if ( (y-1) >= 0 ) {
                            if ( (x-1) >= 0 ) {
                                if( pole[y-1][x-1].getStan() == '.' ) pole[y-1][x-1].setProponowane(true);
                            }
                            if ( (x+1) < 8 ) {
                                if( pole[y-1][x+1].getStan() == '.' ) pole[y-1][x+1].setProponowane(true);
                            }
                        }
                    }
                    if( (y+2) < 8 ) {
                        if( (x-2) >= 0 ) {
                            if( pole[y+2][x-2].getStan() == '.' ) {
                                if( pole[y+1][x-1].getStan() == przeciwnik ) {
                                    przeciwnikY = y+1;
                                    przeciwnikX = x-1;
                                    pole[y+2][x-2].setProponowane(true);
                                    pole[y+2][x-2].setProponowaneBicie(true);
                                }
                            }
                        }
                        if( (x+2) < 8 ) {
                            if( pole[y+2][x+2].getStan() == '.' ) {
                                if( pole[y+1][x+1].getStan() == przeciwnik ) {
                                    przeciwnikY = y+1;
                                    przeciwnikX = x+1;
                                    pole[y+2][x+2].setProponowane(true);
                                    pole[y+2][x+2].setProponowaneBicie(true);
                                }
                            }
                        }
                    }
                    if( (y-2) >= 0 ) {
                        if( (x-2) >= 0 ) {
                            if( pole[y-2][x-2].getStan() == '.' ) {
                                if( pole[y-1][x-1].getStan() == przeciwnik ) {
                                    przeciwnikY = y-1;
                                    przeciwnikX = x-1;
                                    pole[y-2][x-2].setProponowane(true);
                                    pole[y-2][x-2].setProponowaneBicie(true);
                                }
                            }
                        }
                        if( (x+2) < 8 ) {
                            if( pole[y-2][x+2].getStan() == '.' ) {
                                if( pole[y-1][x+1].getStan() == przeciwnik ) {
                                    przeciwnikY = y-1;
                                    przeciwnikX = x+1;
                                    pole[y-2][x+2].setProponowane(true);
                                    pole[y-2][x+2].setProponowaneBicie(true);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    void zaproponujRuchDamka() {
        char pionek = '.';
        char damka = '.';
        char przeciwnik = '.';
        char przeciwnikDamka = '.';
        if( Gracz.ruch == 1 ) { pionek = 'x'; damka = 'X'; przeciwnik = 'o'; przeciwnikDamka = 'O'; }
        else if( Gracz.ruch == 2 ) { pionek = 'o'; damka = 'O'; przeciwnik = 'x'; przeciwnikDamka = 'X'; }
        for( int y=0; y<8; y++ ) {
            for( int x=0; x<8; x++ ) {
                if( pole[y][x].getZaznaczonaDamka() == true ) {
                    poprzednieY = y;
                    poprzednieX = x;
                    if( (y+1) < 8 ) {
                        if( (x-1) >= 0 ) {
                            if( pole[y+1][x-1].getStan() == '.' ) pole[y+1][x-1].setProponowane(true);
                        }
                        if( (x+1) < 8 ) {
                            if( pole[y+1][x+1].getStan() == '.' ) pole[y+1][x+1].setProponowane(true);
                        }
                    }
                    if ( (y-1) >= 0 ) {
                        if ( (x-1) >= 0 ) {
                            if( pole[y-1][x-1].getStan() == '.' ) pole[y-1][x-1].setProponowane(true);
                        }
                        if ( (x+1) < 8 ) {
                            if( pole[y-1][x+1].getStan() == '.' ) pole[y-1][x+1].setProponowane(true);
                        }
                    }
                    if( (y+2) < 8 ) {
                        if( (x-2) >= 0 ) {
                            if( pole[y+2][x-2].getStan() == '.' ) {
                                if( pole[y+1][x-1].getStan() == przeciwnik || pole[y+1][x-1].getStan() == przeciwnikDamka ) {
                                    przeciwnikY = y+1;
                                    przeciwnikX = x-1;
                                    pole[y+2][x-2].setProponowane(true);
                                    pole[y+2][x-2].setProponowaneBicie(true);
                                }
                            }
                        }
                        if( (x+2) < 8 ) {
                            if( pole[y+2][x+2].getStan() == '.' ) {
                                if( pole[y+1][x+1].getStan() == przeciwnik || pole[y+1][x+1].getStan() == przeciwnikDamka ) {
                                    przeciwnikY = y+1;
                                    przeciwnikX = x+1;
                                    pole[y+2][x+2].setProponowane(true);
                                    pole[y+2][x+2].setProponowaneBicie(true);
                                }
                            }
                        }
                    }
                    if( (y-2) >= 0 ) {
                        if( (x-2) >= 0 ) {
                            if( pole[y-2][x-2].getStan() == '.' ) {
                                if( pole[y-1][x-1].getStan() == przeciwnik || pole[y-1][x-1].getStan() == przeciwnikDamka ) {
                                    przeciwnikY = y-1;
                                    przeciwnikX = x-1;
                                    pole[y-2][x-2].setProponowane(true);
                                    pole[y-2][x-2].setProponowaneBicie(true);
                                }
                            }
                        }
                        if( (x+2) < 8 ) {
                            if( pole[y-2][x+2].getStan() == '.' ) {
                                if( pole[y-1][x+1].getStan() == przeciwnik || pole[y-1][x+1].getStan() == przeciwnikDamka ) {
                                    przeciwnikY = y-1;
                                    przeciwnikX = x+1;
                                    pole[y-2][x+2].setProponowane(true);
                                    pole[y-2][x+2].setProponowaneBicie(true);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    void zaproponujRuchDamka2() {
        char pionek = '.';
        char damka = '.';
        char przeciwnik = '.';
        char przeciwnikDamka = '.';
        if( Gracz.ruch == 1 ) { pionek = 'x'; damka = 'X'; przeciwnik = 'o'; przeciwnikDamka = 'O'; }
        else if( Gracz.ruch == 2 ) { pionek = 'o'; damka = 'O'; przeciwnik = 'x'; przeciwnikDamka = 'X'; }
        for( int y=0; y<8; y++ ) {
            for( int x=0; x<8; x++ ) {
                if( pole[y][x].getZaznaczonaDamka() == true ) {
                    poprzednieY = y;
                    poprzednieX = x;
                    for( int i=1; i<8; i++ ) {
                        if( y+i < 8 ) {
                            if( x+i < 8 ) {
                                if( pole[y+i][x+i].getStan() == '.' ) pole[y+i][x+i].setProponowane(true);
                                else if( pole[y+i][x+i].getStan() == pionek ) break;
                                else if( pole[y+i][x+i].getStan() == damka ) break;
                                else if( pole[y+i][x+i].getStan() == przeciwnik || pole[y+i][x+i].getStan() == przeciwnikDamka ) {
                                    if( y+i+1 < 8 ) {
                                        if( x+i+1 < 8 ) {
                                            if( pole[y+i+1][x+i+1].getStan() != '.' ) break;
                                            else if( pole[y+i+1][x+i+1].getStan() == '.' ) {
                                                pole[y+i+1][x+i+1].setProponowane(true);
                                                pole[y+i+1][x+i+1].setProponowaneBicie(true);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    for( int i=1; i<8; i++ ) {
                        if( y+i < 8 ) {
                            if( x-i >= 0 ) {
                                if( pole[y+i][x-i].getStan() == '.' ) pole[y+i][x-i].setProponowane(true);
                                else if( pole[y+i][x-i].getStan() == pionek ) break;
                                else if( pole[y+i][x-i].getStan() == damka ) break;
                                else if( pole[y+i][x-i].getStan() == przeciwnik || pole[y+i][x-i].getStan() == przeciwnikDamka ) {
                                    if( y+i+1 < 8 ) {
                                        if( x-i-1 >= 0 ) {
                                            if( pole[y+i+1][x-i-1].getStan() != '.' ) break;
                                            else if( pole[y+i+1][x-i-1].getStan() == '.' ) {
                                                pole[y+i+1][x-i-1].setProponowane(true);
                                                pole[y+i+1][x-i-1].setProponowaneBicie(true);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    for( int i=1; i<8; i++ ) {
                        if( y-i >= 0 ) {
                            if( x+i < 8 ) {
                                if( pole[y-i][x+i].getStan() == '.' ) pole[y-i][x+i].setProponowane(true);
                                else if( pole[y-i][x+i].getStan() == pionek ) break;
                                else if( pole[y-i][x+i].getStan() == damka ) break;
                                else if( pole[y-i][x+i].getStan() == przeciwnik || pole[y-i][x+i].getStan() == przeciwnikDamka ) {
                                    if( y-i-1 >= 0 ) {
                                        if( x+i+1 < 8 ) {
                                            if( pole[y-i-1][x+i+1].getStan() != '.' ) break;
                                            else if( pole[y-i-1][x+i+1].getStan() == '.' ) {
                                                pole[y-i-1][x+i+1].setProponowane(true);
                                                pole[y-i-1][x+i+1].setProponowaneBicie(true);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    for( int i=1; i<8; i++ ) {
                        if( y-i >= 0 ) {
                            if( x-i >= 0 ) {
                                if( pole[y-i][x-i].getStan() == '.' ) pole[y-i][x-i].setProponowane(true);
                                else if( pole[y-i][x-i].getStan() == pionek ) break;
                                else if( pole[y-i][x-i].getStan() == damka ) break;
                                else if( pole[y-i][x-i].getStan() == przeciwnik || pole[y-i][x-i].getStan() == przeciwnikDamka ) {
                                    if( y-i-1 >= 0 ) {
                                        if( x-i-1 >= 0 ) {
                                            if( pole[y-i-1][x-i-1].getStan() != '.' ) break;
                                            else if( pole[y-i-1][x-i-1].getStan() == '.' ) {
                                                pole[y-i-1][x-i-1].setProponowane(true);
                                                pole[y-i-1][x-i-1].setProponowaneBicie(true);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    boolean wykonajRuch(int myszX, int myszY) {
        for( int y=0; y<8; y++ ) {
            for( int x=0; x<8; x++) {
                if( ( myszY > (WspY+Pole.szer*y) ) && ( myszY < (WspY+Pole.szer*y+Pole.szer) ) ) {
                    if( ( myszX > (WspX+Pole.szer*x) ) && ( myszX < (WspX+Pole.szer*x+Pole.szer) ) ) {
                        if( pole[y][x].getProponowane() == true && pole[y][x].getProponowaneBicie() == true ) {
                            obecneX = x;
                            obecneY = y;
                            if( Gracz.ruch == 1 ) Gracz.lPionkow2--;
                            else if( Gracz.ruch == 2 ) Gracz.lPionkow1--;
                            if( pole[poprzednieY][poprzednieX].getStan() == 'X') pole[y][x].setStan('X');
                            else if( pole[poprzednieY][poprzednieX].getStan() == 'x') pole[y][x].setStan('x');
                            else if( pole[poprzednieY][poprzednieX].getStan() == 'o') pole[y][x].setStan('o');
                            else if( pole[poprzednieY][poprzednieX].getStan() == 'O') pole[y][x].setStan('O');
                            pole[poprzednieY][poprzednieX].wyczysc();
                            pole[przeciwnikY][przeciwnikX].wyczysc();
                            byloBicie = true;
                            System.out.println("przeciwnik: Y="+przeciwnikY+" "+przeciwnikX);
                            mozliweCofniecie = true;
                            return true;
                        }
                        else if( pole[y][x].getProponowane() == true ) {
                            obecneX = x;
                            obecneY = y;
                            if( pole[poprzednieY][poprzednieX].getStan() == 'X') pole[y][x].setStan('X');
                            else if( pole[poprzednieY][poprzednieX].getStan() == 'x') pole[y][x].setStan('x');
                            else if( pole[poprzednieY][poprzednieX].getStan() == 'o') pole[y][x].setStan('o');
                            else if( pole[poprzednieY][poprzednieX].getStan() == 'O') pole[y][x].setStan('O');
                            pole[poprzednieY][poprzednieX].wyczysc();
                            mozliweCofniecie = true;
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    void cofnijRuch() {
        if( Gracz.ruch == 1 ) {
            Gracz.ruch = 2;
            pole[poprzednieY][poprzednieX].setStan('o');
            if( byloBicie == true ) {
                pole[przeciwnikY][przeciwnikX].setStan('x');
                Gracz.lPionkow1++;
            }
        }
        else if( Gracz.ruch == 2 ) {
            Gracz.ruch = 1;
            pole[poprzednieY][poprzednieX].setStan('x');
            if( byloBicie == true ) {
                pole[przeciwnikY][przeciwnikX].setStan('o');
                Gracz.lPionkow2++;
            }
        }
        pole[obecneY][obecneX].wyczysc();
        mozliweCofniecie = false;
    }
    void wyczyscProponowaneRuchy() {
        for( int y=0; y<8; y++ ) {
            for( int x=0; x<8; x++) {
                pole[y][x].setProponowane(false);
                pole[y][x].setProponowaneBicie(false);
            }
        }
    }
    void odznaczPionek() {
        for( int y=0; y<8; y++ ) {
            for( int x=0; x<8; x++) {
                pole[y][x].setZaznaczonePole(false);
                pole[y][x].setZaznaczonyPionek(false);
                pole[y][x].setZaznaczonaDamka(false);
            }
        }
    }
}