package artopia.services.commands;

import artopia.commands.infrastructure.AbstractCommand;
import artopia.handlers.ExceptionHandler;
import artopia.models.User;
import artopia.services.commands.errors.AbstractCommandError;
import artopia.services.commands.errors.CommandNotFound;
import artopia.services.commands.errors.CommandEmpty;
import artopia.services.commands.errors.InternalError;

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

        if (command.length() == 0) {
            commandResult = this.createCommandResultWithError(command, new CommandEmpty());

            return commandResult;
        }

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

                commandResult = this.createCommandResultWithError(command, new InternalError());
            }
        }

        return commandResult;
    }

    private CommandResult createCommandResultWithError(String command, AbstractCommandError commandError) {
        CommandResult commandResult = new CommandResult(command, null);
        commandResult.addError(commandError);

        return commandResult;
    }
}
