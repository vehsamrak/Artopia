package artopia.command;

import artopia.command.infrastructure.AbstractCommand;
import artopia.entitiy.User;
import artopia.exception.ServiceNotFound;
import artopia.service.command.CommandResult;
import artopia.service.locator.ServiceLocator;

/**
 * @author Rottenwood
 */
public class QuitCommand extends AbstractCommand
{

    @Override
    public CommandResult execute(String[] arguments, User user, ServiceLocator serviceLocator) throws ServiceNotFound
    {
        CommandResult commandResult = new CommandResult("exit", "До встречи!");
        commandResult.addSubCommand("exit");

        return commandResult;
    }

    @Override
    public String getDescription()
    {
        return "выход из игры";
    }
}
