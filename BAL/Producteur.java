import java.util.Random;

public class Producteur extends Thread {
    private BAL bal;

    public Producteur(BAL bal) {
        this.bal = bal;
    }

    @Override
    public void run() {
        try {
            Random random = new Random();
            String lettre = String.valueOf((char) (random.nextInt(26) + 'A'));
            bal.deposer(lettre);
            System.out.println("Producteur a déposé la lettre: " + lettre);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
