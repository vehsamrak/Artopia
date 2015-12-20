package artopia.commands;

import artopia.services.commands.CommandResult;

/**
 * @author Rottenwood
 */
public class AbstractCommand {
    public final String command;

    public AbstractCommand(String command) {
        this.command = command;
    }

    public CommandResult execute() {
        return new CommandResult(this.command);
    }
}
