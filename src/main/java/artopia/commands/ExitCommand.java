package artopia.commands;

import artopia.models.User;
import artopia.services.commands.CommandResult;

/**
 * @author Rottenwood
 */
public class ExitCommand extends AbstractCommand {

    private User user;
    private String command = "exit";

    @Override
    public CommandResult execute(User user) {
        this.user = user;

        CommandResult commandResult = new CommandResult(this.command, "До встречи!");
        commandResult.addSubCommand(this.command);

        return commandResult;
    }

    @Override
    public User getUser()
    {
        return this.user;
    }

    @Override
    public String getCommandName()
    {
        return this.command;
    }
}
