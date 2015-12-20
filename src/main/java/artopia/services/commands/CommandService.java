package artopia.services.commands;

import artopia.services.commands.errors.CommandNotFound;

/**
 * @author Rottenwood
 */
public class CommandService {

    public CommandResult execute(String command) {
        CommandResult commandResult = new CommandResult(command);

        String commandClassName = command.toLowerCase();
        commandClassName = "artopia.commands."
                + Character.toString(commandClassName.charAt(0)).toUpperCase()
                + commandClassName.substring(1)
                + "Command";

        try {
            Class.forName(commandClassName);
        } catch (ClassNotFoundException exception) {
            commandResult.addError(new CommandNotFound());
        }

        return commandResult;
    }
}
