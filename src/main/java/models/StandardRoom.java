package models;

public class StandardRoom extends Room{
    private String typeName;
    public StandardRoom(int roomId, String roomName, double pricePerNight) {
        super(roomId, roomName, pricePerNight);
        this.typeName = " Standard";
    }

    public String getTypeName() {
        return this.typeName;
    }
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
