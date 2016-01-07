package artopia.service.command;

import artopia.command.infrastructure.AbstractCommand;
import artopia.entitiy.User;
import artopia.service.DatabaseService;
import artopia.service.command.errors.AbstractCommandError;
import artopia.service.command.errors.CommandEmpty;
import artopia.service.command.errors.CommandNotFound;

/**
 * @author Rottenwood
 */
public class CommandService
{

    private final User user;
    private final DatabaseService databaseService;
    private final CommandRepository commandRepository;

    public CommandService(User user, DatabaseService databaseService)
    {
        this.user = user;
        this.databaseService = databaseService;
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

        return commandObject.execute(this.user, this.databaseService);
    }

    private CommandResult createCommandResultWithError(String command, AbstractCommandError commandError)
    {
        CommandResult commandResult = new CommandResult(command, null);
        commandResult.addError(commandError);

        return commandResult;
    }
}
