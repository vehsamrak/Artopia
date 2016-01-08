package artopia.entitiy;

/**
 * @author Vehsamrak
 */
public class Room
{
    private String id;
    private String name;
    private String description;
    private String north;
    private String south;
    private String east;
    private String west;
    private String up;
    private String down;

    public Room(
            String id,
            String name,
            String description,
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
        this.description = description;
        this.north = northId;
        this.east = eastId;
        this.south = southId;
        this.west = westId;
        this.up = upId;
        this.down = downId;
    }

    public String getId()
    {
        return this.id;
    }

    public String getName()
    {
        return this.name;
    }

    public String getDescription()
    {
        return this.description;
    }

    public String getNorth()
    {
        return north;
    }

    public String getSouth()
    {
        return south;
    }

    public String getEast()
    {
        return east;
    }

    public String getWest()
    {
        return west;
    }

    public String getUp()
    {
        return up;
    }

    public String getDown()
    {
        return down;
    }
}
