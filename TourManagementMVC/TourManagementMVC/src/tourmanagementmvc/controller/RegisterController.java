package tourmanagementmvc.controller;

import javax.swing.JOptionPane;
import tourmanagementmvc.controller.LoginController;
import tourmanagementmvc.model.UserModel;
import tourmanagementmvc.view.LoginView;
import tourmanagementmvc.view.RegisterView;

public class RegisterController {
    private UserModel userModel;
    private RegisterView registerView;

    public RegisterController(UserModel userModel, RegisterView registerView) {
        this.userModel = userModel;
        this.registerView = registerView;

        // Gắn sự kiện cho nút đăng ký
        registerView.addRegisterListener(e -> {
            String username = registerView.getUsername();
            String password = registerView.getPassword();
            String confirmPassword = registerView.getConfirmPassword();

            if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                JOptionPane.showMessageDialog(registerView, "Vui lòng nhập đầy đủ thông tin!");
                return;
            }

            if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(registerView, "Mật khẩu và mật khẩu nhập lại không khớp!");
                return;
            }

            if (userModel.registerUser(username, password)) {
                JOptionPane.showMessageDialog(registerView, "Đăng ký thành công! Vui lòng đăng nhập.");
                registerView.clearFields();
                registerView.dispose();
                LoginView loginView = new LoginView();
                new LoginController(new UserModel(), loginView);
                loginView.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(registerView, "Tên đăng nhập đã tồn tại!");
            }
        });

        // Gắn sự kiện cho nút quay lại
        registerView.addBackListener(e -> {
            registerView.dispose();
            LoginView loginView = new LoginView();
            new LoginController(new UserModel(), loginView);
            loginView.setVisible(true);
        });
    }
}