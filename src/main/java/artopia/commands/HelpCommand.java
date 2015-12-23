package artopia.commands;

import artopia.models.User;
import artopia.services.commands.CommandResult;

/**
 * @author Rottenwood
 */
public class HelpCommand extends AbstractCommand {
    private final String commandName = "help";

    @Override
    public CommandResult execute(User user) {
        String commandResult = "" +
                "Доступные команды:\n" +
                "help - игровая инфорация\n" +
                "look - посмотреть вокруг\n";

        return new CommandResult(this.commandName, commandResult);
    }
}
