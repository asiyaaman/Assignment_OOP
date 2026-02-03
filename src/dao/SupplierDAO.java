package dao;

import db.DBConnection;

import java.sql.*;

public class SupplierDAO {


    public int addSupplier(String name, String email, String phone) throws SQLException {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Supplier name cannot be empty");

        String sql = "INSERT INTO supplier(name, email, phone) VALUES (?, ?, ?) RETURNING id";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, phone);

            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getInt("id");
            }

        } catch (SQLException e) {
            System.out.println("Error while adding supplier");
            throw e;
        }
    }

    public void printAllSuppliers() throws SQLException {
        String sql = "SELECT id, name, email, phone FROM supplier ORDER BY id";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println(
                        rs.getString("name") + " | " +
                                rs.getString("email") + " | " +
                                rs.getString("phone")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error while reading suppliers");
            throw e;
        }
    }

    public void updateEmail(int id, String newEmail) throws SQLException {
        String sql = "UPDATE supplier SET email = ? WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, newEmail);
            ps.setInt(2, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error while updating supplier email");
            throw e;
        }
    }

    public void deleteSupplier(int id) throws SQLException {
        String sql = "DELETE FROM supplier WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error while deleting supplier");
            throw e;
        }
    }
}
