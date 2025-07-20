package ProductManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;

public class BuyerManager extends JFrame {
    private JTable table;
    private JTextField tfSearch, tfName, tfEmail, tfPhone;
    private DefaultTableModel model;
    private List<Buyer> buyerList;

    public BuyerManager() {
        setTitle("Buyer Manager - Task 6");
        setSize(700, 400);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel lblSearch = new JLabel("Search:");
        lblSearch.setBounds(20, 10, 60, 25);
        add(lblSearch);

        tfSearch = new JTextField();
        tfSearch.setBounds(80, 10, 200, 25);
        add(tfSearch);

        JButton btnSearch = new JButton("Search");
        btnSearch.setBounds(290, 10, 100, 25);
        add(btnSearch);

        model = new DefaultTableModel(new String[]{"ID", "Name", "Email", "Phone"}, 0);
        table = new JTable(model);
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(20, 50, 640, 150);
        add(pane);

        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(20, 220, 60, 25);
        add(lblName);

        tfName = new JTextField();
        tfName.setBounds(80, 220, 150, 25);
        add(tfName);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(250, 220, 50, 25);
        add(lblEmail);

        tfEmail = new JTextField();
        tfEmail.setBounds(310, 220, 150, 25);
        add(tfEmail);

        JLabel lblPhone = new JLabel("Phone:");
        lblPhone.setBounds(480, 220, 50, 25);
        add(lblPhone);

        tfPhone = new JTextField();
        tfPhone.setBounds(540, 220, 120, 25);
        add(tfPhone);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.setBounds(280, 270, 120, 30);
        add(btnUpdate);

        loadTable("");

        btnSearch.addActionListener(e -> loadTable(tfSearch.getText()));

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                Buyer b = buyerList.get(row);
                tfName.setText(b.getName());
                tfEmail.setText(b.getEmail());
                tfPhone.setText(b.getPhone());
            }
        });

        btnUpdate.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Select a buyer to update.");
                return;
            }

            Buyer b = buyerList.get(row);
            b.setName(tfName.getText());
            b.setEmail(tfEmail.getText());
            b.setPhone(tfPhone.getText());

            if (BuyerDAO.updateBuyer(b)) {
                JOptionPane.showMessageDialog(null, "Buyer updated!");
                loadTable(tfSearch.getText());
            } else {
                JOptionPane.showMessageDialog(null, "Update failed.");
            }
        });

        setVisible(true);
    }

    private void loadTable(String keyword) {
        model.setRowCount(0);
        buyerList = BuyerDAO.getBuyers(keyword);
        for (Buyer b : buyerList) {
            model.addRow(new Object[]{b.getId(), b.getName(), b.getEmail(), b.getPhone()});
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BuyerManager());
    }
}

