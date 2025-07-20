package ProductManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;

public class ProductManager extends javax.swing.JFrame {
    private JTable table;
    private JTextField tfSearch, tfName, tfCategory, tfPrice;
    private DefaultTableModel model;
    private List<Product> productList;

    public ProductManager() {
        setTitle("Product Manager - Task 6");
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

        model = new DefaultTableModel(new String[]{"ID", "Name", "Category", "Price"}, 0);
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

        JLabel lblCategory = new JLabel("Category:");
        lblCategory.setBounds(250, 220, 70, 25);
        add(lblCategory);

        tfCategory = new JTextField();
        tfCategory.setBounds(320, 220, 150, 25);
        add(tfCategory);

        JLabel lblPrice = new JLabel("Price:");
        lblPrice.setBounds(490, 220, 50, 25);
        add(lblPrice);

        tfPrice = new JTextField();
        tfPrice.setBounds(540, 220, 120, 25);
        add(tfPrice);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.setBounds(280, 270, 120, 30);
        add(btnUpdate);

        // Load initial data
        loadTable("");

        // Search Action
        btnSearch.addActionListener(e -> loadTable(tfSearch.getText()));

        // Table Click -> Load values to form
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                Product p = productList.get(row);
                tfName.setText(p.getName());
                tfCategory.setText(p.getCategory());
                tfPrice.setText(String.valueOf(p.getPrice()));
            }
        });

        // Update Button Action
        btnUpdate.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Please select a product to update.");
                return;
            }

            Product p = productList.get(row);
            p.setName(tfName.getText());
            p.setCategory(tfCategory.getText());
            try {
                p.setPrice(Double.parseDouble(tfPrice.getText()));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid price!");
                return;
            }

            if (ProductDAO.updateProduct(p)) {
                JOptionPane.showMessageDialog(null, "Product updated successfully.");
                loadTable(tfSearch.getText());
            } else {
                JOptionPane.showMessageDialog(null, "Update failed.");
            }
        });

        setVisible(true);
    }

    private void loadTable(String keyword) {
        model.setRowCount(0);
        productList = ProductDAO.getProducts(keyword);
        for (Product p : productList) {
            model.addRow(new Object[]{p.getId(), p.getName(), p.getCategory(), p.getPrice()});
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ProductManager());
    }
}
