public class Consommateur implements Runnable {
    private final BAL bal;

    public Consommateur(BAL bal) {
        this.bal = bal;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String lettre = bal.retirer();
                if ("*".equals(lettre)) {
                    System.out.println("Consommateur a terminé son travail.");
                    break;
                }
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
