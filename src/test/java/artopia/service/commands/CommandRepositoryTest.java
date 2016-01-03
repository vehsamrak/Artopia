package artopia.service.commands;

import artopia.command.AuthorsCommand;
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

    @Test
    public void getDescriptions_repositoryWithCommands_returnsSummaryStringWithAllDescriptions()
    {
        CommandRepository commandRepository = this.createCommandRepository();
        String commandsDescription = commandRepository.getDescriptions();

        assertTrue(commandsDescription.startsWith("Игровые команды\n===============\n" +
                "authors, credits, авторы - информация об авторах проекта\n"));
        assertTrue(commandsDescription.contains("\nexit, quit, выход - выход из игры\n"));
    }

    private CommandRepository createCommandRepository()
    {
        return new CommandRepository();
    }
}
