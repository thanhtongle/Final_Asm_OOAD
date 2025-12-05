import models.Booking;
import models.Room;
import services.BookingService;
import services.RoomService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MainMenu {

    private final RoomService roomService;
    private final BookingService bookingService;
    private final Scanner scanner = new Scanner(System.in);

    public MainMenu(RoomService roomService, BookingService bookingService) {
        this.roomService = roomService;
        this.bookingService = bookingService;
    }

    public void start() {
        int choice;
        do {
            System.out.println("\n==== HOTEL BOOKING SYSTEM ====");
            System.out.println("1. View All Rooms");
            System.out.println("2. View Available Rooms By Type");
            System.out.println("3. Create Booking");
            System.out.println("4. View All Bookings");
            System.out.println("5. Check-In Booking");
            System.out.println("6. Check-Out Booking");
            System.out.println("7. Generate Bill");
            System.out.println("0. Exit");
            System.out.print("Choose: ");

            choice = readInt();

            switch (choice) {
                case 1 -> viewAllRooms();
                case 2 -> viewAvailableByType();
                case 3 -> createBookingUI();
                case 4 -> viewAllBookings();
                case 5 -> checkInBookingUI();
                case 6 -> checkOutBookingUI();
                case 7 -> generateBillUI();
                case 0 -> System.out.println("Exiting... Goodbye!");
                default -> System.out.println("Invalid option. Try again.");
            }

        } while (choice != 0);
    }

    // -------------------------------------------------------------------------
    // MENU ACTIONS
    // -------------------------------------------------------------------------

    private void viewAllRooms() {
        System.out.println("\n--- All Rooms ---");
        List<Room> rooms = roomService.getAllRooms();
        for (Room r : rooms) {
            System.out.println(r.getRoomId() + " | " + r.getRoomName() + " | " + r.getRoomStatus());
        }
    }

    private void viewAvailableByType() {
        System.out.print("Enter room type (Standard/Suite): ");
        String type = scanner.nextLine();

        Room room = roomService.findAvailableByType(type);
        if (room == null) {
            System.out.println("No available rooms for type: " + type);
        } else {
            System.out.println("Available Room Found: " + room.getRoomId() + " | " + room.getRoomName());
        }
    }

    private void viewAllBookings() {
        System.out.println("\n--- All Bookings ---");
        List<Booking> bookings = bookingService.getAllBookings();
        if (bookings.isEmpty()) {
            System.out.println("No bookings yet.");
        } else {
            for (Booking b : bookings) {
                System.out.println("Booking ID: " + b.getId() +
                        " | Customer: " + b.getCustomer().getName() +
                        " | Room: " + b.getRoom().getRoomName() +
                        " | Status: " + b.getStatus());
            }
        }
    }

    private void createBookingUI() {
        System.out.print("Customer Name: ");
        String name = scanner.nextLine();

        System.out.print("Customer Phone: ");
        String phone = scanner.nextLine();

        System.out.print("Room Type (Standard/Suite): ");
        String type = scanner.nextLine();

        System.out.print("Check-in date (YYYY-MM-DD): ");
        LocalDate checkIn = LocalDate.parse(scanner.nextLine());

        System.out.print("Check-out date (YYYY-MM-DD): ");
        LocalDate checkOut = LocalDate.parse(scanner.nextLine());

        try {
            Booking booking = bookingService.createBooking(name, phone, type, checkIn, checkOut);
            System.out.println("Booking created successfully! ID: " + booking.getId());
        } catch (Exception e) {
            System.out.println("Failed to create booking: " + e.getMessage());
        }
    }

    private void checkInBookingUI() {
        System.out.print("Enter Booking ID to Check-In: ");
        int id = readInt();
        boolean result = bookingService.checkIn(id);
        if (result) {
            System.out.println("Check-In successful!");
        } else {
            System.out.println("Check-In failed. Booking may not exist or already checked-in.");
        }
    }

    private void checkOutBookingUI() {
        System.out.print("Enter Booking ID to Check-Out: ");
        int id = readInt();
        boolean result = bookingService.checkOut(id);
        if (result) {
            System.out.println("Check-Out successful!");
        } else {
            System.out.println("Check-Out failed. Booking may not exist or not checked-in.");
        }
    }

    private void generateBillUI() {
        System.out.print("Enter Booking ID to generate Bill: ");
        int id = readInt();
        double total = bookingService.generateBill(id);
        if (total > 0) {
            System.out.println("Total Bill: $" + total);
        } else {
            System.out.println("Cannot generate bill. Booking may not exist.");
        }
    }
    private int readInt() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.print("Enter a valid number: ");
            }
        }
    }
}