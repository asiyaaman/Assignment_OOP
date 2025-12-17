public class Inventory {
        private Product product;
        private Supplier supplier;
        private int quantity;

        public Inventory(Product product, Supplier supplier, int quantity) {
            this.product = product;
            this.supplier = supplier;
            this.quantity = quantity;
        }

        public Product getProduct() {
            return product;
        }

        public Supplier getSupplier() {
            return supplier;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public void displayInventory() {
            product.displayInfo();
            supplier.displayInfo();
            System.out.println("Quantity: " + quantity);
        }
    }
