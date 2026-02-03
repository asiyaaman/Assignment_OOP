public class Supplier extends Person {

    public Supplier(String name, String email) {
        super(name, email);
    }

    @Override
    public void displayInfo() {
        System.out.println("Supplier: " + name + ", Email: " + email);
    }

    public String getCompanyName() {
        return name;
    }
}