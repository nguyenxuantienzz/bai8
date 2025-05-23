package tourmanagementmvc.model;

public class Revenue {
    private double totalRevenue;
    private String date;
    private String tourName;

    public Revenue(double totalRevenue, String date, String tourName) {
        this.totalRevenue = totalRevenue;
        this.date = date;
        this.tourName = tourName;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }
}