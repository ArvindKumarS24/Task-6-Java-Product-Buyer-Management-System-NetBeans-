import java.sql.*;
import java.util.*;

public class ProductDAO {

    public static List<Product> getProducts(String keyword) {
        List<Product> list = new ArrayList<>();
        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT * FROM products WHERE name LIKE ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Product(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("category"),
                    rs.getDouble("price")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static boolean updateProduct(Product p) {
        try (Connection con = DBConnection.getConnection()) {
            String sql = "UPDATE products SET name=?, category=?, price=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, p.getName());
            ps.setString(2, p.getCategory());
            ps.setDouble(3, p.getPrice());
            ps.setInt(4, p.getId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
public static boolean insertProduct(Product p) {
    try (Connection con = DBConnection.getConnection()) {
        String sql = "INSERT INTO products (name, category, price) VALUES (?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, p.getName());
        ps.setString(2, p.getCategory());
        ps.setDouble(3, p.getPrice());
        return ps.executeUpdate() > 0;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}

public static boolean deleteProduct(int id) {
    try (Connection con = DBConnection.getConnection()) {
        String sql = "DELETE FROM products WHERE id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        return ps.executeUpdate() > 0;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}
