package tourmanagementmvc.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import tourmanagementmvc.db.DatabaseConnection;

public class UserModel {
    public boolean validateUser(String username, String password) {
        String query = "SELECT password FROM users WHERE username = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String storedPassword = rs.getString("password");
                    return storedPassword.equals(password);
                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi xác thực người dùng: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public boolean registerUser(String username, String password) {
        System.out.println("Đăng ký - Tên đăng nhập: " + username);
        System.out.println("Đăng ký - Mật khẩu: " + password);

        try {
            if (isUsernameExists(username)) {
                System.out.println("Tên đăng nhập đã tồn tại trong database!");
                return false;
            }

            System.out.println("Tên đăng nhập chưa tồn tại, đang thêm vào database...");
            String query = "INSERT INTO users (username, password) VALUES (?, ?)";
            int retries = 3; // Số lần thử lại
            while (retries > 0) {
                try (Connection conn = DatabaseConnection.getConnection();
                     PreparedStatement stmt = conn.prepareStatement(query)) {
                    stmt.setString(1, username);
                    stmt.setString(2, password);
                    int rows = stmt.executeUpdate();
                    System.out.println("Số hàng được thêm: " + rows);
                    return rows > 0;
                } catch (SQLException e) {
                    System.out.println("Lỗi đăng ký (thử lại " + retries + "): " + e.getMessage());
                    if (e.getMessage().contains("database is locked")) {
                        Thread.sleep(500); // Chờ 500ms trước khi thử lại
                        retries--;
                    } else {
                        e.printStackTrace();
                        return false;
                    }
                }
            }
            System.out.println("Không thể đăng ký sau nhiều lần thử!");
            return false;
        } catch (InterruptedException e) {
            System.out.println("Lỗi chờ khi đăng ký: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    private boolean isUsernameExists(String username) {
        System.out.println("Kiểm tra tên đăng nhập: " + username);
        String query = "SELECT 1 FROM users WHERE username = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                boolean exists = rs.next();
                System.out.println("Tên đăng nhập " + username + " tồn tại: " + exists);
                return exists;
            }
        } catch (SQLException e) {
            System.out.println("Lỗi kiểm tra tên đăng nhập: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}