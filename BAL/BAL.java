import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class BAL {
    private final BlockingQueue<String> queue;

    public BAL(int capacite) {
        this.queue = new ArrayBlockingQueue<>(capacite);
    }

    public boolean deposer(String lettre) throws InterruptedException {
        boolean success = queue.offer(lettre, 200, TimeUnit.MILLISECONDS);
        if (success) {
            System.out.println("Producteur a déposé: " + lettre);
        } else {
            System.out.println("Le tampon est plein, le producteur abandonne: " + lettre);
        }
        return success;
    }

    public String retirer() throws InterruptedException {
        String lettre = queue.poll(200, TimeUnit.MILLISECONDS);
        if (lettre != null) {
            System.out.println("Consommateur a retiré: " + lettre);
        } else {
            System.out.println("Le tampon est vide, le consommateur repart les mains vides.");
        }
        return lettre;
    }

    public int getStock() {
        return queue.size();
    }
}
