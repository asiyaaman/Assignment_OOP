import java.sql.*;

public class Main {
    public static void main(String[] args) {

        String url = "jdbc:postgresql://localhost:5432/warehouseinv_db";
        String user = "postgres";
        String password = "251098";

        try (Connection con = DriverManager.getConnection(url, user, password)) {

            // CREATE (INSERT) + get generated id
            int newId;
            try (PreparedStatement psInsert = con.prepareStatement(
                    "INSERT INTO product(name, price) VALUES (?, ?) RETURNING id"
            )) {
                psInsert.setString(1, "Laptop");
                psInsert.setDouble(2, 1200);
                ResultSet r = psInsert.executeQuery();
                r.next();
                newId = r.getInt("id");
            }

            // READ
            System.out.println("After INSERT:");
            printAll(con);

            // UPDATE
            try (PreparedStatement psUpdate = con.prepareStatement(
                    "UPDATE product SET price = ? WHERE id = ?"
            )) {
                psUpdate.setDouble(1, 1300);
                psUpdate.setInt(2, newId);
                psUpdate.executeUpdate();
            }

            System.out.println("After UPDATE (id=" + newId + "):");
            printAll(con);

            // DELETE
            try (PreparedStatement psDelete = con.prepareStatement(
                    "DELETE FROM product WHERE id = ?"
            )) {
                psDelete.setInt(1, newId);
                psDelete.executeUpdate();
            }

            System.out.println("After DELETE (id=" + newId + "):");
            printAll(con);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void printAll(Connection con) throws SQLException {
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM product ORDER BY id")) {
            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                                rs.getString("name") + " | " +
                                rs.getDouble("price")
                );
            }
        }
    }
}
