import java.awt.*;
import javax.swing.*;

class UnMobile extends JPanel implements Runnable
{
    int saLargeur, saHauteur, sonDebDessin;
    final int sonCote=40;
	static int[] dico_pas = {1, 2, 3, 4, 5, 6, 7};
	static semaphoreGeneral sem = new semaphoreGeneral(10);
    
    UnMobile(int telleLargeur, int telleHauteur)
    {
	super();
	saLargeur = telleLargeur;
	saHauteur = telleHauteur;
	setSize(telleLargeur, telleHauteur);
    }

    public void run()
    {
		int sonPas = dico_pas[(int)(Math.random() * 7)];
		int sonTemps = 5;
		while (true)
		{
			for (sonDebDessin=0; sonDebDessin < (saLargeur * 1/3) - (sonPas + 40); sonDebDessin+= sonPas) //parcours du premier tier
			{
				repaint();
				try
				{
					Thread.sleep(sonTemps);
				}
				catch (InterruptedException telleExcp)
				{
					telleExcp.printStackTrace();
				}
			}
			sem.syncWait();
			for (sonDebDessin=(saLargeur * 1/3) - (sonPas + 40); sonDebDessin < (saLargeur * 2/3) - (sonPas + 40); sonDebDessin += sonPas) //premier au deuxième tier
			{
				repaint();
				try
				{
					Thread.sleep(sonTemps);
				}
				catch (InterruptedException telleExcp)
				{
					telleExcp.printStackTrace();
				}
			}
			sem.syncSignal();
			for (sonDebDessin=(saLargeur * 2/3) - (sonPas + 40); sonDebDessin < saLargeur - (sonPas + 40); sonDebDessin += sonPas) //deuxieme au troisième tier
			{
				repaint();
				try
				{
					Thread.sleep(sonTemps);
				}
				catch (InterruptedException telleExcp)
				{
					telleExcp.printStackTrace();
				}
			}
			for (sonDebDessin=saLargeur - (sonPas + 40); sonDebDessin > (saLargeur * 2/3) - (sonPas + 40); sonDebDessin -= sonPas) //troisième au deuxième tier
			{
				repaint();
				try
				{
					Thread.sleep(sonTemps);
				}
				catch (InterruptedException telleExcp)
				{
					telleExcp.printStackTrace();
				}
			}
			sem.syncWait();
			for (sonDebDessin=(saLargeur * 2/3) - (sonPas + 40); sonDebDessin > (saLargeur * 1/3) - (sonPas + 40); sonDebDessin -= sonPas) //deuximèe au premier tier
			{
				repaint();
				try
				{
					Thread.sleep(sonTemps);
				}
				catch (InterruptedException telleExcp)
				{
					telleExcp.printStackTrace();
				}
			}
			sem.syncSignal();
			for (sonDebDessin=(saLargeur * 1/3) - (sonPas + 40); sonDebDessin > 0; sonDebDessin -= sonPas) //premier tier au début
			{
				repaint();
				try
				{
					Thread.sleep(sonTemps);
				}
				catch (InterruptedException telleExcp)
				{
					telleExcp.printStackTrace();
				}
			}
		}
    }
    

    public void paintComponent(Graphics telCG)
    {
	super.paintComponent(telCG);
	telCG.fillRect(sonDebDessin, saHauteur/2, sonCote, sonCote);
    }
}