package tourmanagementmvc.model;

public class Tour {
    private int id;
    private String name;
    private double price;
    private String location;
    private int duration;
    private String startDate;
    private String description;

    public Tour(int id, String name, double price, String location, int duration, String startDate, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.location = location;
        this.duration = duration;
        this.startDate = startDate;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getLocation() {
        return location;
    }

    public int getDuration() {
        return duration;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Tour [ID=" + id + ", Name=" + name + ", Price=" + price + ", Location=" + location +
               ", Duration=" + duration + ", StartDate=" + startDate + ", Description=" + description + "]";
    }
}