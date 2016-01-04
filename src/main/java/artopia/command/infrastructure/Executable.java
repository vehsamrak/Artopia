package artopia.command.infrastructure;

import artopia.entitiy.User;
import artopia.service.command.CommandResult;

/**
 * @author Rottenwood
 */
interface Executable
{
    CommandResult execute(User user);
}
