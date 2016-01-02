package artopia.commands.infrastructure;

import artopia.models.User;
import artopia.services.commands.CommandResult;

/**
 * @author Rottenwood
 */
interface Executable
{
    CommandResult execute(User user);
}
