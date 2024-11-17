public class Main {
    public static void main(String[] args) {
        int capacite = 20;
        BAL bal = new BAL(capacite);

        Thread producteur = new Thread(new Producteur(bal));
        Thread consommateur = new Thread(new Consommateur(bal));

        producteur.start();
        consommateur.start();
    }
}
