package artopia.service.command.errors;

/**
 * @author Vehsamrak
 */
public class ArgumentMissing extends AbstractCommandError
{
    private String message;

    public ArgumentMissing(String message)
    {
        this.message = message;
    }

    @Override
    public String getErrorMessage()
    {
        return this.message;
    }
}
