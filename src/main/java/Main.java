import artopia.handler.ConnectionHandler;
import artopia.handler.ExceptionHandler;
import artopia.service.DatabaseService;
import artopia.service.UserService;

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
        DatabaseService databaseService = new DatabaseService();
        UserService userService = new UserService(databaseService);

        try {
            ServerSocket serverSocket = new ServerSocket(port);

            System.out.printf("Сервер запущен на порту %s ...%n", port);

            while (true) {
                Socket socket = serverSocket.accept();

                BufferedReader socketInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter socketOutput = new PrintWriter(socket.getOutputStream(), true);

                Runnable connectionHandler = new ConnectionHandler(
                        socket,
                        socketInput,
                        socketOutput,
                        userService,
                        databaseService
                );

                new Thread(connectionHandler).start();

            }
        } catch (Exception exception) {
            ExceptionHandler.handle(exception);
        }
    }
}
