package artopia.entitiy.room;

/**
 * @author Vehsamrak
 */
public class Room
{
    private String id;
    private String name;
    private String description;
    private Exit north;
    private Exit south;
    private Exit east;
    private Exit west;
    private Exit up;
    private Exit down;

    public Room(
            String id,
            String name,
            String description,
            Exit northExit,
            Exit eastExit,
            Exit southExit,
            Exit westExit,
            Exit upExit,
            Exit downExit
    )
    {
        this.id = id;
        this.name = name;
        this.description = description;
        this.north = northExit;
        this.east = eastExit;
        this.south = southExit;
        this.west = westExit;
        this.up = upExit;
        this.down = downExit;
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

    public Exit getNorth()
    {
        return north;
    }

    public Exit getSouth()
    {
        return south;
    }

    public Exit getEast()
    {
        return east;
    }

    public Exit getWest()
    {
        return west;
    }

    public Exit getUp()
    {
        return up;
    }

    public Exit getDown()
    {
        return down;
    }
}
