package artopia.entitiy.room;

import artopia.command.infrastructure.move.Direction;
import artopia.exception.UndefinedDirection;

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
        return this.north;
    }

    public Exit getSouth()
    {
        return this.south;
    }

    public Exit getEast()
    {
        return this.east;
    }

    public Exit getWest()
    {
        return this.west;
    }

    public Exit getUp()
    {
        return this.up;
    }

    public Exit getDown()
    {
        return this.down;
    }

    public Exit getExitByDirection(Direction direction) throws UndefinedDirection
    {
        Exit exit;

        if (direction == null) {
            throw new UndefinedDirection();
        }

        if (direction.equals(Direction.NORTH)) {
            exit = this.getNorth();
        } else if (direction.equals(Direction.EAST)) {
            exit = this.getEast();
        } else if (direction.equals(Direction.SOUTH)) {
            exit = this.getSouth();
        } else if (direction.equals(Direction.WEST)) {
            exit = this.getWest();
        } else if (direction.equals(Direction.UP)) {
            exit = this.getUp();
        } else if (direction.equals(Direction.DOWN)) {
            exit = this.getDown();
        } else {
            throw new UndefinedDirection();
        }

        return exit;
    }
}
