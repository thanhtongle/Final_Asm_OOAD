package services;

import models.Bill;
import models.Booking;
import models.BookingStatus;
import models.RoomStatus;
import repositories.BillRepository;
import repositories.BookingRepository;

public class CheckOutService {
    private BookingRepository bookingRepo;
    private BillRepository billRepo;

    public CheckOutService(BookingRepository bookingRepo, BillRepository billRepo) {
        this.bookingRepo = bookingRepo;
        this.billRepo = billRepo;
    }

    public Bill checkOut(int bookingId) {
        Booking booking = bookingRepo.findById(bookingId);

        if (booking == null || booking.getRoom().getRoomStatus() != RoomStatus.Occupied)
            return null;

        long nights = booking.getNights();
        double price = booking.getRoom().getPricePerNight();
        double total = nights * price;

        Bill bill = new Bill();
        bill.setTotalAmount(total);
        bill.setGeneratedDate(java.sql.Date.valueOf(java.time.LocalDate.now()));

        billRepo.save(bill);

        booking.setBill(bill);
        booking.setStatus(BookingStatus.Booked);
        booking.getRoom().setRoomStatus(RoomStatus.Available);

        return bill;
    }
}
