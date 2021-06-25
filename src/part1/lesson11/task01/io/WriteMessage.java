package part1.lesson11.task01.io;

import java.io.PrintWriter;
import java.util.Scanner;

public class WriteMessage extends Thread {
    PrintWriter out;
    Scanner console;

    public WriteMessage(PrintWriter out, Scanner console) {
        this.out = out;
        this.console = console;
    }

    @Override
    public void run() {
        while (true) {
            try {
                String line = console.nextLine();
                out.write(line + "\n"); // отправляем на сервер
                out.flush(); // чистим буфер
                if (line.equals("quit")) {
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
