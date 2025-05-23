package tourmanagementmvc.model;

public class Booking {
    private int id;
    private int tourId;
    private String customerId;

    public Booking(int id, int tourId, String customerId) {
        this.id = id;
        this.tourId = tourId;
        this.customerId = customerId;
    }

    public int getId() {
        return id;
    }

    public int getTourId() {
        return tourId;
    }

    public String getCustomerId() {
        return customerId;
    }

    @Override
    public String toString() {
        return "Booking [ID=" + id + ", TourID=" + tourId + ", CustomerID=" + customerId + "]";
    }
}