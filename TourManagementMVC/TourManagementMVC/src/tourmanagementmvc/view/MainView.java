package tourmanagementmvc.view;

import tourmanagementmvc.controller.TourController;
import tourmanagementmvc.model.TourModel;
import javax.swing.*;
import java.awt.*;

public class MainView extends javax.swing.JFrame {
    
    private JLabel welcomeLabel;
    private JMenuBar menuBar;
    private JMenu menuTour;
    private JMenuItem menuItemQuanLyTour;

    public MainView() {
        setTitle("Hệ Thống Quản Lý Tour Du Lịch - Viet Diamond Travel");
        setSize(800, 600); // Kích thước phù hợp với giao diện đẹp
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Thanh menu
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(245, 245, 250)); // Màu xám nhạt
        setJMenuBar(menuBar);

        // Menu Quản Lý Tour
        JMenu tourMenu = new JMenu("Quản Lý Tour");
        tourMenu.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JMenuItem addTourItem = new JMenuItem("Thêm Tour Mới");
        JMenuItem viewTourItem = new JMenuItem("Xem Danh Sách Tour");
        tourMenu.add(addTourItem);
        tourMenu.add(viewTourItem);
        menuBar.add(tourMenu);

        // Menu Quản Lý Khách Hàng
        JMenu customerMenu = new JMenu("Quản Lý Khách Hàng");
        customerMenu.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JMenuItem addCustomerItem = new JMenuItem("Thêm Khách Hàng");
        JMenuItem viewCustomerItem = new JMenuItem("Xem Danh Sách Khách");
        customerMenu.add(addCustomerItem);
        customerMenu.add(viewCustomerItem);
        menuBar.add(customerMenu);

        // Menu Quản Lý Đặt Tour
        JMenu bookingMenu = new JMenu("Quản Lý Đặt Tour");
        bookingMenu.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JMenuItem addBookingItem = new JMenuItem("Thêm Đặt Tour");
        JMenuItem viewBookingItem = new JMenuItem("Xem Danh Sách Đặt Tour");
        bookingMenu.add(addBookingItem);
        bookingMenu.add(viewBookingItem);
        menuBar.add(bookingMenu);

        // Menu Báo Cáo
        JMenu reportMenu = new JMenu("Báo Cáo");
        reportMenu.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JMenuItem revenueReportItem = new JMenuItem("Báo Cáo Doanh Thu");
        reportMenu.add(revenueReportItem);
        menuBar.add(reportMenu);

        // Menu Tài Khoản
        JMenu accountMenu = new JMenu("Tài Khoản");
        accountMenu.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JMenuItem changePasswordItem = new JMenuItem("Đổi Mật Khẩu");
        JMenuItem logoutItem = new JMenuItem("Đăng Xuất");
        accountMenu.add(changePasswordItem);
        accountMenu.add(logoutItem);
        menuBar.add(accountMenu);

        // Panel chính với hình nền
        JPanel mainPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Giả sử bạn có file background.jpg trong src/resources/
                Image backgroundImage = new ImageIcon("src/resources/background.jpg").getImage();
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        mainPanel.setOpaque(false);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Thêm logo
        JLabel logoLabel = new JLabel(new ImageIcon("src/resources/logo.png"));
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        logoLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        mainPanel.add(logoLabel, BorderLayout.NORTH);

        // Panel chào mừng với hiệu ứng bóng
        JPanel welcomePanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(new Color(0, 0, 0, 0.1f));
                g2d.fillRoundRect(5, 5, getWidth() - 10, getHeight() - 10, 15, 15);
            }
        };
        welcomePanel.setBackground(new Color(255, 255, 255, 230)); // Trắng mờ
        welcomePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        welcomeLabel = new JLabel("Chào mừng đến với hệ thống quản lý tour!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        welcomeLabel.setForeground(new Color(33, 150, 243)); // Màu xanh đồng bộ
        welcomePanel.add(welcomeLabel, BorderLayout.CENTER);

        mainPanel.add(welcomePanel, BorderLayout.CENTER);
        add(mainPanel);

        // Gắn sự kiện cho menu
        addTourItem.addActionListener(e -> {
            TourView tourView = new TourView();
            new tourmanagementmvc.controller.TourController(new tourmanagementmvc.model.TourModel(), tourView);
            tourView.setVisible(true);
        });

        viewTourItem.addActionListener(e -> {
            TourView tourView = new TourView();
            new tourmanagementmvc.controller.TourController(new tourmanagementmvc.model.TourModel(), tourView);
            tourView.setVisible(true);
        });

        addCustomerItem.addActionListener(e -> {
            CustomerView customerView = new CustomerView();
            new tourmanagementmvc.controller.CustomerController(new tourmanagementmvc.model.CustomerModel(), customerView);
            customerView.setVisible(true);
        });

        viewCustomerItem.addActionListener(e -> {
            CustomerView customerView = new CustomerView();
            new tourmanagementmvc.controller.CustomerController(new tourmanagementmvc.model.CustomerModel(), customerView);
            customerView.setVisible(true);
        });

        addBookingItem.addActionListener(e -> {
            BookingView bookingView = new BookingView();
            new tourmanagementmvc.controller.BookingController(new tourmanagementmvc.model.BookingModel(), bookingView);
            bookingView.setVisible(true);
        });

        viewBookingItem.addActionListener(e -> {
            BookingView bookingView = new BookingView();
            new tourmanagementmvc.controller.BookingController(new tourmanagementmvc.model.BookingModel(), bookingView);
            bookingView.setVisible(true);
        });

        revenueReportItem.addActionListener(e -> {
            RevenueView revenueView = new RevenueView();
            new tourmanagementmvc.controller.RevenueController(revenueView);
            revenueView.setVisible(true);
        });

        changePasswordItem.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Chức năng Đổi Mật Khẩu đang được phát triển!");
        });

        logoutItem.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có muốn đăng xuất?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                dispose();
                new LoginView().setVisible(true);
            }
        });
    }

    public void setWelcomeMessage(String message) {
        welcomeLabel.setText(message);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainView mainView = new MainView();
            mainView.setVisible(true);
        });
    }
}