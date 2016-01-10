package artopia.service.room;

import artopia.command.infrastructure.move.Direction;
import artopia.entitiy.room.Exit;
import artopia.entitiy.room.Room;
import artopia.handler.ExceptionHandler;
import org.jdom2.Attribute;
import org.jdom2.DataConversionException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
                HashMap<Direction, Exit> exits = this.createExits(roomExits);

                this.roomList.add(
                        new Room(
                                roomElement.getChildTextNormalize("id"),
                                roomElement.getChildTextNormalize("name"),
                                roomElement.getChildTextNormalize("description"),
                                exits.get(Direction.NORTH),
                                exits.get(Direction.EAST),
                                exits.get(Direction.SOUTH),
                                exits.get(Direction.WEST),
                                exits.get(Direction.UP),
                                exits.get(Direction.DOWN)
                        )
                );
            }
        } catch (JDOMException | IOException exception) {
            ExceptionHandler.handle(exception);
        }

        return this.roomList;
    }

    private HashMap<Direction, Exit> createExits(Element roomExits) throws DataConversionException
    {
        HashMap<Direction, Exit> exitMap = new HashMap<>();

        for (Direction direction : Direction.values()) {
            Element exitElement = roomExits.getChild(direction.toString());

            if (exitElement == null) {
                continue;
            }

            if (exitElement.getChild("room") == null) {
                String exitRoomId = exitElement.getTextNormalize();
                exitMap.put(direction, new Exit(exitRoomId));
            } else {
                Element doorElement = exitElement.getChild("door");
                String exitRoomId = exitElement.getChildTextNormalize("room");

                if (doorElement == null) {
                    exitMap.put(direction, new Exit(exitRoomId));
                } else {
                    String doorName = exitElement.getChildTextNormalize("door");

                    Attribute doorClosedAttribute = doorElement.getAttribute("closed");

                    boolean isClosed = true;
                    if (doorClosedAttribute != null) {
                        isClosed = doorClosedAttribute.getBooleanValue();
                    }

                    exitMap.put(direction, new Exit(exitRoomId, doorName, isClosed));
                }
            }
        }

        return exitMap;
    }
}
