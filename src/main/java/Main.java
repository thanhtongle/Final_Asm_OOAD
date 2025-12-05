import repositories.BookingRepository;
import repositories.RoomRepository;
import services.BookingService;
import services.RoomService;

public class Main {
    public static void main(String[] args) {
        RoomRepository roomRepo = new RoomRepository();
        BookingRepository bookingRepo = new BookingRepository();

        RoomService roomService = new RoomService(roomRepo);
        BookingService bookingService = new BookingService(roomRepo, bookingRepo);

        MainMenu menu = new MainMenu(roomService, bookingService);
        menu.start();
    }
}