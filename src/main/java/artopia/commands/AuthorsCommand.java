package artopia.commands;

import artopia.commands.infrastructure.AbstractCommand;
import artopia.models.User;
import artopia.services.commands.CommandResult;

/**
 * @author Vehsamrak
 */
public class AuthorsCommand extends AbstractCommand
{
    private User user;
    private String commandName = "authors";

    @Override
    public CommandResult execute(User user)
    {
        this.user = user;
        return new CommandResult(this.commandName, "Автор проекта: Petr Karmashev (Vehsamrak)");
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
