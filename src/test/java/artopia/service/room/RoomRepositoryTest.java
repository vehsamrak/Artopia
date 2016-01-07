package artopia.service.room;

import artopia.entitiy.Room;
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
        RoomRepository roomRepository = new RoomRepository();
        Room unexistingRoom = roomRepository.findById("test-test-test-test");

        assertNull(unexistingRoom);
    }

    @Test
    public void findById_repositoryWithAskedRoomId_returnsRoom()
    {
        RoomRepository roomRepository = new RoomRepository();
        Room testRoom1 = roomRepository.findById("system-start");

        assertNotNull(testRoom1);
    }
}
