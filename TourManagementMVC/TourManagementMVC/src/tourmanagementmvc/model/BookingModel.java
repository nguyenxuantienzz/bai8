package tourmanagementmvc.model;

import tourmanagementmvc.db.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingModel {
    public void bookTour(int tourId, String customerId) {
        // Kiểm tra tourId và customerId có tồn tại không
        if (!isTourExists(tourId)) {
            throw new IllegalArgumentException("Tour ID " + tourId + " không tồn tại!");
        }
        if (!isCustomerExists(customerId)) {
            throw new IllegalArgumentException("Customer ID " + customerId + " không tồn tại!");
        }

        String query = "INSERT INTO bookings (tourId, customerId) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, tourId);
            stmt.setString(2, customerId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Không thể thêm đặt tour!");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi đặt tour: " + e.getMessage());
        }
    }

    private boolean isTourExists(int tourId) {
        String query = "SELECT COUNT(*) FROM tours WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, tourId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi kiểm tra tour: " + e.getMessage());
        }
        return false;
    }

    private boolean isCustomerExists(String customerId) {
        String query = "SELECT COUNT(*) FROM customers WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, customerId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi kiểm tra khách hàng: " + e.getMessage());
        }
        return false;
    }

    public List<Booking> getBookings() {
        List<Booking> bookings = new ArrayList<>();
        String query = "SELECT id, tourId, customerId FROM bookings";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                int tourId = rs.getInt("tourId");
                String customerId = rs.getString("customerId");
                bookings.add(new Booking(id, tourId, customerId));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi tải dữ liệu đặt tour: " + e.getMessage());
        }
        return bookings;
    }
}