package artopia.handlers;

import artopia.models.User;
import artopia.services.UserService;

import java.io.*;
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

            String command = null;
            while (true) {
                socketOutput.println("Введите команду:");
                command = this.socketInput.readLine();
                socketOutput.printf("Вы ввели команду \"%s\".%n", command);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
