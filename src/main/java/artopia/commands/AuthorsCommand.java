package artopia.commands;

import artopia.commands.infrastructure.AbstractCommand;
import artopia.models.User;
import artopia.services.commands.CommandResult;

/**
 * @author Vehsamrak
 */
public class AuthorsCommand extends AbstractCommand
{

    @Override
    public CommandResult execute(User user)
    {
        return new CommandResult("authors", "Автор проекта: Petr Karmashev (Vehsamrak)");
    }
}
