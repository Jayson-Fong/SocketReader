package org.jaysonfong.socketreader.handler;

import javafx.scene.control.TextArea;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.Scanner;

public class ReceiveHandler extends Handler {

    protected final Socket socket;

    public ReceiveHandler(Socket socket, TextArea textArea)
    {
        super(textArea);
        this.socket = socket;
    }

    @Override
    public void run() {
        try (InputStream input = this.socket.getInputStream())
        {
            Scanner scanner = new Scanner(input);
            scanner.useDelimiter("\n");

            if (scanner.hasNext())
            {
                this.write("===New Message===\n");

                // Create StringBuilder
                StringBuilder stringBuilder = new StringBuilder();
                while (scanner.hasNext())
                {
                    stringBuilder.append(scanner.next()).append("\n");
                }
                this.write(stringBuilder.toString());
                this.write("===End of Message===");
            }
        } catch (IOException ioException)
        {
            this.write("Encountered IOException: " + ioException.getMessage());
        }

        this.write("Client Socket Closed");
    }

}
