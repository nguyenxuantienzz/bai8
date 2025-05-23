package tourmanagementmvc.db;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class DBInitializer {
    public static void initializeDatabase() {
        String createUsersTable = """
            CREATE TABLE IF NOT EXISTS users (
                username TEXT PRIMARY KEY,
                password TEXT NOT NULL
            )
        """;

        String createToursTable = """
            CREATE TABLE IF NOT EXISTS tours (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL,
                price REAL NOT NULL,
                location TEXT,
                duration INTEGER,
                start_date TEXT,
                description TEXT
            )
        """;

        String createCustomersTable = """
            CREATE TABLE IF NOT EXISTS customers (
                id TEXT PRIMARY KEY,
                name TEXT NOT NULL,
                phone TEXT NOT NULL,
                address TEXT,
                email TEXT
            )
        """;

        String createBookingsTable = """
            CREATE TABLE IF NOT EXISTS bookings (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                tourId INTEGER,
                customerId TEXT,
                FOREIGN KEY (tourId) REFERENCES tours(id) ON DELETE SET NULL,
                FOREIGN KEY (customerId) REFERENCES customers(id) ON DELETE SET NULL
            )
        """;

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(createUsersTable);
            stmt.execute(createToursTable);
            stmt.execute(createCustomersTable);
            stmt.execute(createBookingsTable);
            System.out.println("Tạo bảng thành công! - " + new java.util.Date().toString());
        } catch (SQLException e) {
            System.out.println("Lỗi tạo bảng: " + e.getMessage());
            e.printStackTrace();
        }
    }
}