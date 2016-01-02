package artopia.services.commands;

import artopia.commands.AuthorsCommand;
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

        assertTrue(commandRepository.findByName("authors") instanceof AuthorsCommand);
    }

    @Test
    public void findByName_russianAliasExistsInRepository_returnsCommand()
    {
        CommandRepository commandRepository = this.createCommandRepository();

        assertTrue(commandRepository.findByName("авторы") instanceof AuthorsCommand);
    }

    @Test
    public void findByName_commandDoesNotExistsInRepository_returnsNull()
    {
        CommandRepository commandRepository = this.createCommandRepository();

        assertNull(commandRepository.findByName("notExistingTestCommand"));
    }

    @Test
    public void findByName_partialAliasExistsInRepository_returnsCommand()
    {
        CommandRepository commandRepository = this.createCommandRepository();

        assertTrue(commandRepository.findByName("aut") instanceof AuthorsCommand);
        assertTrue(commandRepository.findByName("auth") instanceof AuthorsCommand);
        assertTrue(commandRepository.findByName("autho") instanceof AuthorsCommand);
        assertTrue(commandRepository.findByName("author") instanceof AuthorsCommand);
    }

    private CommandRepository createCommandRepository()
    {
        return new CommandRepository();
    }
}
