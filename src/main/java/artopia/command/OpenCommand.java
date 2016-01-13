package artopia.command;

import artopia.command.infrastructure.open.AbstractOpenCommand;
import artopia.entitiy.User;
import artopia.entitiy.room.Exit;
import artopia.exception.ServiceNotFound;
import artopia.exception.UndefinedDirection;
import artopia.service.command.CommandResult;
import artopia.service.command.errors.AbstractCommandError;
import artopia.service.command.errors.ArgumentMissing;
import artopia.service.command.errors.OpenTargetNotFound;
import artopia.service.locator.ServiceLocator;

/**
 * @author Vehsamrak
 */
public class OpenCommand extends AbstractOpenCommand
{
    @Override
    public String getDescription()
    {
        return "открыть что-либо, будь то сундук или дверь";
    }

    @Override
    public CommandResult execute(
            String[] arguments, User user, ServiceLocator serviceLocator
    ) throws ServiceNotFound, UndefinedDirection
    {
        if (arguments.length == 0) {
            return this.createCommandResultWithError(new ArgumentMissing("Что именно открыть?"));
        }

        Exit exit = super.getExitByArguments(arguments, user, serviceLocator);

        if (exit == null || !exit.isClosed()) {
            return this.createCommandResultWithError(new OpenTargetNotFound());
        }

        exit.open();

        return new CommandResult(
                "open",
                "Ты открыл проход "
                        + super.getCyrillicDirectionString(super.findDirectionByFirstLetters(arguments[0]))
                        + "."
        );
    }

    private CommandResult createCommandResultWithError(AbstractCommandError error)
    {
        CommandResult commandResult = new CommandResult("open");
        commandResult.addError(error);

        return commandResult;
    }
}
