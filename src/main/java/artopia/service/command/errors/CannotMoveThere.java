package artopia.service.command.errors;

/**
 * @author Vehsamrak
 */
public class CannotMoveThere extends AbstractCommandError
{
    @Override
    public String getErrorMessage()
    {
        return "Ты не можешь пройти туда.";
    }
}
