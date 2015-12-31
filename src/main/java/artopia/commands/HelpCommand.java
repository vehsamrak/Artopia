package artopia.commands;

import artopia.models.User;
import artopia.services.commands.CommandResult;

/**
 * @author Rottenwood
 */
public class HelpCommand extends AbstractCommand {

    private User user;
    private String commandName = "help";

    @Override
    public CommandResult execute(User user) {
        this.user = user;

        String commandResult = "" +
                "Доступные команды:\n" +
                "help - игровая информация\n" +
                "look - посмотреть вокруг\n" +
                "exit - выход из игры";

        return new CommandResult(this.commandName, commandResult);
    }

    @Override
    public User getUser()
    {
        return this.user;
    }

    @Override
    public String getCommandName()
    {
        return this.commandName;
    }
}
