package warcaby;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Panel extends JPanel implements ActionListener, MouseListener {
    static boolean wyswietlanyStanGry = false;
    Graphics2D g2d;
    JButton nowaGra;
    JButton cofnij;
    Color Black;
    Color White;
    Color Tlo;
    Plansza plansza;
    public Panel() {
        setLayout(null);
        setBackground(Tlo);
        nowaGra = new JButton("Nowa gra");
        cofnij = new JButton("Cofnij ruch");
        cofnij.setEnabled(false);
        nowaGra.addActionListener(this);
        cofnij.addActionListener(this);
        addMouseListener(this);
        add(nowaGra);
        add(cofnij);
        Black = new Color(90,91,86);
        White = new Color(233,233,223);
        Tlo = new Color(223,223,223);
        plansza = new Plansza();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2d = (Graphics2D) g;
        sprawdzWarunki();
        rysujPlansze();
        rysujMenu();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if( e.getSource() == nowaGra ) {
            Plansza.ustawionePionki = true;
            Plansza.mozliweCofniecie = false;
            plansza.wyczysc();
            wyswietlanyStanGry = true;
            repaint();
            JOptionPane.showMessageDialog(this, "Nowa gra. Rozgrywkę zaczynają pionki czarne.");
        }
        if( e.getSource() == cofnij ) {
            Plansza.cofniecie = true;
            repaint();
        }
    }
    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent mysz) {
        plansza.odznaczPionek();
        if( plansza.poprzednioZaznaczonyPionek == false ) {
            plansza.wyczyscProponowaneRuchy();
            if( plansza.zaznaczPionek( mysz.getX(), mysz.getY() ) == true ) {
                plansza.zaproponujRuch();
                plansza.zaproponujRuchDamka();
                plansza.poprzednioZaznaczonyPionek = true;
            }
            else if( plansza.zaznaczPionek( mysz.getX(), mysz.getY() ) == false ) plansza.poprzednioZaznaczonyPionek = false;
        }
        else if( plansza.poprzednioZaznaczonyPionek == true ) {
            if( plansza.zaznaczPionek( mysz.getX(), mysz.getY() ) == true ) {
                plansza.poprzednioZaznaczonyPionek = true;
                plansza.wyczyscProponowaneRuchy();
                plansza.zaproponujRuch();
                plansza.zaproponujRuchDamka();
            }
            else if( plansza.zaznaczPionek( mysz.getX(), mysz.getY() ) == false ) {
                plansza.poprzednioZaznaczonyPionek = false;
                if( plansza.wykonajRuch( mysz.getX(), mysz.getY() ) == true ) {
                    Gracz.zmienGracza();
                }
                plansza.wyczyscProponowaneRuchy();
            }
        }
        plansza.sprawdzCzyDamka();
        plansza.wyswietl();
        System.out.println("myszX: "+mysz.getX()+"\tobecneX: "+plansza.obecneX+"\tpoprzednieX: "+plansza.poprzednieX+"\tprzeciwnikX: "+plansza.przeciwnikX);
        System.out.println("myszY: "+mysz.getY()+"\tobecneY: "+plansza.obecneY+"\tpoprzednieY: "+plansza.poprzednieY+"\tprzeciwnikY: "+plansza.przeciwnikY);
        repaint();
    }
    void sprawdzWarunki() {
        if( Gracz.lPionkow2 == 0 ) {
            Plansza.mozliweCofniecie = false;
            wyswietlanyStanGry = false;
            plansza.wyczysc();
            Gracz.ustawStanPoczatkowy();
            JOptionPane.showMessageDialog(this, "Koniec gry. Wygrały pionki czarne.");
        }
        else if( Gracz.lPionkow1 == 0 ) {
            Plansza.mozliweCofniecie = false;
            wyswietlanyStanGry = false;
            plansza.wyczysc();
            Gracz.ustawStanPoczatkowy();
            JOptionPane.showMessageDialog(this, "Koniec gry. Wygrały pionki białe.");
        }
        if( Plansza.ustawionePionki == true ) {
            plansza.ustawPionki();
            Plansza.ustawionePionki = false;
        }
        if( Plansza.cofniecie == true ) {
            plansza.cofnijRuch();
            Plansza.cofniecie = false;
        }
        if( Plansza.mozliweCofniecie == false ) {
            cofnij.setEnabled(false);
        }
        else if( Plansza.mozliweCofniecie == true ) {
            cofnij.setEnabled(true);
        }
    }
    void rysujMenu() {
        g2d.setColor(Black);
        g2d.setFont(new Font("Arial Bold", Font.ITALIC, 28));
        g2d.drawString("WARCABY", 400, 50);
        nowaGra.setBounds(420, 90, 100, 30);
        cofnij.setBounds(420, 130, 100, 30);
        g2d.setFont(new Font("Helvetica", Font.PLAIN, 20));
        
        if( wyswietlanyStanGry == true ) {
            g2d.drawString("Gracz 1", 395, 230);
        g2d.drawString("Gracz 2", 395, 260);
            g2d.setFont(new Font("Helvetica", Font.PLAIN, 14));
            g2d.drawString(Gracz.lPionkow1+" pionków", 475, 230);
            g2d.drawString(Gracz.lPionkow2+" pionków", 475, 260);
            g2d.setFont(new Font("Helvetica", Font.PLAIN, 16));
            g2d.drawString("Ruch należy", 395, 310);
            g2d.drawString("do gracza "+Gracz.ruch+".", 395, 330);
            g2d.setColor(Color.lightGray);
            g2d.fillRect( plansza.WspX+Pole.szer*8+100,290,Pole.szer,Pole.szer );
            if( Gracz.ruch == 1 ) {
                g2d.setColor(Black);
                g2d.fillOval( Pole.szer*8+8+100,8+290, Pole.szerPionka, Pole.szerPionka );
            }
            else if( Gracz.ruch == 2 ) {
                g2d.setColor(White);
                g2d.fillOval( Pole.szer*8+8+100,8+290, Pole.szerPionka, Pole.szerPionka );
            }
        }
    }
    void rysujPlansze() {
        for( int y=0; y<8; y++ ) {
            for( int x=0; x<8; x++) {
                if( plansza.pole[y][x].getDozwolone() == false ) {
                    g2d.setColor(Color.white);
                    g2d.fillRect( plansza.WspX+Pole.szer*x,plansza.WspY+Pole.szer*y,Pole.szer,Pole.szer );
                }
                else if( plansza.pole[y][x].getDozwolone() == true ) {
                    g2d.setColor(Color.lightGray);
                    g2d.fillRect( plansza.WspX+Pole.szer*x,plansza.WspY+Pole.szer*y,Pole.szer,Pole.szer );
                }
                if( plansza.pole[y][x].getZaznaczonePole() == true ) {
                    g2d.setColor(Color.blue);
                    g2d.fillRect( plansza.WspX+Pole.szer*x,plansza.WspY+Pole.szer*y,Pole.szer,Pole.szer );
                }
                if( plansza.pole[y][x].getProponowane() == true ) {
                    g2d.setColor(Color.gray);
                    g2d.fillRect( plansza.WspX+Pole.szer*x,plansza.WspY+Pole.szer*y,Pole.szer,Pole.szer );
                }
                if( plansza.pole[y][x].getStan() == 'x' ) {
                    g2d.setColor(Black);
                    g2d.fillOval(plansza.WspX+Pole.szer*x+8,plansza.WspY+Pole.szer*y+8, Pole.szerPionka, Pole.szerPionka);
                }
                else if( plansza.pole[y][x].getStan() == 'X' ) {
                    g2d.setColor(Black);
                    g2d.fillOval(plansza.WspX+Pole.szer*x+8,plansza.WspY+Pole.szer*y+8, Pole.szerPionka, Pole.szerPionka);
                    g2d.setColor(White);
                    g2d.setFont(new Font("Helvetica", Font.PLAIN, 20));
                    g2d.drawString("D", plansza.WspX+Pole.szer*x+18,plansza.WspY+Pole.szer*y+32);
                }
                else if( plansza.pole[y][x].getStan() == 'o' ) {
                    g2d.setColor(White);
                    g2d.fillOval(plansza.WspX+Pole.szer*x+8,plansza.WspY+Pole.szer*y+8, Pole.szerPionka, Pole.szerPionka);
                }
                else if( plansza.pole[y][x].getStan() == 'O' ) {
                    g2d.setColor(White);
                    g2d.fillOval(plansza.WspX+Pole.szer*x+8,plansza.WspY+Pole.szer*y+8, Pole.szerPionka, Pole.szerPionka);
                    g2d.setColor(Black);
                    g2d.setFont(new Font("Helvetica", Font.PLAIN, 20));
                    g2d.drawString("D", plansza.WspX+Pole.szer*x+18,plansza.WspY+Pole.szer*y+32);
                }
                if( plansza.pole[y][x].getZaznaczonyPionek() == true ) {
                    g2d.setColor(Color.orange);
                    g2d.fillOval(plansza.WspX+Pole.szer*x+8,plansza.WspY+Pole.szer*y+8, Pole.szerPionka, Pole.szerPionka);
                }
                else if( plansza.pole[y][x].getZaznaczonaDamka() == true ) {
                    g2d.setColor(Color.orange);
                    g2d.fillOval(plansza.WspX+Pole.szer*x+8,plansza.WspY+Pole.szer*y+8, Pole.szerPionka, Pole.szerPionka);
                    g2d.setColor(White);
                    g2d.setFont(new Font("Helvetica", Font.PLAIN, 20));
                    g2d.drawString("D", plansza.WspX+Pole.szer*x+18,plansza.WspY+Pole.szer*y+32);
                }
            }
        }
    }
}