package artopia.services.commands;

import artopia.services.commands.errors.AbstractCommandError;

import java.util.ArrayList;

/**
 * @author Rottenwood
 */
public class CommandResult {
    public String command;
    private ArrayList<AbstractCommandError> errors = new ArrayList<>();
    private String result;

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
     * Команда без результата
     * // TODO: 25.12.15 Нужно от этого избавиться
     * @param command команда
     */
    public CommandResult(String command) {
        this.command = command;
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
        return command;
    }

    public ArrayList<AbstractCommandError> getErrors() {
        return errors;
    }
}
