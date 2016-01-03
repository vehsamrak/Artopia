package artopia.handlers;

import artopia.exceptions.EmptyPassword;
import artopia.exceptions.EmptyUsername;
import artopia.exceptions.WrongPassword;
import artopia.entities.User;
import artopia.services.UserService;
import artopia.services.commands.CommandResult;
import artopia.services.commands.CommandService;

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
    private final Socket socket;
    private final BufferedReader socketInput;
    private final String clientHostName;
    private final UserService userService;

    public ConnectionHandler(Socket socket, BufferedReader socketInput, PrintWriter socketOutput, UserService userService)
    {
        this.socket = socket;
        this.socketInput = socketInput;
        this.socketOutput = socketOutput;
        this.clientHostName = socket.getInetAddress().getCanonicalHostName();
        this.userService = userService;
    }

    @Override
    public void run()
    {
        System.out.printf("[>] Новое подключение! (%s)%n", this.clientHostName);

        try {
            this.socketOutput.println("Добро пожаловать в JavaMud!");
            this.socketOutput.println("Введите ваше имя: ");
            String username = this.socketInput.readLine();

            this.socketOutput.println("Введите пароль: ");
            String password = this.socketInput.readLine();

            try {
                User user = this.userService.login(username, password);

                this.socketOutput.printf("Добро пожаловать, %s!%n", user.getUsername());

                CommandService commandService = new CommandService(user);

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
        } catch (IOException exception) {
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
