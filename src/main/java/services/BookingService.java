package services;

import models.*;
import repositories.BookingRepository;
import repositories.RoomRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class BookingService {

    private final RoomRepository roomRepo;
    private final BookingRepository bookingRepo;

    public BookingService(RoomRepository roomRepo, BookingRepository bookingRepo) {
        this.roomRepo = roomRepo;
        this.bookingRepo = bookingRepo;
    }

    public Booking createBooking(String customerName, String customerPhone, String typeName, LocalDate in, LocalDate out) {
        Room room = roomRepo.findAvailableByType(typeName);
        if (room == null) {
            throw new RuntimeException("No available room for type: " + typeName);
        }

        Customer customer = new Customer(customerName, customerPhone);

        Booking booking = new Booking();
        booking.setCustomer(customer);
        booking.setRoom(room);
        booking.setCheckIn(in);
        booking.setCheckOut(out);
        booking.setStatus(BookingStatus.Booked);

        room.setRoomStatus(RoomStatus.Reserved);
        roomRepo.update(room);

        return bookingRepo.save(booking);
    }

    public List<Booking> getAllBookings() {
        return bookingRepo.getAll();
    }

    public boolean checkIn(int bookingId) {
        Booking b = bookingRepo.findById(bookingId);
        if (b != null && b.getStatus() == BookingStatus.Booked) {
            b.setStatus(BookingStatus.Pending);
            b.getRoom().setRoomStatus(RoomStatus.Occupied);
            roomRepo.update(b.getRoom());
            bookingRepo.update(b);
            return true;
        }
        return false;
    }

    public boolean checkOut(int bookingId) {
        Booking b = bookingRepo.findById(bookingId);
        if (b != null && b.getStatus() == BookingStatus.Pending) {
            b.setStatus(BookingStatus.Booked);
            b.getRoom().setRoomStatus(RoomStatus.Available);
            roomRepo.update(b.getRoom());
            bookingRepo.update(b);
            return true;
        }
        return false;
    }

    public double generateBill(int bookingId) {
        Booking b = bookingRepo.findById(bookingId);
        if (b != null) {
            long nights = ChronoUnit.DAYS.between(b.getCheckIn(), b.getCheckOut());
            return nights * b.getRoom().getPricePerNight();
        }
        return 0;
    }
}