package artopia.services.commands.errors;

/**
 * @author Rottenwood
 */
public class CommandEmpty extends AbstractCommandError {

    @Override
    public String getErrorMessage() {
        return "Введена пустая команда.";
    }
}
