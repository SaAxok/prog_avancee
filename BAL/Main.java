import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        BAL bal = new BAL();

        JFrame frame = new JFrame("Producteur-Consommateur BAL");
        JTextField letterField = new JTextField(10);
        JTextArea displayArea = new JTextArea(10, 30);
        displayArea.setEditable(false);

        JButton depositButton = new JButton("Déposer Lettre");

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Lettre:"));
        inputPanel.add(letterField);
        inputPanel.add(depositButton);

        frame.add(inputPanel, "North");
        frame.add(new JScrollPane(displayArea), "Center");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        Consommateur consommateur = new Consommateur(bal, displayArea);
        consommateur.start();

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String lettre = letterField.getText().trim();
                if (lettre.isEmpty()) return;

                new Thread(() -> {
                    try {
                        bal.deposer(lettre);
                        SwingUtilities.invokeLater(() -> displayArea.append("Producteur a déposé: " + lettre + "\n"));
                        if ("Q".equalsIgnoreCase(lettre)) {
                            System.exit(0);
                        }
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }).start();
                
                letterField.setText("");
            }
        });
    }
}
