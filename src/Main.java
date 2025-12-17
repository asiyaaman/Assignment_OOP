public class Main {
    public static void main(String[] args) {

        Product p1 = new Product(1, "Laptop", 1200);
        Product p2 = new Product(2, "Monitor", 300);

        Supplier supplier = new Supplier("TechSupply", "info@tech.com");

        Inventory inv1 = new Inventory(p1, supplier, 10);
        Inventory inv2 = new Inventory(p2, supplier, 25);

        inv1.displayInventory();
        System.out.println();
        inv2.displayInventory();

        if (inv1.getQuantity() > inv2.getQuantity()) {
            System.out.println("\nLaptop has more stock.");
        } else {
            System.out.println("\nMonitor has more stock.");
        }
    }
}
