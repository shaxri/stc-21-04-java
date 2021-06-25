package part1.lesson11.task01.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;

public class ServerThread extends Thread {

    String name;
    private Socket socket;
    private Map<String, Socket> connections;

    public ServerThread(Socket socket, Map<String, Socket> connections) {
        this.socket = socket;
        this.connections = connections;
    }

    @Override
    public void run() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) { // поток на чтение
            name = br.readLine();

            if(connections.containsKey(name)){
                System.out.println("Сlient already exists");
                out.write("quit" + "\n");
                out.flush();
                return;
            }
            System.out.println("Сlient " + name + " is connected");
            connections.put(name, socket);

            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("Received from the client " + name + ": " + line);
                String destination;
                if ((line.indexOf(':') != -1) && (!(destination = line.substring(0, line.indexOf(':'))).isEmpty()
                        && connections.containsKey(destination))) {
                    sendMessageUnicast(line.substring(destination.length() + 1), connections.get(destination), "");
                } else {
                    if (line.equals("quit")) {
                        out.write(line + "\n");
                        out.flush();
                        connections.remove(name);
                        sendMessageBroadcast("quit chat");

                    } else {
                        sendMessageBroadcast(line);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMessageUnicast(String message, Socket socket, String name) throws IOException {
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        out.write(this.name + ": " + message + "\n");
        out.flush();
    }

    private void sendMessageBroadcast(String message) throws IOException {
        for (Map.Entry<String, Socket> entry : connections.entrySet()) {
            if (!name.equals(entry.getKey())) {
                sendMessageUnicast(message, entry.getValue(), entry.getKey());
            }
        }
    }
}