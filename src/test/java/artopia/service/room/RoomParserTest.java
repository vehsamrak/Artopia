package artopia.service.room;

import artopia.entitiy.room.Room;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

/**
 * @author Vehsamrak
 */
public class RoomParserTest extends Assert
{

    @Test
    public void getRooms() throws Exception
    {
        RoomParser roomParser = new RoomParser();
        File zoneFile = new File("src/test/resources/zones/test.xml");
        ArrayList<Room> roomList = roomParser.parseRooms(zoneFile);

        assertEquals(2, roomList.size());
    }
}
