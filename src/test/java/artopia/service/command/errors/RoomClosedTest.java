package artopia.service.command.errors;

import org.junit.Assert;
import org.junit.Test;


/**
 * Created by wooferclaw on 23.03.2016.
 */
public class RoomClosedTest extends Assert
{

    @Test
    public void getErrorMessage_nullConstructor_errorMessageWithClosedDoorPhrase() throws Exception
    {
            RoomClosed roomClosed = new RoomClosed(null);

            assertEquals(roomClosed.getErrorMessage(), "Ты не можешь пройти туда. Дверь закрыта.");
    }


    @Test
    public void getErrorMessage_nullConstructor_errorMessageWithOtherPhrase() throws Exception
    {
            RoomClosed roomClosed = new RoomClosed("Тестовая строка");

            assertEquals(roomClosed.getErrorMessage(), "Ты не можешь пройти туда. Тестовая строка.");
    }
}
