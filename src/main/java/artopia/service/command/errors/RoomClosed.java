package artopia.service.command.errors;

/**
 * @author Vehsamrak
 */
public class RoomClosed extends AbstractCommandError
{
    private String doorName;

    public RoomClosed(String doorName)
    {
        this.doorName = doorName;
    }

    @Override
    public String getErrorMessage()
    {
        return "Ты не можешь пройти туда. " + this.doorName + " - закрыто.";
    }
}
