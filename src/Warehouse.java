import java.util.*;
import java.util.stream.Collectors;

    public class Warehouse {
        private List<Inventory> inventoryList = new ArrayList<>();

        public void addInventory(Inventory inv) {
            inventoryList.add(inv);
        }

        // Display all inventory
        public void displayAll() {
            inventoryList.forEach(Inventory::displayInventory);
        }

        // Search by product name
        public Inventory searchByProductName(String name) {
            return inventoryList.stream()
                    .filter(inv -> inv.getProduct().getName().equalsIgnoreCase(name))
                    .findFirst()
                    .orElse(null);
        }

        // Filter by quantity greater than threshold
        public List<Inventory> filterByQuantity(int minQty) {
            return inventoryList.stream()
                    .filter(inv -> inv.getQuantity() >= minQty)
                    .collect(Collectors.toList());
        }

        // Sort by product price
        public void sortByPrice(boolean ascending) {
            inventoryList.sort(Comparator.comparingDouble(inv -> inv.getProduct().getPrice()));
            if (!ascending) Collections.reverse(inventoryList);
        }
    }
