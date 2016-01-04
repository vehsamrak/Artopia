package artopia.command;

import artopia.command.infrastructure.AbstractCommand;
import artopia.entitiy.User;
import artopia.service.command.CommandRepository;
import artopia.service.command.CommandResult;

/**
 * @author Rottenwood
 */
public class HelpCommand extends AbstractCommand
{
    @Override
    public CommandResult execute(User user) {
        // TODO: 03.01.16 Нужно запрашивать CommandRepository из DI, когда он появится
        return new CommandResult("help", new CommandRepository().getDescriptions());
    }

    @Override
    public String getDescription()
    {
        return "игровая информация";
    }
}
