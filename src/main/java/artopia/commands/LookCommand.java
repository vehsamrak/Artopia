package artopia.commands;

import artopia.commands.infrastructure.AbstractCommand;
import artopia.models.User;
import artopia.services.commands.CommandResult;

/**
 * @author Rottenwood
 */
public class LookCommand extends AbstractCommand
{

    @Override
    public CommandResult execute(User user)
    {
        String commandResult = "Ты осмотрелся.";

        return new CommandResult("look", commandResult);
    }

    @Override
    public String getDescription()
    {
        return "посмотреть вокруг";
    }
}
