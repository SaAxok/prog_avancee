public class Main {
    public static void main(String[] args) {
        int capacite = 20;
        BAL bal = new BAL(capacite);

        Producteur producteur = new Producteur(bal);
        Consommateur consommateur = new Consommateur(bal);

        producteur.start();
        consommateur.start();
    }
}
