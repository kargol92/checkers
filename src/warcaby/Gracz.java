package warcaby;

public class Gracz {
    static int ruch = 1;
    static int lPionkow1 = 12;
    static int lPionkow2 = 12;
    //boolean bialy;
    //int lPionkow;
    static void zmienGracza() {
        if( ruch == 1 ) ruch = 2;
        else if( ruch == 2 ) ruch = 1;
    }
    static void ustawStanPoczatkowy() {
        lPionkow1 = 12;
        lPionkow2 = 12;
    }
//    Gracz(boolean bialy) {
//        this.bialy = true;
//        lPionkow = 12;
//        pionek = new Pionek[lPionkow];
//        if( bialy == false ) {
//            pionek[0] = new Pionek(0,1,bialy);
//            pionek[1] = new Pionek(0,3,bialy);
//            pionek[2] = new Pionek(0,5,bialy);
//            pionek[3] = new Pionek(0,7,bialy);
//            pionek[4] = new Pionek(1,0,bialy);
//            pionek[5] = new Pionek(1,2,bialy);
//            pionek[6] = new Pionek(1,4,bialy);
//            pionek[7] = new Pionek(1,6,bialy);
//            pionek[8] = new Pionek(2,1,bialy);
//            pionek[9] = new Pionek(2,3,bialy);
//            pionek[10] = new Pionek(2,5,bialy);
//            pionek[11] = new Pionek(2,7,bialy);
//        }
//        else if( bialy == true ) {
//            pionek[0] = new Pionek(5,0,bialy);
//            pionek[1] = new Pionek(5,2,bialy);
//            pionek[2] = new Pionek(5,4,bialy);
//            pionek[3] = new Pionek(5,6,bialy);
//            pionek[4] = new Pionek(6,1,bialy);
//            pionek[5] = new Pionek(6,3,bialy);
//            pionek[6] = new Pionek(6,5,bialy);
//            pionek[7] = new Pionek(6,7,bialy);
//            pionek[8] = new Pionek(7,0,bialy);
//            pionek[9] = new Pionek(7,2,bialy);
//            pionek[10] = new Pionek(7,4,bialy);
//            pionek[11] = new Pionek(7,6,bialy);
//        }
//    }
}
