import java.awt.*;
import javax.swing.*;


class UneFenetre extends JFrame 
{
    UnMobile sonMobile1;
    UnMobile sonMobile2;
    UnMobile sonMobile3;
    UnMobile sonMobile4;
    UnMobile sonMobile5;
    UnMobile sonMobile6;

    private final int LARG=1000, HAUT=1000;
    private final int LARG_MOBILE=1000, HAUT_MOBILE=100;
    private final int NBRLIG=6, NBRCOL=1;
    
    public UneFenetre()
    {
        super("le Mobile");
        Container leConteneur = getContentPane();
        leConteneur.setLayout (new GridLayout(NBRLIG, NBRCOL));

        // JButton bouton = new JButton("Stop/Resume");
        // bouton.addActionListener(this);
        // leConteneur.add(bouton);

        sonMobile1 = new UnMobile(LARG_MOBILE, HAUT_MOBILE);
        leConteneur.add(sonMobile1);

        sonMobile2 = new UnMobile(LARG_MOBILE, HAUT_MOBILE);
        leConteneur.add(sonMobile2);

        sonMobile3 = new UnMobile(LARG_MOBILE, HAUT_MOBILE);
        leConteneur.add(sonMobile3);

        sonMobile4 = new UnMobile(LARG_MOBILE, HAUT_MOBILE);
        leConteneur.add(sonMobile4);

        sonMobile5 = new UnMobile(LARG_MOBILE, HAUT_MOBILE);
        leConteneur.add(sonMobile5);

        sonMobile6 = new UnMobile(LARG_MOBILE, HAUT_MOBILE);
        leConteneur.add(sonMobile6);

        setSize(LARG, HAUT);
        setVisible(true);

        Thread laTache1 = new Thread(sonMobile1);
        Thread laTache2 = new Thread(sonMobile2);
        Thread laTache3 = new Thread(sonMobile3);
        Thread laTache4 = new Thread(sonMobile4);
        Thread laTache5 = new Thread(sonMobile5);
        Thread laTache6 = new Thread(sonMobile6);
        laTache1.start();
        laTache2.start();
        laTache3.start();
        laTache4.start();
        laTache5.start();
        laTache6.start();
    } 
}
