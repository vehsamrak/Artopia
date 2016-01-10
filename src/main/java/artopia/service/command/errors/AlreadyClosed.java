package artopia.service.command.errors;

/**
 * @author Vehsamrak
 */
public class AlreadyClosed extends AbstractCommandError
{
    @Override
    public String getErrorMessage()
    {
        return "Уже закрыто.";
    }
}
