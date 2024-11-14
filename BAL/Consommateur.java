import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class Consommateur extends Thread {
    private BAL bal;
    private JTextArea displayArea;

    public Consommateur(BAL bal, JTextArea displayArea) {
        this.bal = bal;
        this.displayArea = displayArea;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String lettre = bal.retirer();
                SwingUtilities.invokeLater(() -> displayArea.append("Consommateur a retiré: " + lettre + "\n"));
                if ("Q".equalsIgnoreCase(lettre)) {
                    break;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
