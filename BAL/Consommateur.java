public class Consommateur extends Thread {
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
                    break; 
                }
                Thread.sleep(500); 
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
