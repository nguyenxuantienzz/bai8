package tourmanagementmvc.view;

import tourmanagementmvc.model.Revenue;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class RevenueView extends JFrame {
    private JTable revenueTable;
    private DefaultTableModel tableModel;
    private JButton backButton;

    public RevenueView() {
        setTitle("Báo Cáo Doanh Thu - Viet Diamond Travel");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Panel chính
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setBackground(new Color(245, 245, 250));

        // Tiêu đề
        JLabel titleLabel = new JLabel("Báo Cáo Doanh Thu", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setForeground(new Color(33, 150, 243));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Bảng hiển thị doanh thu
        String[] columnNames = {"Tên Tour", "Ngày", "Tổng Doanh Thu"};
        tableModel = new DefaultTableModel(columnNames, 0);
        revenueTable = new JTable(tableModel);
        revenueTable.setRowHeight(25);
        revenueTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        revenueTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        revenueTable.getTableHeader().setBackground(new Color(33, 150, 243));
        revenueTable.getTableHeader().setForeground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(revenueTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Panel nút
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(245, 245, 250));
        backButton = new JButton("Quay Lại");
        backButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        backButton.setBackground(new Color(33, 150, 243));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        buttonPanel.add(backButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    // Phương thức để hiển thị danh sách doanh thu
    public void displayRevenue(List<Revenue> revenueList) {
        tableModel.setRowCount(0); // Xóa dữ liệu cũ
        for (Revenue revenue : revenueList) {
            Object[] row = {
                revenue.getTourName(),
                revenue.getDate(),
                String.format("%,.0f VNĐ", revenue.getTotalRevenue())
            };
            tableModel.addRow(row);
        }
    }

    // Getter cho nút Quay Lại
    public JButton getBackButton() {
        return backButton;
    }
}