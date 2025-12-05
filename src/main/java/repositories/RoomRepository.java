package repositories;

import models.Room;
import models.RoomStatus;
import models.StandardRoom;
import models.SuiteRoom;

import java.util.*;

public class RoomRepository {
    private List<Room> rooms = new ArrayList<>();

    public RoomRepository() {
        // Fixed RoomType via inheritance — StandardRoom, SuiteRoom already define typeName
        rooms.add(new StandardRoom(101, "Standard Room 101", 100));
        rooms.add(new StandardRoom(102, "Standard Room 102", 100));
        rooms.add(new SuiteRoom(201, "Suite Room 201", 200));
    }

    public List<Room> getAll() {
        return rooms;
    }

    public Room findById(int roomId) {
        for (Room r : rooms) {
            if (r.getRoomId() == roomId) return r;
        }
        return null;
    }

    public Room findAvailableByType(String typeName) {
        for (Room r : rooms) {
            boolean matches = false;

            if (r instanceof StandardRoom && typeName.equalsIgnoreCase("Standard")) {
                matches = true;
            }
            if (r instanceof SuiteRoom && typeName.equalsIgnoreCase("Suite")) {
                matches = true;
            }

            if (matches && r.getRoomStatus() == RoomStatus.Available) {
                return r;
            }
        }
        return null;
    }


    public void update(Room room) {
        // no DB, room is already updated in memory
    }
}
