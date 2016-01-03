package artopia.service.commands.errors;

/**
 * @author Rottenwood
 */
public class InternalError extends AbstractCommandError {

    @Override
    public String getErrorMessage() {
        // TODO: 25.12.15 добавить логирование

        return "Ошибка!";
    }
}
