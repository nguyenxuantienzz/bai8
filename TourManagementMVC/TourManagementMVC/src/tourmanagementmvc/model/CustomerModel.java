package tourmanagementmvc.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tourmanagementmvc.db.DatabaseConnection;

public class CustomerModel {
    public void addCustomer(String name, String phone, String address, String email) {
        if (name == null || name.trim().isEmpty() || phone == null || phone.trim().isEmpty()) {
            throw new IllegalArgumentException("Tên và số điện thoại không được để trống");
        }

        String id = generateCustomerId();
        String query = "INSERT INTO customers (id, name, phone, address, email) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, id);
            stmt.setString(2, name);
            stmt.setString(3, phone);
            stmt.setString(4, address);
            stmt.setString(5, email);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi thêm khách hàng: " + e.getMessage());
        }
    }

    public void editCustomer(int index, String name, String phone, String address, String email) {
        if (name == null || name.trim().isEmpty() || phone == null || phone.trim().isEmpty()) {
            throw new IllegalArgumentException("Tên và số điện thoại không được để trống");
        }

        List<Customer> customers = getCustomers();
        if (index >= 0 && index < customers.size()) {
            String id = customers.get(index).getId();
            String query = "UPDATE customers SET name = ?, phone = ?, address = ?, email = ? WHERE id = ?";
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, name);
                stmt.setString(2, phone);
                stmt.setString(3, address);
                stmt.setString(4, email);
                stmt.setString(5, id);
                stmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException("Lỗi sửa khách hàng: " + e.getMessage());
            }
        } else {
            throw new IllegalArgumentException("Chỉ số khách hàng không hợp lệ");
        }
    }

    public void deleteCustomer(int index) {
        List<Customer> customers = getCustomers();
        if (index >= 0 && index < customers.size()) {
            String id = customers.get(index).getId();
            String query = "DELETE FROM customers WHERE id = ?";
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, id);
                stmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException("Lỗi xóa khách hàng: " + e.getMessage());
            }
        } else {
            throw new IllegalArgumentException("Chỉ số khách hàng không hợp lệ");
        }
    }

    private String generateCustomerId() {
        String query = "SELECT COUNT(*) FROM customers";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                int count = rs.getInt(1);
                return "C" + (count + 1);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi tạo ID khách hàng: " + e.getMessage());
        }
        return "C1"; // Mặc định nếu có lỗi
    }

    public List<Customer> getCustomers() {
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT id, name, phone, address, email FROM customers";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                String address = rs.getString("address");
                String email = rs.getString("email");
                customers.add(new Customer(id, name, phone, address, email));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi tải dữ liệu khách hàng: " + e.getMessage());
        }
        return customers;
    }
}