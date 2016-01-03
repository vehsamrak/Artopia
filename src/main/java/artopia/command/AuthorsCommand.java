package artopia.command;

import artopia.command.infrastructure.AbstractCommand;
import artopia.entitiy.User;
import artopia.service.commands.CommandResult;

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
