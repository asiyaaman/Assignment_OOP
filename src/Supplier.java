public class Supplier {
    private String companyName;
    private String email;

    public Supplier(String companyName, String email) {
        this.companyName = companyName;
        this.email = email;
    }
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void displayInfo() {
        System.out.println("Supplier: " + companyName +
                ", Email: " + email);
    }
}
