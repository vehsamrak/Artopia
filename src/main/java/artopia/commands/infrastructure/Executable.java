package artopia.commands.infrastructure;

import artopia.entities.User;
import artopia.services.commands.CommandResult;

/**
 * @author Rottenwood
 */
interface Executable
{
    CommandResult execute(User user);
}
