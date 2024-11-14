public class Consommateur extends Thread {
    private BAL bal;

    public Consommateur(BAL bal) {
        this.bal = bal;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String lettre = bal.retirer();
                System.out.println("Consommateur a retiré: " + lettre);
                if ("*".equals(lettre)) {
                    break;
                }
                Thread.sleep(150);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
