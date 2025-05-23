package tourmanagementmvc.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.net.URL;

public class LoginView extends javax.swing.JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;

    public LoginView() {
        setTitle("Đăng Nhập - Viet Diamond Travel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null); // Căn giữa màn hình
        setResizable(false);

        // Panel chính với bố cục BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(240, 242, 245)); // Màu nền nhạt giống Facebook

        // Panel bên trái (logo và mô tả)
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBackground(new Color(240, 242, 245));
        leftPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        JLabel logoLabel = new JLabel("Viet Diamond Travel");
        logoLabel.setFont(new Font("Arial", Font.BOLD, 40));
        logoLabel.setForeground(new Color(59, 89, 152)); // Màu xanh logo Facebook
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        leftPanel.add(logoLabel, BorderLayout.NORTH);

        JLabel descriptionLabel = new JLabel("<html>Viet Diamond Travel giúp bạn kết nối và chia sẻ<br>với mọi người trong cuộc sống của bạn.</html>");
        descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        descriptionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        leftPanel.add(descriptionLabel, BorderLayout.CENTER);

        mainPanel.add(leftPanel, BorderLayout.WEST);

        // Icon ở giữa
        URL iconUrl = getClass().getResource("/resources/airplane.png"); // Đường dẫn từ root classpath
        if (iconUrl != null) {
            ImageIcon icon = new ImageIcon(iconUrl);
            // Điều chỉnh kích thước icon
            Image img = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(img);
            JLabel iconLabel = new JLabel(scaledIcon);
            iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
            mainPanel.add(iconLabel, BorderLayout.CENTER);
        } else {
            System.err.println("Không tìm thấy file airplane.png tại /resources/airplane.png!");
            System.err.println("Kiểm tra: 1) File có trong src/resources/, 2) Build lại dự án.");
            JLabel errorLabel = new JLabel("Icon không tải được! Kiểm tra src/resources/airplane.png.");
            errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
            mainPanel.add(errorLabel, BorderLayout.CENTER);
        }

        // Panel bên phải (form đăng nhập)
        JPanel loginPanel = new JPanel(new GridBagLayout());
        loginPanel.setBackground(new Color(255, 255, 255));
        loginPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(219, 219, 219)),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        loginPanel.setPreferredSize(new Dimension(350, 200)); // Tăng chiều rộng panel
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL; // Đảm bảo các thành phần mở rộng theo chiều ngang

        // Trường nhập Email
        emailField = new JTextField();
        emailField.setFont(new Font("Arial", Font.PLAIN, 16)); // Tăng kích thước font
        emailField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(10, 10, 10, 10) // Tăng padding
        ));
        emailField.setPreferredSize(new Dimension(300, 40)); // Tăng kích thước trường nhập liệu
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        loginPanel.add(emailField, gbc);

        // Trường nhập Mật khẩu
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.PLAIN, 16)); // Tăng kích thước font
        passwordField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)),
            BorderFactory.createEmptyBorder(10, 10, 10, 10) // Tăng padding
        ));
        passwordField.setPreferredSize(new Dimension(300, 40)); // Tăng kích thước trường nhập liệu
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        loginPanel.add(passwordField, gbc);

        // Nút Đăng nhập
        loginButton = new JButton("Đăng Nhập");
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.setBackground(new Color(59, 89, 152)); // Màu xanh Facebook
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        loginPanel.add(loginButton, gbc);

        // Nút Đăng ký
        registerButton = new JButton("Tạo Tài Khoản Mới");
        registerButton.setFont(new Font("Arial", Font.BOLD, 14));
        registerButton.setBackground(new Color(66, 183, 42)); // Màu xanh lá Facebook
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);
        registerButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        loginPanel.add(registerButton, gbc);

        // Thêm panel đăng nhập vào phía Đông
        mainPanel.add(loginPanel, BorderLayout.EAST);

        add(mainPanel);
    }

    public String getEmail() {
        return emailField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public void addLoginListener(ActionListener listener) {
        loginButton.addActionListener(listener);
    }

    public void addRegisterListener(ActionListener listener) {
        registerButton.addActionListener(listener);
    }
}