package artopia.services.commands;

import artopia.commands.AuthorsCommand;
import artopia.commands.infrastructure.AbstractCommand;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Vehsamrak
 */
public class CommandRepositoryTest extends Assert
{
    @Test
    public void findByName_englishAliasExistsInRepository_returnsCommand()
    {
        CommandRepository commandRepository = this.createCommandRepository();

        AbstractCommand authorsCommand = commandRepository.findByName("authors");

        assertTrue(authorsCommand instanceof AuthorsCommand);
    }

    @Test
    public void findByName_russianAliasExistsInRepository_returnsCommand()
    {
        CommandRepository commandRepository = this.createCommandRepository();

        AbstractCommand authorsCommand = commandRepository.findByName("авторы");

        assertTrue(authorsCommand instanceof AuthorsCommand);
    }

    @Test
    public void findByName_commandDoesNotExistsInRepository_returnsNull()
    {
        CommandRepository commandRepository = this.createCommandRepository();

        AbstractCommand notExistingTestCommand = commandRepository.findByName("notExistingTestCommand");

        assertNull(notExistingTestCommand);
    }

    @Test
    public void findByName_partialAliasExistsInRepository_returnsCommand()
    {
        CommandRepository commandRepository = this.createCommandRepository();

        AbstractCommand authorsCommand = commandRepository.findByName("autho");

        assertTrue(authorsCommand instanceof AuthorsCommand);
    }

    private CommandRepository createCommandRepository()
    {
        return new CommandRepository();
    }
}
