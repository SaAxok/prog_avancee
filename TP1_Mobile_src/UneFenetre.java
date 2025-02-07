import java.awt.*;
import javax.swing.*;

class UneFenetre extends JFrame 
{
    UnMobile sonMobile;
    JButton sonButton1;
    private final int LARG=1240, HAUT=250;
    
    public UneFenetre()
    {
        super("le Mobile");
        Container leConteneur = getContentPane();
        sonMobile = new UnMobile(LARG, HAUT);
        leConteneur.add(sonMobile);
        Thread laTache = new Thread (sonMobile);
        laTache.start();
        setSize(LARG, HAUT);
        setVisible(true);

        sonButton1 = new JButton("Start/Stop");
        sonButton1.addVetoableChangeListener(null);
        leConteneur.add(sonButton1);
    }
}
