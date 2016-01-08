package artopia.command.infrastructure;

import artopia.entitiy.User;
import artopia.exception.ServiceNotFound;
import artopia.service.command.CommandResult;
import artopia.service.locator.ServiceLocator;

/**
 * @author Rottenwood
 */
interface Executable
{
    CommandResult execute(User user, ServiceLocator serviceLocator) throws ServiceNotFound;
}
