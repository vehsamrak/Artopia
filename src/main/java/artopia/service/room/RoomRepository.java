package artopia.service.room;

import artopia.entitiy.Room;
import artopia.service.locator.AbstractService;
import artopia.service.locator.Service;

import java.util.ArrayList;

/**
 * @author Vehsamrak
 */
public class RoomRepository extends AbstractService
{
    final private ArrayList<Room> roomList = new ArrayList<>();

    /**
     * Список всех комнат
     * TODO: 04.01.16 Должно быть вынесено в отдельный конфиг
     */
    public RoomRepository()
    {
        this.roomList.add(new Room(
                "system-start",
                "Центр Мира",
                "Ты находишься в самом центре мира.",
                "system-north",
                "system-east",
                "system-south",
                "system-east",
                "system-up",
                "system-down"
        ));

        this.roomList.add(new Room(
                "system-north",
                "Северный Полюс",
                "Холодные пустоши смертоносного льда.",
                null,
                null,
                "system-start",
                null,
                null,
                null
        ));

        this.roomList.add(new Room(
                "system-east",
                "Восточный Край",
                "Жаркие пустыни востока.",
                null,
                null,
                null,
                "system-start",
                null,
                null
        ));

        this.roomList.add(new Room(
                "system-south",
                "Южный Полюс",
                "Снежный буран приближается со всех сторон.",
                "system-start",
                null,
                null,
                null,
                null,
                null
        ));

        this.roomList.add(new Room(
                "system-west",
                "Западный Край",
                "Ветренные прерии западного края.",
                null,
                "system-start",
                null,
                null,
                null,
                null
        ));
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

    @Override
    public Service getName()
    {
        return Service.ROOM_REPOSITORY;
    }
}
