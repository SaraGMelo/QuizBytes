package academy.mindswap.server.util;

import java.io.*;
import java.net.Socket;

public class Timer extends java.util.Timer implements Runnable {
    private int minutes;
    private int seconds;
    private BufferedWriter out;
    private Socket socket;

    public Timer(int minutes, Socket socket) throws IOException {
        this.socket = socket;
        this.minutes = minutes - 1;
        this.seconds = 60;
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    @Override
    public void run() {
        while (!socket.isClosed()) {
            if (minutes < 0) {
                return;
            }
            //while (seconds >= 0) {
                seconds--;
                setSeconds(seconds);
                /*if (seconds == -1) {
                    seconds = 59;
                }*/
                /*try {
                    Thread.sleep(100);
                    out.write("Timer: " + minutes + ":" + seconds);
                    out.flush();
                    out.write("\b\b\b\b"); //VERIFY
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }*/
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                try {
//                    out.write("\b\b\b\b"); //VERIFY
//                    out.flush();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                if (seconds == 0) {
                    try {
                        out.write("Time is over!");
                        out.newLine();
                        out.flush();
                        break;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            //}
        }
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }
}

