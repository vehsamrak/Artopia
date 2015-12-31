package artopia.commands;

import artopia.models.User;
import artopia.services.commands.CommandResult;

/**
 * @author Rottenwood
 */
interface Commandable {
    CommandResult execute(User user);

    User getUser();
    String getCommandName();
}
