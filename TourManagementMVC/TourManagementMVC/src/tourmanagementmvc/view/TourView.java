package tourmanagementmvc.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import tourmanagementmvc.model.Tour;

public class TourView extends JFrame {
    private JTextField txtName, txtPrice, txtLocation, txtDuration, txtStartDate, txtSearch;
    private JTextArea txtDescription;
    private JButton btnAdd, btnEdit, btnDelete, btnSearch;
    private JTable tourTable;
    private DefaultTableModel tableModel;

    public TourView() {
        setTitle("Quản Lý Tour");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Panel nhập liệu
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBorder(BorderFactory.createTitledBorder("Thông tin Tour"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int y = 0;

        gbc.gridx = 0; gbc.gridy = y;
        inputPanel.add(new JLabel("Tên Tour:"), gbc);
        txtName = new JTextField(20);
        gbc.gridx = 1;
        inputPanel.add(txtName, gbc);

        gbc.gridx = 2;
        inputPanel.add(new JLabel("Giá (VNĐ):"), gbc);
        txtPrice = new JTextField(15);
        gbc.gridx = 3;
        inputPanel.add(txtPrice, gbc);

        y++;
        gbc.gridx = 0; gbc.gridy = y;
        inputPanel.add(new JLabel("Địa điểm:"), gbc);
        txtLocation = new JTextField(20);
        gbc.gridx = 1;
        inputPanel.add(txtLocation, gbc);

        gbc.gridx = 2;
        inputPanel.add(new JLabel("Thời gian (ngày):"), gbc);
        txtDuration = new JTextField(15);
        gbc.gridx = 3;
        inputPanel.add(txtDuration, gbc);

        y++;
        gbc.gridx = 0; gbc.gridy = y;
        inputPanel.add(new JLabel("Ngày khởi hành:"), gbc);
        txtStartDate = new JTextField(20);
        gbc.gridx = 1;
        inputPanel.add(txtStartDate, gbc);

        gbc.gridx = 2;
        inputPanel.add(new JLabel("Mô tả:"), gbc);
        txtDescription = new JTextArea(2, 15);
        txtDescription.setLineWrap(true);
        txtDescription.setWrapStyleWord(true);
        JScrollPane descriptionScroll = new JScrollPane(txtDescription);
        gbc.gridx = 3;
        inputPanel.add(descriptionScroll, gbc);

        y++;
        gbc.gridx = 0; gbc.gridy = y; gbc.gridwidth = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        JPanel buttonPanel = new JPanel();
        btnAdd = new JButton("Thêm Tour");
        btnEdit = new JButton("Sửa Tour");
        btnDelete = new JButton("Xóa Tour");
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnDelete);
        inputPanel.add(buttonPanel, gbc);

        // Bảng Tour
        String[] columnNames = {"ID", "Tên Tour", "Giá", "Địa điểm", "Thời gian", "Ngày khởi hành", "Mô tả"};
        tableModel = new DefaultTableModel(columnNames, 0);
        tourTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tourTable);

        tourTable.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = tourTable.getSelectedRow();
            if (selectedRow >= 0) {
                txtName.setText((String) tableModel.getValueAt(selectedRow, 1));
                txtPrice.setText(tableModel.getValueAt(selectedRow, 2).toString());
                txtLocation.setText((String) tableModel.getValueAt(selectedRow, 3));
                txtDuration.setText(tableModel.getValueAt(selectedRow, 4).toString());
                txtStartDate.setText((String) tableModel.getValueAt(selectedRow, 5));
                txtDescription.setText((String) tableModel.getValueAt(selectedRow, 6));
            }
        });

        // Tìm kiếm
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        searchPanel.add(new JLabel("Tìm kiếm:"));
        txtSearch = new JTextField(15);
        btnSearch = new JButton("Tìm");
        searchPanel.add(txtSearch);
        searchPanel.add(btnSearch);

        // Layout chính
        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(searchPanel, BorderLayout.SOUTH);
    }

    public String getTourName() { return txtName.getText(); }
    public String getTourPrice() { return txtPrice.getText(); }
    public String getTourLocation() { return txtLocation.getText(); }
    public String getTourDuration() { return txtDuration.getText(); }
    public String getTourStartDate() { return txtStartDate.getText(); }
    public String getTourDescription() { return txtDescription.getText(); }

    public void clearInput() {
        txtName.setText("");
        txtPrice.setText("");
        txtLocation.setText("");
        txtDuration.setText("");
        txtStartDate.setText("");
        txtDescription.setText("");
    }

    public JButton getBtnAdd() { return btnAdd; }
    public JButton getBtnEdit() { return btnEdit; }
    public JButton getBtnDelete() { return btnDelete; }
    public JButton getBtnSearch() { return btnSearch; }

    public String getSearchText() { return txtSearch.getText(); }

    public int getSelectedTourId() {
        int selectedRow = tourTable.getSelectedRow();
        if (selectedRow >= 0) {
            return (int) tableModel.getValueAt(selectedRow, 0);
        }
        return -1;
    }

    public JTable getTourTable() { return tourTable; }
    public DefaultTableModel getTableModel() { return tableModel; }

    public void updateTourList(List<Tour> tours) {
        tableModel.setRowCount(0); // Xóa dữ liệu cũ
        for (Tour tour : tours) {
            Object[] row = {tour.getId(), tour.getName(), tour.getPrice(), tour.getLocation(), tour.getDuration(), tour.getStartDate(), tour.getDescription()};
            tableModel.addRow(row);
        }
        tableModel.fireTableDataChanged(); // Thông báo dữ liệu thay đổi
        tourTable.revalidate(); // Làm mới bảng
        tourTable.repaint(); // Vẽ lại bảng
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}