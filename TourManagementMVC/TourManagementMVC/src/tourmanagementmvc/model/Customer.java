package tourmanagementmvc.model;

public class Customer {
    private String id;
    private String name;
    private String phone;
    private String address;
    private String email;

    public Customer(String id, String name, String phone, String address, String email) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Customer [ID=" + id + ", Name=" + name + ", Phone=" + phone + ", Address=" + address + ", Email=" + email + "]";
    }
}