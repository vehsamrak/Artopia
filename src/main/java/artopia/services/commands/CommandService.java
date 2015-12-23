package artopia.services.commands;

import artopia.commands.AbstractCommand;
import artopia.handlers.ExceptionHandler;
import artopia.models.User;
import artopia.services.commands.errors.AbstractCommandError;
import artopia.services.commands.errors.CommandNotFound;
import artopia.services.commands.errors.Internal;

/**
 * @author Rottenwood
 */
public class CommandService {

    private final User user;

    public CommandService(User user) {
        this.user = user;
    }

    public CommandResult execute(String command) {
        CommandResult commandResult;
        String commandInLowercase = command.toLowerCase();
        String commandClassName = "artopia.commands."
                + Character.toString(commandInLowercase.charAt(0)).toUpperCase()
                + commandInLowercase.substring(1)
                + "Command";

        try {
            AbstractCommand commandObject = (AbstractCommand) Class.forName(commandClassName).newInstance();

            commandResult = commandObject.execute(this.user);

        } catch (Exception exception) {
            if (exception instanceof ClassNotFoundException) {
                commandResult = this.createCommandResultWithError(command, new CommandNotFound());
            } else {
                ExceptionHandler.handle(exception);

                commandResult = this.createCommandResultWithError(command, new Internal());
            }
        }

        return commandResult;
    }

    private CommandResult createCommandResultWithError(String command, AbstractCommandError commandError) {
        CommandResult commandResult = new CommandResult(command);
        commandResult.addError(commandError);

        return commandResult;
    }
}
