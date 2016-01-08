package artopia.handler;

import artopia.entitiy.User;
import artopia.exception.EmptyPassword;
import artopia.exception.EmptyUsername;
import artopia.exception.WrongPassword;
import artopia.service.UserService;
import artopia.service.command.CommandResult;
import artopia.service.command.CommandService;
import artopia.service.locator.Service;
import artopia.service.locator.ServiceLocator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author Rottenwood
 */
public class ConnectionHandler implements Runnable
{
    private final PrintWriter socketOutput;
    private final ServiceLocator serviceLocator;
    private final Socket socket;
    private final BufferedReader socketInput;
    private final String clientHostName;

    public ConnectionHandler(
            Socket socket,
            BufferedReader socketInput,
            PrintWriter socketOutput,
            ServiceLocator serviceLocator
    ) {
        this.socket = socket;
        this.socketInput = socketInput;
        this.socketOutput = socketOutput;
        this.serviceLocator = serviceLocator;
        this.clientHostName = socket.getInetAddress().getCanonicalHostName();
    }

    @Override
    public void run()
    {
        System.out.printf("[>] Новое подключение! (%s)%n", this.clientHostName);

        try {
            this.socketOutput.println("Добро пожаловать в JavaMud!");
            this.socketOutput.println("Введите имя персонажа: ");
            String name = this.socketInput.readLine();

            this.socketOutput.println("Введите пароль: ");
            String password = this.socketInput.readLine();

            try {
                UserService userService = (UserService) this.serviceLocator.get(Service.USER);
                User user = userService.login(name, password);

                this.socketOutput.printf("Добро пожаловать, %s!%n", user.getName());

                CommandService commandService = (CommandService) this.serviceLocator.get(Service.COMMAND);
                commandService.setUser(user);

                while (true) {
                    this.socketOutput.println("Введите команду:");
                    String command = this.socketInput.readLine();

                    CommandResult commandResult = commandService.execute(command);
                    this.socketOutput.printf("%s%n%n", commandResult.toString());

                    // TODO: 31.12.15 Реализовать для этой цели систему Event Listening
                    if (commandResult.haveSubCommands() && commandResult.getSubCommands().contains("exit")) {
                        this.disconnect();
                        return;
                    }
                }
            } catch (EmptyPassword | EmptyUsername exception) {
                this.socketOutput.println("Логин и пароль не могут быть пустыми!");
                this.disconnect();
            } catch (WrongPassword exception) {
                this.socketOutput.println("Неверный пароль!");
                this.disconnect();
            }
        } catch (Exception exception) {
            this.socketOutput.println("Ошибка!");
            ExceptionHandler.handle(exception);
        }
    }

    public void disconnect() throws IOException
    {
        this.socket.close();
        System.out.printf("[<] Клиент отключен. (%s)%n", this.clientHostName);
    }
}
