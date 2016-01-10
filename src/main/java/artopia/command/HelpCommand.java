package artopia.command;

import artopia.command.infrastructure.AbstractCommand;
import artopia.entitiy.User;
import artopia.service.command.CommandRepository;
import artopia.service.command.CommandResult;
import artopia.service.locator.ServiceLocator;

/**
 * @author Rottenwood
 */
public class HelpCommand extends AbstractCommand
{
    @Override
    public CommandResult execute(String[] arguments, User user, ServiceLocator serviceLocator) {
        // TODO: 03.01.16 Нужно запрашивать CommandRepository из DI, когда он появится
        return new CommandResult("help", new CommandRepository().getDescriptions());
    }

    @Override
    public String getDescription()
    {
        return "игровая информация";
    }
}
