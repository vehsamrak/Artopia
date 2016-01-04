package artopia.service.room;

import artopia.entitiy.Room;

import java.util.ArrayList;

/**
 * @author Vehsamrak
 */
public class RoomRepository
{
    final private ArrayList<Room> roomList = new ArrayList<>();

    /**
     * Список всех комнат
     * TODO: 04.01.16 Должно быть вынесено в отдельный конфиг
     */
    public RoomRepository()
    {
        this.roomList.add(new Room("test-room-1", "test-room-1", "test-room-1"));
        this.roomList.add(new Room("test-room-2", "test-room-2", "test-room-2"));
        this.roomList.add(new Room("test-room-3", "test-room-3", "test-room-3"));
    }

    /**
     * @param id Идентификатор комнаты
     * @return Комната найденная по идентификатору, или null если комната не найдена
     */
    public Room findById(String id)
    {
        id = id.toLowerCase();

        for (Room room : this.roomList) {
            if (room.getId().equals(id)) {
                return room;
            }
        }

        return null;
    }
}
