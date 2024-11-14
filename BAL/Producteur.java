public class Producteur extends Thread {
    private BAL bal;

    public Producteur(BAL bal) {
        this.bal = bal;
    }

    @Override
    public void run() {
        try {
            for (char lettre = 'A'; lettre <= 'Z'; lettre++) {
                bal.deposer(String.valueOf(lettre));
                System.out.println("Producteur a déposé: " + lettre);
                Thread.sleep(100);
            }
            bal.deposer("*");
            System.out.println("Producteur a terminé la production.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
