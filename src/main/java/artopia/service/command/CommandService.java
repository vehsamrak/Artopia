package artopia.service.command;

import artopia.command.infrastructure.AbstractCommand;
import artopia.entitiy.User;
import artopia.exception.ServiceNotFound;
import artopia.service.command.errors.AbstractCommandError;
import artopia.service.command.errors.CommandEmpty;
import artopia.service.command.errors.CommandNotFound;
import artopia.service.locator.AbstractService;
import artopia.service.locator.Service;
import artopia.service.locator.ServiceLocator;

/**
 * @author Rottenwood
 */
public class CommandService extends AbstractService
{

    private User user;
    private final CommandRepository commandRepository;
    private final ServiceLocator serviceLocator;

    public CommandService(ServiceLocator serviceLocator)
    {
        this.serviceLocator = serviceLocator;
        this.commandRepository = new CommandRepository();
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public CommandResult execute(String command) throws ServiceNotFound
    {
        if (command.length() == 0) {
            return this.createCommandResultWithError(command, new CommandEmpty());
        }

        AbstractCommand commandObject = this.commandRepository.findByName(command);

        if (commandObject == null) {
            return this.createCommandResultWithError(command, new CommandNotFound());
        }

        return commandObject.execute(this.user, this.serviceLocator);
    }

    private CommandResult createCommandResultWithError(String command, AbstractCommandError commandError)
    {
        CommandResult commandResult = new CommandResult(command, null);
        commandResult.addError(commandError);

        return commandResult;
    }

    @Override
    public Service getName()
    {
        return Service.COMMAND;
    }
}
