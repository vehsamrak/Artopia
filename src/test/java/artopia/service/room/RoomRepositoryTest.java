package artopia.service.room;

import artopia.entitiy.room.Room;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Vehsamrak
 */
public class RoomRepositoryTest extends Assert
{

    @Test
    public void findById_repositoryWithoutAskedRoomId_returnsNull() throws Exception
    {
        RoomRepository roomRepository = this.createRoomRepository();
        Room unexistingRoom = roomRepository.findById("test-test-test-test");

        assertNull(unexistingRoom);
    }

    @Test
    public void findById_repositoryWithAskedRoomId_returnsRoom()
    {
        RoomRepository roomRepository = this.createRoomRepository();
        Room testRoom1 = roomRepository.findById("system-start");

        assertNotNull(testRoom1);
    }

    private RoomRepository createRoomRepository()
    {
        RoomParser roomParser = new RoomParser();
        return new RoomRepository(roomParser);
    }
}
