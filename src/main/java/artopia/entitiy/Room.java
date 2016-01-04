package artopia.entitiy;

/**
 * @author Vehsamrak
 */
public class Room
{
    private String id;
    private String name;
    private String desciption;
    private Room north;
    private Room south;
    private Room east;
    private Room west;
    private Room up;
    private Room down;

    public Room(String id, String name, String desciption)
    {
        this.id = id;
        this.name = name;
        this.desciption = desciption;
    }

    public Room getNorth()
    {
        return north;
    }

    public void setNorth(Room room)
    {
        this.north = room;
    }

    public Room getSouth()
    {
        return south;
    }

    public void setSouth(Room room)
    {
        this.south = room;
    }

    public Room getEast()
    {
        return east;
    }

    public void setEast(Room room)
    {
        this.east = room;
    }

    public Room getWest()
    {
        return west;
    }

    public void setWest(Room room)
    {
        this.west = room;
    }

    public Room getUp()
    {
        return up;
    }

    public void setUp(Room room)
    {
        this.up = room;
    }

    public Room getDown()
    {
        return down;
    }

    public void setDown(Room room)
    {
        this.down = room;
    }
}
