package artopia.services;

import artopia.commands.AbstractCommand;
import artopia.commands.AuthorsCommand;
import artopia.services.commands.CommandRepository;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Vehsamrak
 */
public class CommandRepositoryTest extends Assert
{
    @Test
    public void findByName_givenCommandListWithCommands_returnsExistingCommand()
    {
        CommandRepository commandRepository = this.createCommandRepository();

        AbstractCommand authorsCommand = commandRepository.findByName("authors");

        assertTrue(authorsCommand instanceof AuthorsCommand);
    }

    private CommandRepository createCommandRepository()
    {
        return new CommandRepository();
    }
}
