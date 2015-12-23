package artopia.commands;

import artopia.models.User;
import artopia.services.commands.CommandResult;

/**
 * @author Rottenwood
 */
public class HelpCommand extends AbstractCommand {
    private final String commandName = "help";
    private User user;

    @Override
    public CommandResult execute(User user) {
        this.user = user;
        return new CommandResult(this.commandName);
    }
}
