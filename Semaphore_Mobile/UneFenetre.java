import java.awt.*;
import javax.swing.*;

class UneFenetre extends JFrame {
    private final int LARG = 1750, HAUT = 1000;
    private final int LARG_MOBILE = 1750, HAUT_MOBILE = 10;
    private final int NBRCOL = 1;
    private final int nbMobile = 50;

    public UneFenetre() {
        super("le Mobile");
        final int NBRLIG = nbMobile; 
        Container leConteneur = getContentPane();
        leConteneur.setLayout(new GridLayout(NBRLIG, NBRCOL));

        for (int i = 0; i < nbMobile; i++) {
            UnMobile unMobile = new UnMobile(LARG_MOBILE, HAUT_MOBILE);
            leConteneur.add(unMobile);
            Thread unThread = new Thread(unMobile);
            unThread.start();
        }

        setSize(LARG, HAUT);
        setVisible(true);
    }
}
