package warcaby;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class Okno extends JFrame implements ActionListener, MouseMotionListener {
    JPanel panel;
    JMenuBar menuBar;
    JMenu menuGra, menuOpcje, menuInformacje;
    JMenuItem mNowa, mCofnij, mWyjscie, mPlansza, mPionki, mOProgramie, mOAutorze;
    public Okno() {
        setTitle("Warcaby");
        setSize(Pole.szer*8+6+180,Pole.szer*8+52);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        addMouseMotionListener(this);
        panel = new Panel();
        menuBar = new JMenuBar();
            menuGra = new JMenu("Gra");
                mCofnij = new JMenuItem("Cofnij");
                mCofnij.addActionListener(this);
                mCofnij.setEnabled(false);
                mNowa = new JMenuItem("Nowa");
                mNowa.addActionListener(this);
                mWyjscie = new JMenuItem("Wyjście");
                mWyjscie.addActionListener(this);
            menuOpcje = new JMenu("Opcje");
                mPlansza = new JMenuItem("Plansza");
                mPionki = new JMenuItem("Pionki");
            menuInformacje = new JMenu("Informacje");
                mOProgramie = new JMenuItem("O Programie");
                mOProgramie.addActionListener(this);
                mOAutorze = new JMenuItem("O Autorze");
                mOAutorze.addActionListener(this);
        add(panel);
        setJMenuBar(menuBar);
            menuBar.add(menuGra);
                menuGra.add(mCofnij);
                menuGra.addSeparator();
                menuGra.add(mNowa);
                menuGra.add(mWyjscie);
            menuBar.add(menuOpcje);
                menuOpcje.add(mPlansza);
                menuOpcje.add(mPionki);
            menuBar.add(menuInformacje);
                menuInformacje.add(mOProgramie);
                menuInformacje.add(mOAutorze);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if( e.getSource() == mCofnij ) {
            Plansza.cofniecie = true;
            panel.repaint();
        }
        if( e.getSource() == mNowa ) {
            Plansza.ustawionePionki = true;
            Panel.wyswietlanyStanGry = true;
            repaint();
            JOptionPane.showMessageDialog(this, "Nowa gra. Rozgrywkę zaczynają pionki czarne.");
        }
        if( e.getSource() == mWyjscie ) dispose();
        if( e.getSource() == mOProgramie ) {
            JOptionPane.showMessageDialog(this, "Gra została napisana jako projekt na zaliczenie przedmiotu Programowanie Zaawansowane");
        }
        if( e.getSource() == mOAutorze ) {
            JOptionPane.showMessageDialog(this, "Autor: Karol Kiersnowski, nr indeksu: 59200");
        }
    }
    @Override
    public void mouseDragged(MouseEvent e) {}
    @Override
    public void mouseMoved(MouseEvent e) {
        //System.out.println("ruch");
        if( Plansza.mozliweCofniecie == false ) {
            mCofnij.setEnabled(false);
        }
        else if( Plansza.mozliweCofniecie == true ) {
            mCofnij.setEnabled(true);
        }
    }
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Okno();
            }
        });
    }
}