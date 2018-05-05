package warcaby;

public class Pole {
    final static int szer = 48;
    final static int szerPionka = 32;
    private boolean dozwolone;
    private boolean zaznaczonePole;
    private boolean zaznaczonyPionek;
    private boolean zaznaczonaDamka;
    private boolean proponowane;
    private boolean proponowaneBicie;
    private char stan;
    Pole() {
        zaznaczonePole = false;
        zaznaczonyPionek = false;
        zaznaczonaDamka = false;
        proponowane = false;
        proponowaneBicie = false;
        stan = '.';
    }
    void wyczysc() {
        zaznaczonePole = false;
        zaznaczonyPionek = false;
        zaznaczonaDamka = false;
        proponowane = false;
        proponowaneBicie = false;
        stan = '.';
    }
    void setDozwolone( boolean dozwolone ) { this.dozwolone = dozwolone; }
    void setZaznaczonePole( boolean zaznaczonePole ) { this.zaznaczonePole = zaznaczonePole; }
    void setZaznaczonyPionek( boolean zaznaczonyPionek ) { this.zaznaczonyPionek = zaznaczonyPionek; }
    void setZaznaczonaDamka( boolean zaznaczonaDamka ) { this.zaznaczonaDamka = zaznaczonaDamka; }
    void setProponowane( boolean proponowane ) { this.proponowane = proponowane; }
    void setProponowaneBicie( boolean proponowaneBicie ) { this.proponowaneBicie = proponowaneBicie; }
    void setStan( char stan ) { this.stan = stan; }
    boolean getDozwolone() { return dozwolone; }
    boolean getZaznaczonePole() { return zaznaczonePole; }
    boolean getZaznaczonyPionek() { return zaznaczonyPionek; }
    boolean getZaznaczonaDamka() { return zaznaczonaDamka; }
    boolean getProponowane() { return proponowane; }
    boolean getProponowaneBicie() { return proponowaneBicie; }
    char getStan() { return stan; }
}