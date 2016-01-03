package artopia.commands;

import artopia.commands.infrastructure.AbstractCommand;
import artopia.entities.User;
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

    @Override
    public String getDescription()
    {
        return "информация об авторах проекта";
    }
}
