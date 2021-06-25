package part1.lesson11.task01.message;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadMessage extends Thread {
    BufferedReader br;

    public ReadMessage(BufferedReader br) {
        this.br = br;
    }

    @Override
    public void run() {
        String line;
        try {
            while (true) {
                line = br.readLine(); // ждем сообщения с сервера
                if (line.equals("quit")) {
                    break; // выходим из цикла если пришло "quit"
                }
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
