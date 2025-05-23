package tourmanagementmvc.controller;

import tourmanagementmvc.db.DatabaseConnection;
import tourmanagementmvc.model.Revenue;
import tourmanagementmvc.view.RevenueView;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RevenueController {
    private RevenueView view;

    public RevenueController(RevenueView view) {
        this.view = view;
        loadRevenueData();
        view.getBackButton().addActionListener(e -> view.dispose());
    }

    private void loadRevenueData() {
        List<Revenue> revenueList = new ArrayList<>();
        String query = """
            SELECT t.name, t.price, b.id
            FROM bookings b
            JOIN tours t ON b.tourId = t.id
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String currentDate = dateFormat.format(new Date());

            while (rs.next()) {
                String tourName = rs.getString("name");
                double price = rs.getDouble("price");
                Revenue revenue = new Revenue(price, currentDate, tourName);
                revenueList.add(revenue);
            }

            view.displayRevenue(revenueList);
        } catch (SQLException e) {
            System.out.println("Lỗi khi tải dữ liệu doanh thu: " + e.getMessage());
            e.printStackTrace();
        }
    }
}