package services;

import models.Room;
import models.RoomStatus;
import repositories.RoomRepository;

import java.util.List;

public class RoomService {
    private final RoomRepository roomRepo;

    public RoomService(RoomRepository roomRepo) {
        this.roomRepo = roomRepo;
    }

    // Lấy tất cả phòng
    public List<Room> getAllRooms() {
        return roomRepo.getAll();
    }

    public Room findAvailableByType(String typeName) {
        return roomRepo.findAvailableByType(typeName);
    }

    public void updateRoomStatus(int roomId, RoomStatus newStatus) {
        Room r = roomRepo.findById(roomId);
        if (r != null) {
            r.setRoomStatus(newStatus);
            roomRepo.update(r);
        }
    }

    public Room findById(int roomId) {
        return roomRepo.findById(roomId);
    }
}
