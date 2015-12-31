package artopia.services.commands;

import artopia.commands.infrastructure.AbstractCommand;
import artopia.commands.AuthorsCommand;
import artopia.commands.ExitCommand;
import artopia.commands.HelpCommand;
import artopia.commands.LookCommand;

import java.util.HashMap;

/**
 * @author Vehsamrak
 */
public class CommandRepository
{
    final private HashMap<String, AbstractCommand> commandList = new HashMap<>();

    /**
     * Список всех игровых команд
     */
    public CommandRepository()
    {
        this.commandList.put("look", new LookCommand());
        this.commandList.put("authors", new AuthorsCommand());
        this.commandList.put("exit", new ExitCommand());
        this.commandList.put("help", new HelpCommand());
    }

    /**
     * Поиск по названию команды
     *
     * @param command Название команды
     * @return Объект команды
     */
    public AbstractCommand findByName(String command)
    {
        return this.commandList.get(command.toLowerCase());
    }
}
