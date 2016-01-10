package artopia.service.command.errors;

/**
 * @author Vehsamrak
 */
public class OpenTargetNotFound extends AbstractCommandError
{
    @Override
    public String getErrorMessage()
    {
        return "Ты не нашел того что хотел открыть.";
    }
}
