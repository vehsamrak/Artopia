import artopia.handlers.ConnectionHandler;
import artopia.handlers.ExceptionHandler;
import artopia.services.DatabaseService;
import artopia.services.UserService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Rottenwood
 */
public class Main {
    public static void main(String[] args) {
        int port = 9000;

        System.out.println("Запуск сессии для работы с базой данных ...");

        UserService userService = new UserService(new DatabaseService());

        try {
            ServerSocket serverSocket = new ServerSocket(port);

            System.out.printf("Сервер запущен на порту %s ...%n", port);

            while (true) {
                Socket socket = serverSocket.accept();

                BufferedReader socketInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter socketOutput = new PrintWriter(socket.getOutputStream(), true);

                Runnable connectionHandler = new ConnectionHandler(socket, socketInput, socketOutput, userService);
                new Thread(connectionHandler).start();

            }
        } catch (Exception exception) {
            ExceptionHandler.handle(exception);
        }
    }
}
