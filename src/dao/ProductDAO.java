package dao;

import db.DBConnection;
import java.sql.*;

public class ProductDAO {

    public int add(String name, double price) throws SQLException {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Product name cannot be empty");
        if (price <= 0)
            throw new IllegalArgumentException("Price must be greater than 0");

        String sql = "INSERT INTO product(name, price) VALUES (?, ?) RETURNING id";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setDouble(2, price);

            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getInt("id");
            }

        } catch (SQLException e) {
            System.out.println("Error while adding product");
            throw e;
        }
    }

    public void printAll() throws SQLException {
        String sql = "SELECT id, name, price FROM product ORDER BY id";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println(
                        rs.getString("name") + " | " +
                                rs.getDouble("price")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error while reading products");
            throw e;
        }
    }


    public void updatePrice(int id, double newPrice) throws SQLException {
        if (newPrice <= 0)
            throw new IllegalArgumentException("New price must be > 0");

        String sql = "UPDATE product SET price = ? WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, newPrice);
            ps.setInt(2, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error while updating product price");
            throw e;
        }
    }


    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM product WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error while deleting product");
            throw e;
        }
    }
}
