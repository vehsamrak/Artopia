package artopia.service.command.errors;

/**
 * @author Vehsamrak
 */
public class RoomClosed extends AbstractCommandError
{
    private String doorMessage;

    public RoomClosed(String doorMessage)
    {
        this.doorMessage = Character.toString(doorMessage.charAt(0)).toUpperCase() + doorMessage.substring(1);
    }

    @Override
    public String getErrorMessage()
    {
        return "Ты не можешь пройти туда. " + this.doorMessage + ".";
    }
}
