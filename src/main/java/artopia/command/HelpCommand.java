package artopia.command;

import artopia.command.infrastructure.AbstractCommand;
import artopia.entitiy.User;
import artopia.exception.ServiceNotFound;
import artopia.service.command.CommandRepository;
import artopia.service.command.CommandResult;
import artopia.service.locator.Service;
import artopia.service.locator.ServiceLocator;

/**
 * @author Rottenwood
 */
public class HelpCommand extends AbstractCommand
{
    @Override
    public CommandResult execute(String[] arguments, User user, ServiceLocator serviceLocator) throws ServiceNotFound
    {
        CommandRepository commandRepository = (CommandRepository) serviceLocator.get(Service.COMMAND_REPOSITORY);

        return new CommandResult("help", commandRepository.getDescriptions());
    }

    @Override
    public String getDescription()
    {
        return "игровая информация";
    }
}
