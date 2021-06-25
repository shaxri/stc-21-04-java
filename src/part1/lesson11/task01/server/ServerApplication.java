package part1.lesson11.task01.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ServerApplication {
    public static Map<String, Socket> connections = new ConcurrentHashMap<>();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(4);

        try (ServerSocket serverSocket = new ServerSocket(36003)) {
            while (true) {
                Socket socket = serverSocket.accept();
                ServerThread serverThread = new ServerThread(socket, connections);
                executor.execute(serverThread);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        executor.shutdown();
        executor.awaitTermination(100, TimeUnit.MILLISECONDS);
    }
}
