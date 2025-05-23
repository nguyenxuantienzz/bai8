package tourmanagementmvc.controller;

import javax.swing.JOptionPane;
import tourmanagementmvc.model.UserModel;
import tourmanagementmvc.view.LoginView;
import tourmanagementmvc.view.RegisterView;

public class LoginController {
    private UserModel userModel;
    private LoginView loginView;

    public LoginController(UserModel userModel, LoginView loginView) {
        this.userModel = userModel;
        this.loginView = loginView;

        // Gắn sự kiện cho nút đăng nhập
        loginView.addLoginListener(e -> {
            String email = loginView.getEmail(); // Thay getUsername() bằng getEmail()
            String password = loginView.getPassword();

            if (email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(loginView, "Vui lòng nhập đầy đủ thông tin!");
                return;
            }

            if (userModel.validateUser(email, password)) { // Đảm bảo UserModel hỗ trợ email
                JOptionPane.showMessageDialog(loginView, "Đăng nhập thành công!");
                loginView.dispose(); // Đóng cửa sổ đăng nhập
                tourmanagementmvc.view.MainView mainView = new tourmanagementmvc.view.MainView();
                mainView.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(loginView, "Email hoặc mật khẩu không đúng!");
            }
        });

        // Gắn sự kiện cho nút đăng ký mới
        loginView.addRegisterListener(e -> {
            loginView.dispose(); // Đóng giao diện đăng nhập
            RegisterView registerView = new RegisterView();
            new RegisterController(new UserModel(), registerView);
            registerView.setVisible(true);
        });
    }
}