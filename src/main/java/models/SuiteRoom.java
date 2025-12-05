package models;

public class SuiteRoom extends Room {
    private String typeName;
    public SuiteRoom(int roomId, String roomName, double pricePerNight) {
        super(roomId, roomName, pricePerNight);
        this.typeName = "Suite";
    }

    public String getTypeName() {
        return this.typeName;
    }
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
