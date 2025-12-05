package models;

public abstract class Room {
    private int roomId;
    private String roomName;
    private double pricePerNight;
    private RoomStatus roomStatus = RoomStatus.Available;

    public Room(int roomId, String roomName, double pricePerNight) {
        this.roomId = roomId;
        this.pricePerNight = pricePerNight;
        this.roomName = roomName;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public RoomStatus getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(RoomStatus roomStatus) {
        this.roomStatus = roomStatus;
    }

    public void setPricePerNight(double price) {
        this.pricePerNight = price;
    }
    public double getPricePerNight() {
        return this.pricePerNight;
    }
}
