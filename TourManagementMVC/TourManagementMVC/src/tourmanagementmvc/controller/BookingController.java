package tourmanagementmvc.controller;

import tourmanagementmvc.model.BookingModel;
import tourmanagementmvc.view.BookingView;

public class BookingController {
    private BookingModel bookingModel;
    private BookingView bookingView;

    public BookingController(BookingModel bookingModel, BookingView bookingView) {
        this.bookingModel = bookingModel;
        this.bookingView = bookingView;

        // Cập nhật danh sách đặt tour khi khởi tạo
        bookingView.updateBookingList(bookingModel.getBookings());

        // Gắn sự kiện: Đặt tour mới
        bookingView.addBookTourListener(e -> {
            try {
                int tourId = bookingView.getTourId();
                String customerId = bookingView.getCustomerId();
                bookingModel.bookTour(tourId, customerId);
                bookingView.updateBookingList(bookingModel.getBookings());
                bookingView.clearFields();
                bookingView.showMessage("Chúc mừng đặt tour!");
            } catch (IllegalArgumentException ex) {
                bookingView.showMessage(ex.getMessage());
            } catch (Exception ex) {
                bookingView.showMessage("Lỗi không xác định: " + ex.getMessage());
            }
        });
    }
}