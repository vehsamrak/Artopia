package artopia.commands;

import artopia.models.User;
import artopia.services.commands.CommandResult;

/**
 * @author Rottenwood
 */
public class ExitCommand extends AbstractCommand {

    @Override
    public CommandResult execute(User user) {

        CommandResult commandResult = new CommandResult("exit", "До встречи!");
        commandResult.addSubCommand("exit");

        return commandResult;
    }
}
