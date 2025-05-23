package tourmanagementmvc;

import tourmanagementmvc.db.DBInitializer;
import tourmanagementmvc.model.UserModel;
import tourmanagementmvc.view.LoginView;
import tourmanagementmvc.controller.LoginController;

public class Main {
    public static void main(String[] args) {
        DBInitializer.initializeDatabase();
        UserModel userModel = new UserModel();
        LoginView loginView = new LoginView();
        new LoginController(userModel, loginView);
        loginView.setVisible(true);
    }
}