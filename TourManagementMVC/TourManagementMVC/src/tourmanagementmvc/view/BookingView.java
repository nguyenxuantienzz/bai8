package tourmanagementmvc.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import tourmanagementmvc.model.Booking;

public class BookingView extends JFrame {
    private JTextField tourIdField;
    private JTextField customerIdField;
    private JButton bookTourButton;
    private JTable bookingTable;
    private DefaultTableModel tableModel;

    public BookingView() {
        setTitle("Quản Lý Đặt Tour");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Panel nhập liệu
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBorder(BorderFactory.createTitledBorder("Thông tin Đặt Tour"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int y = 0;

        gbc.gridx = 0; gbc.gridy = y;
        inputPanel.add(new JLabel("Tour ID:"), gbc);
        tourIdField = new JTextField(20);
        gbc.gridx = 1;
        inputPanel.add(tourIdField, gbc);

        y++;
        gbc.gridx = 0; gbc.gridy = y;
        inputPanel.add(new JLabel("Customer ID:"), gbc);
        customerIdField = new JTextField(20);
        gbc.gridx = 1;
        inputPanel.add(customerIdField, gbc);

        y++;
        gbc.gridx = 0; gbc.gridy = y; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        bookTourButton = new JButton("Đặt Tour");
        inputPanel.add(bookTourButton, gbc);

        // Bảng Đặt Tour
        String[] columnNames = {"ID", "Tour ID", "Customer ID"};
        tableModel = new DefaultTableModel(columnNames, 0);
        bookingTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(bookingTable);

        // Layout chính
        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    public int getTourId() {
        try {
            String tourIdText = tourIdField.getText().trim();
            if (tourIdText.isEmpty()) {
                throw new IllegalArgumentException("Tour ID không được để trống!");
            }
            return Integer.parseInt(tourIdText);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Tour ID phải là một số hợp lệ!");
        }
    }

    public String getCustomerId() {
        String customerId = customerIdField.getText().trim();
        if (customerId.isEmpty()) {
            throw new IllegalArgumentException("Customer ID không được để trống!");
        }
        return customerId;
    }

    public void addBookTourListener(ActionListener listener) {
        bookTourButton.addActionListener(listener);
    }

    public void updateBookingList(List<Booking> bookings) {
        tableModel.setRowCount(0);
        for (Booking booking : bookings) {
            Object[] row = {booking.getId(), booking.getTourId(), booking.getCustomerId()};
            tableModel.addRow(row);
        }
        tableModel.fireTableDataChanged();
        bookingTable.revalidate();
        bookingTable.repaint();
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void clearFields() {
        tourIdField.setText("");
        customerIdField.setText("");
    }
}