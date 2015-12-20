package artopia.services.commands;

import artopia.services.commands.errors.CommandNotFound;

/**
 * @author Rottenwood
 */
public class CommandService {

    public CommandResult execute(String command) {
        CommandResult commandResult = new CommandResult(command);

        try {
            Class.forName(command);
        } catch (ClassNotFoundException exception) {
            commandResult.addError(new CommandNotFound());
        }

        return commandResult;
    }
}
