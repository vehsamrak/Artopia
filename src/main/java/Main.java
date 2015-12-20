import artopia.handlers.ConnectionHandler;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Rottenwood
 */
public class Main {
    public static void main(String[] args) {
        int port = 9000;

        try {
            ServerSocket serverSocket = new ServerSocket(port);

            System.out.printf("Сервер запущен на порту %s ...%n", port);

            while (true) {
                Socket socket = serverSocket.accept();

                Runnable connectionHandler = new ConnectionHandler(socket);
                new Thread(connectionHandler).start();

            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
