public class Main {
        public static void main(String[] args) {
            Supplier supplier = new Supplier("TechSupply", "info@tech.com");

            Product laptop = new Product(1, "Laptop", 1200) {
                @Override
                public void displayInfo() {
                    System.out.println(this.toString());
                }
            };
            Product monitor = new Product(2, "Monitor", 300) {
                @Override
                public void displayInfo() {
                    System.out.println(this.toString());
                }
            };

            Inventory inv1 = new Inventory(laptop, supplier, 10);
            Inventory inv2 = new Inventory(monitor, supplier, 25);

            Warehouse warehouse = new Warehouse();
            warehouse.addInventory(inv1);
            warehouse.addInventory(inv2);

            System.out.println("=== Full Inventory ===");
            warehouse.displayAll();

            System.out.println("\n=== Search for Laptop ===");
            Inventory found = warehouse.searchByProductName("Laptop");
            if (found != null) found.displayInventory();

            System.out.println("\n=== Sort by Price Descending ===");
            warehouse.sortByPrice(false);
            warehouse.displayAll();
        }
    }
