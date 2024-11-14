public class BAL {
    private final String[] tampon;
    private int tete = 0;
    private int queue = 0;
    private int nbLettres = 0;
    private final int capacite;

    public BAL(int capacite) {
        this.capacite = capacite;
        this.tampon = new String[capacite];
    }

    public synchronized void deposer(String lettre) throws InterruptedException {
        while (nbLettres == capacite) {
            wait();
        }
        tampon[queue] = lettre;
        queue = (queue + 1) % capacite;
        nbLettres++;
        notifyAll();
    }

    public synchronized String retirer() throws InterruptedException {
        while (nbLettres == 0) {
            wait();
        }
        String lettre = tampon[tete];
        tete = (tete + 1) % capacite;
        nbLettres--;
        notifyAll();
        return lettre;
    }

    public synchronized int getNbLettres() {
        return nbLettres;
    }
}
