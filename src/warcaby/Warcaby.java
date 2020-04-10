package warcaby;
import java.awt.EventQueue;

public class Warcaby {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Okno();
            }
        });
    }
}