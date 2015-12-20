package artopia.services.commands;

import artopia.services.commands.errors.AbstractCommandError;

import java.util.ArrayList;

/**
 * @author Rottenwood
 */
public class CommandResult {
    public String command;
    private ArrayList<AbstractCommandError> errors = new ArrayList<>();

    public CommandResult(String command) {
        this.command = command;
    }

    public void addError(AbstractCommandError error) {
        this.errors.add(error);
    }

    public ArrayList<AbstractCommandError> getErrors() {
        return errors;
    }

    public boolean haveErrors() {
        return !this.errors.isEmpty();
    }
}
