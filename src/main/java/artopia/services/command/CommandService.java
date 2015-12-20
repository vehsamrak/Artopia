package artopia.services.command;

/**
 * @author Rottenwood
 */
public class CommandService {

    public CommandResult execute(String command) {
        CommandResult commandResult = new CommandResult(command);

        return commandResult;
    }
}
