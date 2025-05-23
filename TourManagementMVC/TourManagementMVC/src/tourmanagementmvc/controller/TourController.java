package tourmanagementmvc.controller;

import tourmanagementmvc.model.TourModel;
import tourmanagementmvc.view.TourView;
import java.awt.event.ActionListener;
import java.util.List;
import tourmanagementmvc.model.Tour;

public class TourController {
    private TourModel tourModel;
    private TourView tourView;

    public TourController(TourModel tourModel, TourView tourView) {
        this.tourModel = tourModel;
        this.tourView = tourView;

        // Cập nhật danh sách tour khi khởi tạo
        tourView.updateTourList(tourModel.getTours());

        // Gắn sự kiện: Thêm tour
        tourView.getBtnAdd().addActionListener(e -> {
            String name = tourView.getTourName();
            String priceStr = tourView.getTourPrice();
            String location = tourView.getTourLocation();
            String durationStr = tourView.getTourDuration();
            String startDate = tourView.getTourStartDate();
            String description = tourView.getTourDescription();

            try {
                double price = Double.parseDouble(priceStr);
                int duration = Integer.parseInt(durationStr);
                tourModel.addTour(name, price, location, duration, startDate, description);
                tourView.updateTourList(tourModel.getTours());
                tourView.clearInput();
                tourView.showMessage("Thêm tour thành công!");
            } catch (NumberFormatException ex) {
                tourView.showMessage("Giá và thời gian phải là số!");
            } catch (IllegalArgumentException ex) {
                tourView.showMessage(ex.getMessage());
            } catch (Exception ex) {
                tourView.showMessage("Lỗi không xác định: " + ex.getMessage());
            }
        });

        // Gắn sự kiện: Sửa tour
        tourView.getBtnEdit().addActionListener(e -> {
            int tourId = tourView.getSelectedTourId();
            if (tourId != -1) {
                String name = tourView.getTourName();
                String priceStr = tourView.getTourPrice();
                String location = tourView.getTourLocation();
                String durationStr = tourView.getTourDuration();
                String startDate = tourView.getTourStartDate();
                String description = tourView.getTourDescription();

                try {
                    double price = Double.parseDouble(priceStr);
                    int duration = Integer.parseInt(durationStr);
                    tourModel.editTour(tourId, name, price, location, duration, startDate, description);
                    tourView.updateTourList(tourModel.getTours());
                    tourView.clearInput();
                    tourView.showMessage("Sửa tour thành công!");
                } catch (NumberFormatException ex) {
                    tourView.showMessage("Giá và thời gian phải là số!");
                } catch (IllegalArgumentException ex) {
                    tourView.showMessage(ex.getMessage());
                } catch (Exception ex) {
                    tourView.showMessage("Lỗi không xác định: " + ex.getMessage());
                }
            } else {
                tourView.showMessage("Vui lòng chọn tour để sửa!");
            }
        });

        // Gắn sự kiện: Xóa tour
        tourView.getBtnDelete().addActionListener(e -> {
            int tourId = tourView.getSelectedTourId();
            if (tourId != -1) {
                try {
                    tourModel.deleteTour(tourId);
                    tourView.updateTourList(tourModel.getTours());
                    tourView.clearInput();
                    tourView.showMessage("Xóa tour thành công!");
                } catch (Exception ex) {
                    tourView.showMessage("Lỗi không xác định: " + ex.getMessage());
                }
            } else {
                tourView.showMessage("Vui lòng chọn tour để xóa!");
            }
        });

        // Gắn sự kiện: Tìm kiếm tour
        tourView.getBtnSearch().addActionListener(e -> {
            String searchText = tourView.getSearchText();
            List<Tour> tours = tourModel.getTours();
            List<Tour> filteredTours = new java.util.ArrayList<>();
            for (Tour tour : tours) {
                if (tour.getName().toLowerCase().contains(searchText.toLowerCase()) ||
                    tour.getLocation().toLowerCase().contains(searchText.toLowerCase())) {
                    filteredTours.add(tour);
                }
            }
            tourView.updateTourList(filteredTours);
        });
    }
}