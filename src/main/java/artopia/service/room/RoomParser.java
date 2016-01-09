package artopia.service.room;

import artopia.entitiy.Room;
import artopia.handler.ExceptionHandler;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vehsamrak
 */
public class RoomParser
{
    private ArrayList<Room> roomList = new ArrayList<>();

    public ArrayList<Room> parseRooms(File inputFile)
    {
        try {
            SAXBuilder builder = new SAXBuilder();
            Document document = builder.build(inputFile);
            Element root = document.getRootElement();

            List<Element> rooms = root.getChildren("rooms").get(0).getChildren("room");

            for (Element roomElement : rooms) {
                Element roomExits = roomElement.getChildren("exits").get(0);

                this.roomList.add(
                        new Room(
                                roomElement.getChildText("id"),
                                roomElement.getChildText("name"),
                                roomElement.getChildText("description"),
                                roomExits.getChildText("north"),
                                roomExits.getChildText("east"),
                                roomExits.getChildText("south"),
                                roomExits.getChildText("west"),
                                roomExits.getChildText("up"),
                                roomExits.getChildText("down")
                        )
                );
            }
        } catch (JDOMException | IOException exception) {
            ExceptionHandler.handle(exception);
        }

        return this.roomList;
    }
}
