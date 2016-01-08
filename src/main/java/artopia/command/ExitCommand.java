package artopia.command;

import artopia.command.infrastructure.AbstractCommand;
import artopia.entitiy.User;
import artopia.exception.ServiceNotFound;
import artopia.service.DatabaseService;
import artopia.service.command.CommandResult;
import artopia.service.locator.Service;
import artopia.service.locator.ServiceLocator;
import org.hibernate.Session;

/**
 * @author Rottenwood
 */
public class ExitCommand extends AbstractCommand
{

    @Override
    public CommandResult execute(User user, ServiceLocator serviceLocator) throws ServiceNotFound
    {
        CommandResult commandResult = new CommandResult("exit", "До встречи!");
        commandResult.addSubCommand("exit");

        DatabaseService databaseService = (DatabaseService) serviceLocator.get(Service.DATABASE);
        Session session = databaseService.openSession();
        session.update(user);

        session.close();

        return commandResult;
    }

    @Override
    public String getDescription()
    {
        return "выход из игры";
    }
}
