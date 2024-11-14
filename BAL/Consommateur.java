public class Consommateur extends Thread {
    private BAL bal;

    public Consommateur(BAL bal) {
        this.bal = bal;
    }

    @Override
    public void run() {
        try {
            String lettre = bal.retirer();
            System.out.println("Consommateur a retiré la lettre: " + lettre);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
