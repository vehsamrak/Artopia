package artopia.handlers;

import artopia.exceptions.EmptyPassword;
import artopia.exceptions.EmptyUsername;
import artopia.exceptions.WrongPassword;
import artopia.models.User;
import artopia.services.UserService;
import artopia.services.commands.CommandResult;
import artopia.services.commands.CommandService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author Rottenwood
 */
public class ConnectionHandler implements Runnable {
    private final Socket socket;
    private UserService userService;
    private BufferedReader socketInput;

    public ConnectionHandler(Socket socket, UserService userService) {
        this.socket = socket;
        this.userService = userService;

        try {
            InputStreamReader in = new InputStreamReader(this.socket.getInputStream());
            this.socketInput = new BufferedReader(in);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        System.out.printf("[>] Новое подключение! (%s)%n", this.socket.getInetAddress().getCanonicalHostName());

        try {
            PrintWriter socketOutput = new PrintWriter(this.socket.getOutputStream(), true);

            socketOutput.println("Добро пожаловать в JavaMud!");
            socketOutput.println("Введите ваше имя: ");
            String username = this.socketInput.readLine();

            socketOutput.println("Введите пароль: ");
            String password = this.socketInput.readLine();

            try {
                User user = this.userService.login(username, password);

                socketOutput.printf("Добро пожаловать, %s!%n", user.getUsername());

                CommandService commandService = new CommandService(user);

                while (true) {
                    socketOutput.println("Введите команду:");
                    String command = this.socketInput.readLine();

                    CommandResult commandResult = commandService.execute(command);
                    socketOutput.printf("%s%n%n", commandResult.toString());

                    if (commandResult.haveSubCommands() && commandResult.getSubCommands().contains("exit")) {
                        this.disconnect();
                        return;
                    }
                }
            } catch (EmptyPassword | EmptyUsername exception) {
                socketOutput.println("Логин и пароль не могут быть пустыми!");
                this.disconnect();
            } catch (WrongPassword exception) {
                socketOutput.println("Неверный пароль!");
                this.disconnect();
            }

        } catch (IOException e) {
            e.printStackTrace();
            this.disconnect();
        }
    }

    public void disconnect() {
        try {
            this.socket.close();
            System.out.printf("[<] Клиент отключен. (%s)%n", this.socket.getInetAddress().getCanonicalHostName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
