import java.awt.*;
import javax.swing.*;

class UnMobile extends JPanel implements Runnable
{
    int saLargeur, saHauteur, sonDebDessin;
    final int sonPas = 5, sonTemps=5, sonCote=40;
    
    UnMobile(int telleLargeur, int telleHauteur)
    {
	super();
	saLargeur = telleLargeur;
	saHauteur = telleHauteur;
	setSize(telleLargeur, telleHauteur);
    }

    public void run()
    {
		while (true)
		{
			for (sonDebDessin=600; sonDebDessin < 1210; sonDebDessin+= sonPas)
			{
			repaint();
			try{Thread.sleep(sonTemps);}
			catch (InterruptedException telleExcp)
				{telleExcp.printStackTrace();}
			}
		
			for (sonDebDessin=1210; sonDebDessin > 600; sonDebDessin-= sonPas)
			{
			repaint();
			try{Thread.sleep(sonTemps);}
			catch (InterruptedException telleExcp)
				{telleExcp.printStackTrace();}
			}
		}
    }
    

    public void paintComponent(Graphics telCG)
    {
	super.paintComponent(telCG);
	telCG.fillRect(sonDebDessin, saHauteur/2, sonCote, sonCote);
    }
}