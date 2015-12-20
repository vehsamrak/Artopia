package artopia.commands;

import artopia.services.commands.CommandResult;

/**
 * @author Rottenwood
 */
public class HelpCommand extends AbstractCommand {
    public HelpCommand(String command) {
        super(command);
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(super.command);
    }
}
