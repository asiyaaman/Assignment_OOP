import java.sql.*;

public class Main {

    public static void main(String[] args) {

        String url = "jdbc:postgresql://localhost:5432/warehouseinv_db";
        String user = "postgres";
        String password = "251098";

        try (Connection con = DriverManager.getConnection(url, user, password)) {

            int productId;
            try (PreparedStatement psInsert = con.prepareStatement(
                    "INSERT INTO product(name, price) VALUES (?, ?) RETURNING id"
            )) {
                psInsert.setString(1, "Laptop");
                psInsert.setDouble(2, 1200);

                try (ResultSet r = psInsert.executeQuery()) {
                    r.next();
                    productId = r.getInt("id");
                }
            }

            System.out.println("PRODUCT after INSERT:");
            printProducts(con);

            try (PreparedStatement psUpdate = con.prepareStatement(
                    "UPDATE product SET price = ? WHERE id = ?"
            )) {
                psUpdate.setDouble(1, 1300);
                psUpdate.setInt(2, productId);
                psUpdate.executeUpdate(); // UPDATE -> executeUpdate
            }

            System.out.println("\nPRODUCT after UPDATE (id=" + productId + "):");
            printProducts(con);

            try (PreparedStatement psDelete = con.prepareStatement(
                    "DELETE FROM product WHERE id = ?"
            )) {
                psDelete.setInt(1, productId);
                psDelete.executeUpdate();
            }

            System.out.println("\nPRODUCT after DELETE (id=" + productId + "):");
            printProducts(con);



            int supplierId;
            try (PreparedStatement psInsert = con.prepareStatement(
                    "INSERT INTO supplier(name, email, phone) VALUES (?, ?, ?) RETURNING id"
            )) {
                psInsert.setString(1, "TechSupply");
                psInsert.setString(2, "info@tech.com");
                psInsert.setString(3, "+7-707-555-45-65");

                try (ResultSet rs = psInsert.executeQuery()) {
                    rs.next();
                    supplierId = rs.getInt("id");
                }
            }

            System.out.println("\nSUPPLIER after INSERT:");
            printSuppliers(con);

            try (PreparedStatement psUpdate = con.prepareStatement(
                    "UPDATE supplier SET email = ? WHERE id = ?"
            )) {
                psUpdate.setString(1, "support@tech.com");
                psUpdate.setInt(2, supplierId);
                psUpdate.executeUpdate(); // UPDATE -> executeUpdate
            }

            System.out.println("\nSUPPLIER after UPDATE (id=" + supplierId + "):");
            printSuppliers(con);

            try (PreparedStatement psDelete = con.prepareStatement(
                    "DELETE FROM supplier WHERE id = ?"
            )) {
                psDelete.setInt(1, supplierId);
                psDelete.executeUpdate();
            }

            System.out.println("\nSUPPLIER after DELETE (id=" + supplierId + "):");
            printSuppliers(con);

        } catch (SQLException e) {
            handleSqlException(e);
        }
    }   private static void handleSqlException(SQLException e) {
        System.out.println("Database error: " + e.getMessage());
        System.out.println("SQLState: " + e.getSQLState());
        System.out.println("ErrorCode: " + e.getErrorCode());
    }

    private static void printProducts(Connection con) throws SQLException {
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT id, name, price FROM product ORDER BY id")) {

            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                                rs.getString("name") + " | " +
                                rs.getDouble("price")
                );
            }
        }
    }

    private static void printSuppliers(Connection con) throws SQLException {
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT id, name, email, phone FROM supplier ORDER BY id")) {

            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                                rs.getString("name") + " | " +
                                rs.getString("email") + " | " +
                                rs.getString("phone")
                );
            }
        }
    }
}
