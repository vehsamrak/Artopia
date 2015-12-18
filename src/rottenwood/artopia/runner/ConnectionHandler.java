package rottenwood.artopia.runner;

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

    public ConnectionHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        System.out.printf("[*] Новое подключение! (%s)%n", this.socket.getInetAddress().getCanonicalHostName());

        BufferedReader socketInput = null;
        try {
            socketInput = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            PrintWriter socketOutput = new PrintWriter(this.socket.getOutputStream(), true);

            String command = null;

            socketOutput.println("Добро пожаловать в JavaMud!");

            while (true) {
                command = socketInput.readLine();

                socketOutput.println("Привет!");
                socketOutput.printf("Введена команда \"%s\".%n", command);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
