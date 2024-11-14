import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        int capacite = 10; // Capacité du tampon
        BAL bal = new BAL(capacite);

        // Interface graphique
        JFrame frame = new JFrame("Producteur-Consommateur avec Tampon Circulaire");
        JTextField letterField = new JTextField(10);
        JTextArea displayArea = new JTextArea(10, 30);
        displayArea.setEditable(false);
        JLabel bufferStatusLabel = new JLabel("Lettres dans le tampon: 0/" + capacite);

        JButton depositButton = new JButton("Déposer Lettre");
        JButton retrieveButton = new JButton("Récupérer Lettre");

        // Panel pour la saisie et les boutons
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Lettre:"));
        inputPanel.add(letterField);
        inputPanel.add(depositButton);
        inputPanel.add(retrieveButton);

        frame.add(inputPanel, "North");
        frame.add(new JScrollPane(displayArea), "Center");
        frame.add(bufferStatusLabel, "South");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Bouton pour déposer une lettre dans le tampon
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String lettre = letterField.getText().trim();
                if (lettre.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Veuillez entrer une lettre.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                new Thread(() -> {
                    try {
                        bal.deposer(lettre);
                        SwingUtilities.invokeLater(() -> {
                            displayArea.append("Producteur a déposé: " + lettre + "\n");
                            bufferStatusLabel.setText("Lettres dans le tampon: " + bal.getNbLettres() + "/" + capacite);
                        });
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }).start();
                letterField.setText("");
            }
        });

        // Bouton pour récupérer la dernière lettre déposée dans le tampon
        retrieveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(() -> {
                    try {
                        String lettre = bal.retirer();
                        SwingUtilities.invokeLater(() -> {
                            displayArea.append("Consommateur a retiré: " + lettre + "\n");
                            bufferStatusLabel.setText("Lettres dans le tampon: " + bal.getNbLettres() + "/" + capacite);
                        });
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }).start();
            }
        });
    }

    // Classe BAL interne statique
    public static class BAL {
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
}
