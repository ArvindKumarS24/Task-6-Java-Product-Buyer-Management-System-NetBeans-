import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddBuyer extends JFrame {
    private JTextField nameField, emailField, phoneField, addressField;
    private JButton saveButton, exitButton;

    public AddBuyer() {
        setTitle("Add Buyer");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null); // Absolute positioning for simplicity

        JLabel titleLabel = new JLabel("ADD BUYRES");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.RED);
        titleLabel.setBounds(140, 10, 200, 30);
        add(titleLabel);

        JLabel nameLabel = new JLabel("Name");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        nameLabel.setBounds(50, 60, 80, 25);
        add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(150, 60, 180, 25);
        add(nameField);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setFont(new Font("Arial", Font.BOLD, 14));
        emailLabel.setBounds(50, 95, 80, 25);
        add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(150, 95, 180, 25);
        add(emailField);

        JLabel phoneLabel = new JLabel("Phone");
        phoneLabel.setFont(new Font("Arial", Font.BOLD, 14));
        phoneLabel.setBounds(50, 130, 80, 25);
        add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(150, 130, 180, 25);
        add(phoneField);

        JLabel addressLabel = new JLabel("Address");
        addressLabel.setFont(new Font("Arial", Font.BOLD, 14));
        addressLabel.setBounds(50, 165, 80, 25);
        add(addressLabel);

        addressField = new JTextField();
        addressField.setBounds(150, 165, 180, 25);
        add(addressField);

        saveButton = new JButton("Save");
        saveButton.setForeground(new Color(0, 153, 0)); // bright green
        saveButton.setBounds(100, 210, 80, 30);
        add(saveButton);

        exitButton = new JButton("Exit");
        exitButton.setForeground(Color.RED);
        exitButton.setBounds(220, 210, 80, 30);
        add(exitButton);

        // Action listeners
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveBuyer();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void saveBuyer() {
        String name = nameField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();
        String address = addressField.getText();

        // Basic validation example
        if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JOptionPane.showMessageDialog(this, "Buyer saved successfully!");

        // Clear fields after saving
        nameField.setText("");
        emailField.setText("");
        phoneField.setText("");
        addressField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AddBuyer().setVisible(true);
        });
    }
}
