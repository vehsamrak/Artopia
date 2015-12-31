package artopia.commands;

import artopia.models.User;
import artopia.services.commands.CommandResult;

/**
 * @author Rottenwood
 */
public class LookCommand extends AbstractCommand
{

    private User user;
    private String commandName = "look";

    @Override
    public CommandResult execute(User user)
    {
        this.user = user;
        String commandResult = "Ты осмотрелся.";

        return new CommandResult(this.commandName, commandResult);
    }

    @Override
    public User getUser()
    {
        return this.user;
    }

    @Override
    public String getCommandName()
    {
        return this.commandName;
    }
}
