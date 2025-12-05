package models;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Booking {
    private int id;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private BookingStatus status;
    private Room room;
    private Customer customer;
    private Bill bill;

    public Booking() {}

    public Booking(int id, LocalDate in, LocalDate out, BookingStatus status, Customer c, Room r, Bill b) {
        this.id = id;
        this.customer = c;
        this.room = r;
        this.checkIn = in;
        this.checkOut = out;
        this.status = status;
        this.bill = b;
    }

    public long getNights() {
        return ChronoUnit.DAYS.between(this.checkIn, this.checkOut);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }
}
