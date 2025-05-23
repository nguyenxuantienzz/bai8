package tourmanagementmvc.view;

import tourmanagementmvc.model.Customer;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class CustomerView extends JFrame {
    private JTextField customerNameField;
    private JTextField phoneField;
    private JTextField addressField;
    private JTextField emailField;
    private JButton addCustomerButton;
    private JButton editCustomerButton;
    private JButton deleteCustomerButton;
    private JButton searchButton;
    private JTextField searchField;
    private JTable customerTable;
    private DefaultTableModel tableModel;

    public CustomerView() {
        setTitle("Quản Lý Khách Hàng");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Panel nhập liệu
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBorder(BorderFactory.createTitledBorder("Thông tin Khách Hàng"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int y = 0;

        gbc.gridx = 0; gbc.gridy = y;
        inputPanel.add(new JLabel("Tên Khách Hàng:"), gbc);
        customerNameField = new JTextField(20);
        gbc.gridx = 1;
        inputPanel.add(customerNameField, gbc);

        gbc.gridx = 2;
        inputPanel.add(new JLabel("Số Điện Thoại:"), gbc);
        phoneField = new JTextField(15);
        gbc.gridx = 3;
        inputPanel.add(phoneField, gbc);

        y++;
        gbc.gridx = 0; gbc.gridy = y;
        inputPanel.add(new JLabel("Địa Chỉ:"), gbc);
        addressField = new JTextField(20);
        gbc.gridx = 1;
        inputPanel.add(addressField, gbc);

        gbc.gridx = 2;
        inputPanel.add(new JLabel("Email:"), gbc);
        emailField = new JTextField(15);
        gbc.gridx = 3;
        inputPanel.add(emailField, gbc);

        y++;
        gbc.gridx = 0; gbc.gridy = y; gbc.gridwidth = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        JPanel buttonPanel = new JPanel();
        addCustomerButton = new JButton("Thêm Khách Hàng");
        editCustomerButton = new JButton("Sửa Khách Hàng");
        deleteCustomerButton = new JButton("Xóa Khách Hàng");
        buttonPanel.add(addCustomerButton);
        buttonPanel.add(editCustomerButton);
        buttonPanel.add(deleteCustomerButton);
        inputPanel.add(buttonPanel, gbc);

        // Bảng Khách Hàng
        String[] columnNames = {"ID", "Tên Khách Hàng", "Số Điện Thoại", "Địa Chỉ", "Email"};
        tableModel = new DefaultTableModel(columnNames, 0);
        customerTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(customerTable);

        customerTable.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = customerTable.getSelectedRow();
            if (selectedRow >= 0) {
                customerNameField.setText((String) tableModel.getValueAt(selectedRow, 1));
                phoneField.setText((String) tableModel.getValueAt(selectedRow, 2));
                addressField.setText((String) tableModel.getValueAt(selectedRow, 3));
                emailField.setText((String) tableModel.getValueAt(selectedRow, 4));
            }
        });

        // Tìm kiếm
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        searchPanel.add(new JLabel("Tìm kiếm:"));
        searchField = new JTextField(15);
        searchButton = new JButton("Tìm");
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        // Layout chính
        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(searchPanel, BorderLayout.SOUTH);
    }

    public String getCustomerName() {
        return customerNameField.getText();
    }

    public String getPhone() {
        return phoneField.getText();
    }

    public String getAddress() {
        return addressField.getText();
    }

    public String getEmail() {
        return emailField.getText();
    }

    public void addAddCustomerListener(ActionListener listener) {
        addCustomerButton.addActionListener(listener);
    }

    public void addEditCustomerListener(ActionListener listener) {
        editCustomerButton.addActionListener(listener);
    }

    public void addDeleteCustomerListener(ActionListener listener) {
        deleteCustomerButton.addActionListener(listener);
    }

    public void addSearchListener(ActionListener listener) {
        searchButton.addActionListener(listener);
    }

    public void updateCustomerList(List<Customer> customers) {
        tableModel.setRowCount(0);
        for (Customer customer : customers) {
            Object[] row = {customer.getId(), customer.getName(), customer.getPhone(), customer.getAddress(), customer.getEmail()};
            tableModel.addRow(row);
        }
    }

    public int getSelectedCustomerIndex() {
        return customerTable.getSelectedRow();
    }

    public String getSearchText() {
        return searchField.getText();
    }

    public void clearFields() {
        customerNameField.setText("");
        phoneField.setText("");
        addressField.setText("");
        emailField.setText("");
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}