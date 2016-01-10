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
                                roomElement.getChildTextNormalize("id"),
                                roomElement.getChildTextNormalize("name"),
                                roomElement.getChildTextNormalize("description"),
                                roomExits.getChildTextNormalize("north"),
                                roomExits.getChildTextNormalize("east"),
                                roomExits.getChildTextNormalize("south"),
                                roomExits.getChildTextNormalize("west"),
                                roomExits.getChildTextNormalize("up"),
                                roomExits.getChildTextNormalize("down")
                        )
                );
            }
        } catch (JDOMException | IOException exception) {
            ExceptionHandler.handle(exception);
        }

        return this.roomList;
    }
}
