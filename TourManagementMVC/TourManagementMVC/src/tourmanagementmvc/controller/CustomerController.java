package tourmanagementmvc.controller;

import java.util.List;
import javax.swing.JOptionPane;
import tourmanagementmvc.model.Customer;
import tourmanagementmvc.model.CustomerModel;
import tourmanagementmvc.view.CustomerView;

public class CustomerController {
    private CustomerModel customerModel;
    private CustomerView customerView;

    public CustomerController(CustomerModel customerModel, CustomerView customerView) {
        this.customerModel = customerModel;
        this.customerView = customerView;

        // Cập nhật danh sách khách hàng khi khởi tạo
        customerView.updateCustomerList(customerModel.getCustomers());

        // Gắn sự kiện: Thêm khách hàng mới
        customerView.addAddCustomerListener(e -> {
            String name = customerView.getCustomerName();
            String phone = customerView.getPhone();
            String address = customerView.getAddress();
            String email = customerView.getEmail();

            try {
                customerModel.addCustomer(name, phone, address, email);
                customerView.updateCustomerList(customerModel.getCustomers());
                customerView.clearFields();
                customerView.showMessage("Thêm khách hàng thành công!");
            } catch (IllegalArgumentException ex) {
                customerView.showMessage(ex.getMessage());
            } catch (Exception ex) {
                customerView.showMessage("Lỗi không xác định: " + ex.getMessage());
            }
        });

        // Gắn sự kiện: Sửa khách hàng
        customerView.addEditCustomerListener(e -> {
            int selectedIndex = customerView.getSelectedCustomerIndex();
            if (selectedIndex != -1) {
                String name = customerView.getCustomerName();
                String phone = customerView.getPhone();
                String address = customerView.getAddress();
                String email = customerView.getEmail();
                try {
                    customerModel.editCustomer(selectedIndex, name, phone, address, email);
                    customerView.updateCustomerList(customerModel.getCustomers());
                    customerView.clearFields();
                    customerView.showMessage("Sửa khách hàng thành công!");
                } catch (IllegalArgumentException ex) {
                    customerView.showMessage(ex.getMessage());
                } catch (Exception ex) {
                    customerView.showMessage("Lỗi không xác định: " + ex.getMessage());
                }
            } else {
                customerView.showMessage("Vui lòng chọn khách hàng để sửa!");
            }
        });

        // Gắn sự kiện: Xóa khách hàng
        customerView.addDeleteCustomerListener(e -> {
            int selectedIndex = customerView.getSelectedCustomerIndex();
            if (selectedIndex != -1) {
                try {
                    customerModel.deleteCustomer(selectedIndex);
                    customerView.updateCustomerList(customerModel.getCustomers());
                    customerView.clearFields();
                    customerView.showMessage("Xóa khách hàng thành công!");
                } catch (Exception ex) {
                    customerView.showMessage("Lỗi không xác định: " + ex.getMessage());
                }
            } else {
                customerView.showMessage("Vui lòng chọn khách hàng để xóa!");
            }
        });

        // Gắn sự kiện: Tìm kiếm khách hàng
        customerView.addSearchListener(e -> {
            String searchText = customerView.getSearchText();
            List<Customer> customers = customerModel.getCustomers();
            List<Customer> filteredCustomers = new java.util.ArrayList<>();
            for (Customer customer : customers) {
                if (customer.getName().toLowerCase().contains(searchText.toLowerCase()) ||
                    customer.getPhone().toLowerCase().contains(searchText.toLowerCase())) {
                    filteredCustomers.add(customer);
                }
            }
            customerView.updateCustomerList(filteredCustomers);
        });
    }
}