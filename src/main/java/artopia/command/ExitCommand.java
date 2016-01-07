package artopia.command;

import artopia.command.infrastructure.AbstractCommand;
import artopia.entitiy.User;
import artopia.service.DatabaseService;
import artopia.service.command.CommandResult;
import org.hibernate.Session;

/**
 * @author Rottenwood
 */
public class ExitCommand extends AbstractCommand
{

    @Override
    public CommandResult execute(User user, DatabaseService databaseService) {
        CommandResult commandResult = new CommandResult("exit", "До встречи!");
        commandResult.addSubCommand("exit");

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
