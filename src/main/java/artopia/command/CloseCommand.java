package artopia.command;

import artopia.command.infrastructure.open.AbstractOpenCommand;
import artopia.entitiy.User;
import artopia.entitiy.room.Exit;
import artopia.exception.ServiceNotFound;
import artopia.exception.UndefinedDirection;
import artopia.service.command.CommandResult;
import artopia.service.command.errors.AbstractCommandError;
import artopia.service.command.errors.AlreadyClosed;
import artopia.service.command.errors.ArgumentMissing;
import artopia.service.command.errors.CloseTargetNotFound;
import artopia.service.locator.ServiceLocator;

/**
 * @author Vehsamrak
 */
public class CloseCommand extends AbstractOpenCommand
{
    @Override
    public String getDescription()
    {
        return "закрыть дверь, сундук, etc";
    }

    @Override
    public CommandResult execute(
            String[] arguments, User user, ServiceLocator serviceLocator
    ) throws ServiceNotFound, UndefinedDirection
    {
        if (arguments.length == 0) {
            return this.createCommandResultWithError(new ArgumentMissing("Что именно закрыть?"));
        }

        Exit exit = super.getExitByArguments(arguments, user, serviceLocator);

        if (exit == null) {
            return this.createCommandResultWithError(new CloseTargetNotFound());
        }

        if (exit.isClosed()) {
            return this.createCommandResultWithError(new AlreadyClosed());
        }

        exit.close();

        return new CommandResult(
                "close",
                "Ты закрыл проход "
                        + super.getCyrillicDirectionString(super.findDirectionByFirstLetters(arguments[0]))
                        + "."
        );
    }

    private CommandResult createCommandResultWithError(AbstractCommandError error)
    {
        CommandResult commandResult = new CommandResult("close");
        commandResult.addError(error);

        return commandResult;
    }
}
