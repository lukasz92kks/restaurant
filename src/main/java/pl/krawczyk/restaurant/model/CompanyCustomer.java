package pl.krawczyk.restaurant.model;

// klasa klienta firmowego
public class CompanyCustomer extends Customer {
    
    private String contactAddress;  // adres korespondencyjny
    private String bankAccount;     // numer konta bankowego
    private String regon;           // REGON

    public CompanyCustomer(String contactAddress, String bankAccount, String regon, String name, String phone, String address, String email, int x, int y) {
        super(name, phone, address, email, x, y);
        this.contactAddress = contactAddress;
        this.bankAccount = bankAccount;
        this.regon = regon;
    }
    
    public CompanyCustomer(String contactAddress, String bankAccount, String regon, String name, String phone, String address, String email, int x, int y, int randomBound) {
        super(name, phone, address, email, x, y, randomBound);
        this.contactAddress = contactAddress;
        this.bankAccount = bankAccount;
        this.regon = regon;
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getRegon() {
        return regon;
    }

    public void setRegon(String regon) {
        this.regon = regon;
    }
}
