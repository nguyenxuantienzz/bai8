package tourmanagementmvc.model;

import tourmanagementmvc.db.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TourModel {
    public void addTour(String name, double price, String location, int duration, String startDate, String description) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Tên tour không được để trống");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("Giá phải lớn hơn 0");
        }
        if (duration <= 0) {
            throw new IllegalArgumentException("Thời gian phải lớn hơn 0");
        }

        String query = "INSERT INTO tours (name, price, location, duration, start_date, description) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setDouble(2, price);
            stmt.setString(3, location);
            stmt.setInt(4, duration);
            stmt.setString(5, startDate);
            stmt.setString(6, description);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Không có hàng nào được thêm vào bảng tours");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi thêm tour: " + e.getMessage());
        }
    }

    public void editTour(int id, String name, double price, String location, int duration, String startDate, String description) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Tên tour không được để trống");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("Giá phải lớn hơn 0");
        }
        if (duration <= 0) {
            throw new IllegalArgumentException("Thời gian phải lớn hơn 0");
        }

        String query = "UPDATE tours SET name = ?, price = ?, location = ?, duration = ?, start_date = ?, description = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setDouble(2, price);
            stmt.setString(3, location);
            stmt.setInt(4, duration);
            stmt.setString(5, startDate);
            stmt.setString(6, description);
            stmt.setInt(7, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Không tìm thấy tour với ID: " + id);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi sửa tour: " + e.getMessage());
        }
    }

    public void deleteTour(int id) {
        String query = "DELETE FROM tours WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("Không tìm thấy tour với ID: " + id);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi xóa tour: " + e.getMessage());
        }
    }

    public List<Tour> getTours() {
        List<Tour> tours = new ArrayList<>();
        String query = "SELECT id, name, price, location, duration, start_date, description FROM tours";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                String location = rs.getString("location");
                int duration = rs.getInt("duration");
                String startDate = rs.getString("start_date");
                String description = rs.getString("description");
                tours.add(new Tour(id, name, price, location, duration, startDate, description));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi tải dữ liệu tour: " + e.getMessage());
        }
        return tours;
    }
}