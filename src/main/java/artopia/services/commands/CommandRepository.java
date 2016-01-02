package artopia.services.commands;

import artopia.commands.AuthorsCommand;
import artopia.commands.ExitCommand;
import artopia.commands.HelpCommand;
import artopia.commands.LookCommand;
import artopia.commands.infrastructure.AbstractCommand;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Vehsamrak
 */
public class CommandRepository
{
    final private HashMap<AbstractCommand, String[]> commandList = new HashMap<>();

    /**
     * Список всех игровых команд
     * TODO: 01.01.16 Должно быть вынесено в отдельный конфиг
     */
    public CommandRepository()
    {
        this.commandList.put(new LookCommand(), new String[]{"look", "смотреть"});
        this.commandList.put(new AuthorsCommand(), new String[]{"authors", "credits", "авторы"});
        this.commandList.put(new ExitCommand(), new String[]{"exit", "quit", "выход"});
        this.commandList.put(new HelpCommand(), new String[]{"help", "помощь", "?"});
    }

    /**
     * Поиск по названию или алиасу команды
     * @param inputCommand Название команды или алиас
     * @return Объект команды
     */
    public AbstractCommand findByName(String inputCommand)
    {
        inputCommand = inputCommand.toLowerCase();

        for (Map.Entry<AbstractCommand, String[]> entry : this.commandList.entrySet()) {
            AbstractCommand command = entry.getKey();
            String[] commandAliases = entry.getValue();

            for (String commandAlias : commandAliases) {
                if (commandAlias.startsWith(inputCommand)) {
                    return command;
                }
            }
        }

        return null;
    }
}
