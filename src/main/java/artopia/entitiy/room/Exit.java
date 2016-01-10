package artopia.entitiy.room;

/**
 * Выход из комнаты
 * @author Vehsamrak
 */
public class Exit
{
    private String roomId;
    private String doorMessage;
    private boolean closed = false;

    public Exit(String roomId, String doorMessage, boolean closed)
    {
        this.roomId = roomId;
        this.doorMessage = doorMessage;
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

    public String getDoorMessage()
    {
        return doorMessage;
    }

    public boolean isClosed()
    {
        return closed;
    }
}
