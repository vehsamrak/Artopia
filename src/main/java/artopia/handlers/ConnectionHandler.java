package artopia.handlers;

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
    private BufferedReader socketInput;

    public ConnectionHandler(Socket socket) {
        this.socket = socket;

        try {
            InputStreamReader in = new InputStreamReader(this.socket.getInputStream());
            this.socketInput = new BufferedReader(in);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        System.out.printf("[*] Новое подключение! (%s)%n", this.socket.getInetAddress().getCanonicalHostName());

        try {
            PrintWriter socketOutput = new PrintWriter(this.socket.getOutputStream(), true);


            socketOutput.println("Добро пожаловать в JavaMud!");
            socketOutput.println("Введите ваше имя: ");
            String username = this.socketInput.readLine();

            socketOutput.println("Введите пароль: ");
            String password = this.socketInput.readLine();

            User user = UserService.login(username, password);

            socketOutput.printf("Добро пожаловать, %s!%n", user.getUsername());

            CommandService commandService = new CommandService(user);

            String command = null;
            while (true) {
                socketOutput.println("Введите команду:");
                command = this.socketInput.readLine();
                CommandResult commandResult = commandService.execute(command);
                socketOutput.printf("%s%n%n", commandResult.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
