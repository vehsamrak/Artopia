package artopia.service.commands;

import artopia.service.commands.errors.AbstractCommandError;

import java.util.ArrayList;

/**
 * @author Rottenwood
 */
public class CommandResult {
    public String command;
    private ArrayList<AbstractCommandError> errors = new ArrayList<>();
    private String result;
    private ArrayList<String> subCommands = new ArrayList<>();

    /**
     * Команда с результатом
     * @param command команда
     * @param result рузультат команды
     */
    public CommandResult(String command, String result) {
        this.command = command;
        this.result = result;
    }

    /**
     * @param error Добавление ошибки
     */
    public void addError(AbstractCommandError error) {
        this.errors.add(error);
    }

    /**
     * @return Проверка наличия ошибок
     */
    public boolean haveErrors() {
        return !this.errors.isEmpty();
    }

    @Override
    public String toString() {
        String result;

        if (this.haveErrors()) {
            result = this.getErrorMessages();
        } else {
            result = this.result;
        }

        return result;
    }

    /**
     * @return Список ошибок в формате строки
     */
    private String getErrorMessages() {
        ArrayList<String> errors = new ArrayList<>();

        for (AbstractCommandError error : this.errors) {
            errors.add(error.getErrorMessage());
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (String errorMessage : errors) {
            stringBuilder.append(errorMessage);
        }

        return stringBuilder.toString();
    }

    /**
     * @return Имя команды
     */
    public String getCommandName() {
        return this.command;
    }

    public ArrayList<AbstractCommandError> getErrors() {
        return this.errors;
    }

    public boolean haveSubCommands() {
        return !this.subCommands.isEmpty();
    }

    // TODO: 31.12.15 Реализовать для этой цели систему Event Listening. После чего удалить этот метод
    public ArrayList<String> getSubCommands() {
        return this.subCommands;
    }

    public void addSubCommand(String subCommand) {
        this.subCommands.add(subCommand);
    }
}
