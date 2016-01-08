package artopia.command.infrastructure;

import artopia.entitiy.User;
import artopia.service.command.CommandResult;
import artopia.service.locator.ServiceLocator;

/**
 * @author Rottenwood
 */
interface Executable
{
    CommandResult execute(User user, ServiceLocator serviceLocator) throws Exception;
}
