package model.logger;

import model.event.Notification;
import model.message.Message;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileLogger extends Logger{
    @Override
    public void log(Message message) {
        FileWriter fw = null;
        BufferedWriter bw = null;
        PrintWriter pw = null;
        try {
            fw = new FileWriter("src/main/resources/log.txt", true);
            bw = new BufferedWriter(fw);
            pw = new PrintWriter(bw);

            pw.println(message);
            pw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                pw.close();
                bw.close();
                fw.close();
            } catch (IOException io) {
                io.printStackTrace();
            }

        }
    }

    @Override
    public void update(Notification notification) {
        log(notification.getMessage());
    }
}
