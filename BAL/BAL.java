public class BAL {
    private String lettre = null;

    public synchronized void deposer(String lettre) throws InterruptedException {
        while (this.lettre != null) {
            wait();
        }
        this.lettre = lettre;
        notifyAll();
    }

    public synchronized String retirer() throws InterruptedException {
        while (this.lettre == null) {
            wait();
        }
        String lettreRetiree = this.lettre;
        this.lettre = null;
        notifyAll();
        return lettreRetiree;
    }
}
