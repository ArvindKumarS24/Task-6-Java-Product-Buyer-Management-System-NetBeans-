package ProductManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;

public class BuyerAddDelete extends JFrame {
    private JTable table;
    private JTextField tfName, tfEmail, tfPhone;
    private DefaultTableModel model;
    private List<Buyer> buyerList;

    public BuyerAddDelete() {
        setTitle("Add and Delete Buyers - Task 5");
        setSize(700, 400);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(20, 20, 60, 25);
        add(lblName);

        tfName = new JTextField();
        tfName.setBounds(80, 20, 150, 25);
        add(tfName);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(250, 20, 60, 25);
        add(lblEmail);

        tfEmail = new JTextField();
        tfEmail.setBounds(310, 20, 150, 25);
        add(tfEmail);

        JLabel lblPhone = new JLabel("Phone:");
        lblPhone.setBounds(480, 20, 60, 25);
        add(lblPhone);

        tfPhone = new JTextField();
        tfPhone.setBounds(540, 20, 120, 25);
        add(tfPhone);

        JButton btnAdd = new JButton("Add");
        btnAdd.setBounds(280, 60, 120, 30);
        add(btnAdd);

        model = new DefaultTableModel(new String[]{"ID", "Name", "Email", "Phone"}, 0);
        table = new JTable(model);
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(20, 110, 640, 180);
        add(pane);

        JButton btnDelete = new JButton("Delete Selected");
        btnDelete.setBounds(260, 310, 160, 30);
        add(btnDelete);

        loadTable();

        // ➕ Add Buyer Action
        btnAdd.addActionListener(e -> {
            String name = tfName.getText();
            String email = tfEmail.getText();
            String phone = tfPhone.getText();

            if (name.isEmpty() || email.isEmpty() || phone.isEmpty()) {
                JOptionPane.showMessageDialog(null, "All fields are required!");
                return;
            }

            Buyer b = new Buyer(0, name, email, phone);
            if (BuyerDAO.insertBuyer(b)) {
                JOptionPane.showMessageDialog(null, "Buyer added.");
                tfName.setText(""); tfEmail.setText(""); tfPhone.setText("");
                loadTable();
            } else {
                JOptionPane.showMessageDialog(null, "Add failed.");
            }
        });

        // 🗑️ Delete Buyer Action
        btnDelete.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Select a buyer to delete.");
                return;
            }

            int id = (int) model.getValueAt(row, 0);
            if (BuyerDAO.deleteBuyer(id)) {
                JOptionPane.showMessageDialog(null, "Buyer deleted.");
                loadTable();
            } else {
                JOptionPane.showMessageDialog(null, "Delete failed.");
            }
        });

        setVisible(true);
    }

    private void loadTable() {
        model.setRowCount(0);
        buyerList = BuyerDAO.getBuyers("");
        for (Buyer b : buyerList) {
            model.addRow(new Object[]{b.getId(), b.getName(), b.getEmail(), b.getPhone()});
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BuyerAddDelete());
    }
}
