package artopia.commands;

import artopia.models.User;
import artopia.services.commands.CommandResult;

/**
 * @author Rottenwood
 */
public class HelpCommand extends AbstractCommand {

    @Override
    public CommandResult execute(User user) {
        String commandResult = "" +
                "Доступные команды:\n" +
                "help - игровая информация\n" +
                "look - посмотреть вокруг";

        return new CommandResult("help", commandResult);
    }
}
