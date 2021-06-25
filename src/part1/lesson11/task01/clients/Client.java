package part1.lesson11.task01.clients;


import part1.lesson11.task01.message.ReadMessage;
import part1.lesson11.task01.message.WriteMessage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void connect(String name) {
        try {
            Socket socket = new Socket("localhost", 36003);
            try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
                 Scanner console = new Scanner(System.in)) {
                Thread writeMessage = new WriteMessage(out, console);
                Thread readMessage = new ReadMessage(in);
                out.println(name);
                writeMessage.start();
                readMessage.start();
                writeMessage.join();
                readMessage.join();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}