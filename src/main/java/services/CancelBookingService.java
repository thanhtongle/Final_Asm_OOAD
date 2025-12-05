package services;

import models.Booking;
import models.BookingStatus;
import models.RoomStatus;
import repositories.BookingRepository;

public class CancelBookingService {
    private final BookingRepository bookingRepo;

    public CancelBookingService(BookingRepository bookingRepo) {
        this.bookingRepo = bookingRepo;
    }

    public boolean cancelBooking(int id) {
        Booking b = bookingRepo.findById(id);

        if (b == null) return false;

        b.setStatus(BookingStatus.Cancelled);
        b.getRoom().setRoomStatus(RoomStatus.Available);

        bookingRepo.save(b);
        return true;
    }
}
