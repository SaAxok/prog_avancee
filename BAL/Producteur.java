public class Producteur extends Thread {
    private final BAL bal;

    public Producteur(BAL bal) {
        this.bal = bal;
    }

    @Override
    public void run() {
        try {
            for (char lettre = 'A'; lettre <= 'Z'; lettre++) {
                bal.deposer(String.valueOf(lettre));
                Thread.sleep(100);
            }
            bal.deposer("*");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
