package artopia.commands;

import artopia.models.User;

/**
 * @author Rottenwood
 */
public abstract class AbstractCommand implements Commandable {
    private User user;
    private String commandName;

    @Override
    public User getUser() {
        return this.user;
    }

    @Override
    public String getCommandName() {
        return commandName;
    }
}
