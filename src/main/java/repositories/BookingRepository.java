package repositories;

import models.Booking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingRepository {
    private Map<Integer, Booking> bookings = new HashMap<>();
    private int idCounter = 1;

    public Booking save(Booking booking) {
        if (booking.getId() == 0) {
            booking.setId(idCounter++);
        }
        bookings.put(booking.getId(), booking);
        return booking;
    }

    public Booking findById(int id) {
        return bookings.get(id);
    }

    public void update(Booking booking) {
        bookings.put(booking.getId(), booking);
    }

    public List<Booking> getAll() {
        return new ArrayList<>(bookings.values());
    }
}
