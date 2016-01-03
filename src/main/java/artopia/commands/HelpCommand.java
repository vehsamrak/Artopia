package artopia.commands;

import artopia.commands.infrastructure.AbstractCommand;
import artopia.entities.User;
import artopia.services.commands.CommandRepository;
import artopia.services.commands.CommandResult;

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
