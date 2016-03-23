package artopia.service.command.errors;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by wooferclaw on 23.03.2016.
 */
public class RoomClosedTest {

    private String doorMessage;

    @Test
    public void testGetErrorMessageDoorClosed() throws Exception {
        if (doorMessage == null) {
            doorMessage = "Дверь закрыта";
            RoomClosed roomClosed = new RoomClosed(doorMessage);
            assertEquals(roomClosed.getErrorMessage(), "Ты не можешь пройти туда. " + this.doorMessage + ".");
        }
    }

    @Test
    public void testGetErrorMessageCharacter() throws Exception {
        if (doorMessage != null) {
            doorMessage = Character.toString(doorMessage.charAt(0)).toUpperCase() + doorMessage.substring(1);
            RoomClosed roomClosed = new RoomClosed(doorMessage);
            assertEquals(roomClosed.getErrorMessage(), "Ты не можешь пройти туда. " + this.doorMessage + ".");
        }
    }
}