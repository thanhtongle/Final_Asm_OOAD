package services;

import models.Booking;
import models.BookingStatus;
import models.RoomStatus;
import repositories.BookingRepository;

public class CheckInService {
    private BookingRepository bookingRepo;

    public CheckInService(BookingRepository bookingRepo) {
        this.bookingRepo = bookingRepo;
    }

    public boolean checkIn(int bookingId) {
        Booking b = bookingRepo.findById(bookingId);

        if (b == null || b.getStatus() != BookingStatus.Booked) {
            return false;
        }

        b.setStatus(BookingStatus.Pending);
        b.getRoom().setRoomStatus(RoomStatus.Occupied);

        bookingRepo.save(b);
        return true;
    }
}
