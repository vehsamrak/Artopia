package artopia.service.commands;

import artopia.command.infrastructure.AbstractCommand;
import artopia.entitiy.User;
import artopia.service.commands.errors.AbstractCommandError;
import artopia.service.commands.errors.CommandEmpty;
import artopia.service.commands.errors.CommandNotFound;

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
        if (command.length() == 0) {
            return this.createCommandResultWithError(command, new CommandEmpty());
        }

        AbstractCommand commandObject = this.commandRepository.findByName(command);

        if (commandObject == null) {
            return this.createCommandResultWithError(command, new CommandNotFound());
        }

        return commandObject.execute(this.user);
    }

    private CommandResult createCommandResultWithError(String command, AbstractCommandError commandError)
    {
        CommandResult commandResult = new CommandResult(command, null);
        commandResult.addError(commandError);

        return commandResult;
    }
}
