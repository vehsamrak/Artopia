package artopia.entitiy;

/**
 * @author Vehsamrak
 */
public class Room
{
    private String id;
    private String name;
    private String desciption;
    private String north;
    private String south;
    private String east;
    private String west;
    private String up;
    private String down;

    public Room(String id, String name, String desciption)
    {
        this.id = id;
        this.name = name;
        this.desciption = desciption;
    }

    public Room(
            String id,
            String name,
            String desciption,
            String northId,
            String eastId,
            String southId,
            String westId,
            String upId,
            String downId
    )
    {
        this.id = id;
        this.name = name;
        this.desciption = desciption;
        this.north = northId;
        this.east = eastId;
        this.south = southId;
        this.west = westId;
        this.up = upId;
        this.down = downId;
    }

    public String getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getDesciption()
    {
        return desciption;
    }

    public String getNorth()
    {
        return north;
    }

    public void setNorth(String roomId)
    {
        this.north = roomId;
    }

    public String getSouth()
    {
        return south;
    }

    public void setSouth(String roomId)
    {
        this.south = roomId;
    }

    public String getEast()
    {
        return east;
    }

    public void setEast(String roomId)
    {
        this.east = roomId;
    }

    public String getWest()
    {
        return west;
    }

    public void setWest(String roomId)
    {
        this.west = roomId;
    }

    public String getUp()
    {
        return up;
    }

    public void setUp(String roomId)
    {
        this.up = roomId;
    }

    public String getDown()
    {
        return down;
    }

    public void setDown(String roomId)
    {
        this.down = roomId;
    }
}
