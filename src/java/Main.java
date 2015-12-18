import java.io.*;
import java.net.*;

/**
 * @author Rottenwood
 */
public class Main {
    public static void main(String[] args) {
        int port = 7777;

        try {
            ServerSocket serverSocket = new ServerSocket(port);

            System.out.printf("Server is started on port %s ...%n", port);

            Socket socket = serverSocket.accept();

            System.out.println("Client!");

            BufferedReader socketInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter socketOutput = new PrintWriter(socket.getOutputStream(), true);

            String command = null;

            socketOutput.println("Добро пожаловать в JavaMud!");

            while (true) {
                command = socketInput.readLine();

                socketOutput.println("Привет!");
                socketOutput.printf("Введена команда \"%s\".%n", command);
            }


        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
