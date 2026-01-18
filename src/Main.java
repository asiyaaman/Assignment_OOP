import dao.ProductDAO;
import dao.SupplierDAO;
import java.sql.*;


public class Main {
    public static void main(String[] args) {

        ProductDAO productDAO = new ProductDAO();
        SupplierDAO supplierDAO = new SupplierDAO();

        try {
            int productId = productDAO.add("Laptop", 1200);
            System.out.println("PRODUCT after INSERT:");
            productDAO.printAll();

            productDAO.updatePrice(productId, 1300);
            System.out.println("\nPRODUCT after UPDATE:");
            productDAO.printAll();

            productDAO.delete(productId);
            System.out.println("\nPRODUCT after DELETE:");
            productDAO.printAll();

            int supplierId = supplierDAO.addSupplier("TechSupply", "info@tech.com", "+7-707-555-45-65");
            System.out.println("\nSUPPLIER after INSERT:");
            supplierDAO.printAllSuppliers();

            supplierDAO.updateEmail(supplierId, "support@tech.com");
            System.out.println("\nSUPPLIER after UPDATE:");
            supplierDAO.printAllSuppliers();

            supplierDAO.deleteSupplier(supplierId);
            System.out.println("\nSUPPLIER after DELETE:");
            supplierDAO.printAllSuppliers();

        } catch (IllegalArgumentException e) {
            System.out.println("INPUT ERROR: " + e.getMessage());

        } catch (SQLException e) {
            System.out.println("DATABASE ERROR: " + e.getMessage());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
