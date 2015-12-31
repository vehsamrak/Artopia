package artopia.services.commands;

import artopia.commands.infrastructure.AbstractCommand;
import artopia.handlers.ExceptionHandler;
import artopia.models.User;
import artopia.services.commands.errors.AbstractCommandError;
import artopia.services.commands.errors.CommandEmpty;
import artopia.services.commands.errors.CommandNotFound;
import artopia.services.commands.errors.InternalError;

/**
 * @author Rottenwood
 */
public class CommandService
{

    private final User user;
    private final CommandRepository commandRepository;

    public CommandService(User user)
    {
        this.user = user;
        this.commandRepository = new CommandRepository();
    }

    public CommandResult execute(String command)
    {
        CommandResult commandResult;

        if (command.length() == 0) {
            return this.createCommandResultWithError(command, new CommandEmpty());
        }

        AbstractCommand commandObject = this.commandRepository.findByName(command);

        if (commandObject == null) {
            return this.createCommandResultWithError(command, new CommandNotFound());
        }

        try {
            commandResult = commandObject.execute(this.user);
        } catch (Exception exception) {
            ExceptionHandler.handle(exception);

            commandResult = this.createCommandResultWithError(command, new InternalError());
        }

        return commandResult;
    }

    private CommandResult createCommandResultWithError(String command, AbstractCommandError commandError)
    {
        CommandResult commandResult = new CommandResult(command, null);
        commandResult.addError(commandError);

        return commandResult;
    }
}
