package TASK-6;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;

public class ProductAddDelete extends JFrame {
    private JTable table;
    private JTextField tfName, tfCategory, tfPrice;
    private DefaultTableModel model;
    private List<Product> productList;

    public ProductAddDelete() {
        setTitle("Add and Delete Products - Task 5");
        setSize(700, 400);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(20, 20, 60, 25);
        add(lblName);

        tfName = new JTextField();
        tfName.setBounds(80, 20, 150, 25);
        add(tfName);

        JLabel lblCategory = new JLabel("Category:");
        lblCategory.setBounds(250, 20, 70, 25);
        add(lblCategory);

        tfCategory = new JTextField();
        tfCategory.setBounds(330, 20, 150, 25);
        add(tfCategory);

        JLabel lblPrice = new JLabel("Price:");
        lblPrice.setBounds(500, 20, 50, 25);
        add(lblPrice);

        tfPrice = new JTextField();
        tfPrice.setBounds(550, 20, 100, 25);
        add(tfPrice);

        JButton btnAdd = new JButton("Add");
        btnAdd.setBounds(280, 60, 120, 30);
        add(btnAdd);

        model = new DefaultTableModel(new String[]{"ID", "Name", "Category", "Price"}, 0);
        table = new JTable(model);
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(20, 110, 640, 180);
        add(pane);

        JButton btnDelete = new JButton("Delete Selected");
        btnDelete.setBounds(260, 310, 160, 30);
        add(btnDelete);

        loadTable();

        // ➕ Add Product Action
        btnAdd.addActionListener(e -> {
            String name = tfName.getText();
            String category = tfCategory.getText();
            double price;

            try {
                price = Double.parseDouble(tfPrice.getText());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid price!");
                return;
            }

            Product p = new Product(0, name, category, price);
            if (ProductDAO.insertProduct(p)) {
                JOptionPane.showMessageDialog(null, "Product added successfully.");
                tfName.setText(""); tfCategory.setText(""); tfPrice.setText("");
                loadTable();
            } else {
                JOptionPane.showMessageDialog(null, "Add failed.");
            }
        });

        // 🗑️ Delete Product Action
        btnDelete.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Select a product to delete.");
                return;
            }
            int id = (int) model.getValueAt(row, 0);
            if (ProductDAO.deleteProduct(id)) {
                JOptionPane.showMessageDialog(null, "Product deleted.");
                loadTable();
            } else {
                JOptionPane.showMessageDialog(null, "Delete failed.");
            }
        });

        setVisible(true);
    }

    private void loadTable() {
        model.setRowCount(0);
        productList = ProductDAO.getProducts("");
        for (Product p : productList) {
            model.addRow(new Object[]{p.getId(), p.getName(), p.getCategory(), p.getPrice()});
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ProductAddDelete());
    }
}
