package artopia.command;

import artopia.command.infrastructure.AbstractCommand;
import artopia.entitiy.User;
import artopia.service.commands.CommandResult;

/**
 * @author Rottenwood
 */
public class ExitCommand extends AbstractCommand
{

    @Override
    public CommandResult execute(User user) {
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
