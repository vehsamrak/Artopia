package artopia.entitiy.room;

/**
 * Выход из комнаты
 * @author Vehsamrak
 */
public class Exit
{
    private String roomId;
    private String doorName;
    private boolean closed = false;

    public Exit(String roomId, String doorName, boolean closed)
    {
        this.roomId = roomId;
        this.doorName = doorName;
        this.closed = closed;
    }

    public Exit(String roomId)
    {
        this.roomId = roomId;
    }

    public String getRoomId()
    {
        return roomId;
    }

    public String getDoorName()
    {
        return doorName;
    }

    public boolean isClosed()
    {
        return closed;
    }
}
