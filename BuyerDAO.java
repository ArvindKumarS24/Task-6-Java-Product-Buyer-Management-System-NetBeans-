package ProductManager;

import java.sql.*;
import java.util.*;

public class BuyerDAO {

    public static List<Buyer> getBuyers(String keyword) {
        List<Buyer> list = new ArrayList<>();
        try (Connection con = DBConnection.getConnection()) {
            String sql = "SELECT * FROM buyers WHERE name LIKE ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Buyer(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("phone")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static boolean updateBuyer(Buyer b) {
        try (Connection con = DBConnection.getConnection()) {
            String sql = "UPDATE buyers SET name=?, email=?, phone=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, b.getName());
            ps.setString(2, b.getEmail());
            ps.setString(3, b.getPhone());
            ps.setInt(4, b.getId());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
public static boolean insertBuyer(Buyer b) {
    try (Connection con = DBConnection.getConnection()) {
        String sql = "INSERT INTO buyers (name, email, phone) VALUES (?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, b.getName());
        ps.setString(2, b.getEmail());
        ps.setString(3, b.getPhone());
        return ps.executeUpdate() > 0;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}

public static boolean deleteBuyer(int id) {
    try (Connection con = DBConnection.getConnection()) {
        String sql = "DELETE FROM buyers WHERE id=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        return ps.executeUpdate() > 0;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}
