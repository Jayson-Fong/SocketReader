package org.jaysonfong.socketreader.process;

import javafx.scene.control.TextArea;
import org.jaysonfong.socketreader.handler.ReceiveHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Receive extends Process {

    protected final short port;
    protected final TextArea textArea;
    protected final ExecutorService executor;

    protected ServerSocket serverSocket;

    {
        this.executor = Executors.newSingleThreadExecutor();
    }

    public Receive(final short port, final TextArea textArea)
    {
        this.port = port;
        this.textArea = textArea;
    }

    @Override
    public void run() {
        try
        {
            this.serverSocket = new ServerSocket(this.port);

            synchronized (this.textArea)
            {
                this.textArea.appendText("\nAccepting Connections...");
            }

            Socket socket;
            while (!serverSocket.isClosed() && !this.isInterrupted())
            {
                socket = serverSocket.accept();
                this.executor.submit(new ReceiveHandler(socket, this.textArea));
            }

        }
        catch (IOException ioException)
        {
            synchronized (this.textArea)
            {
                this.textArea.appendText("\nSocket Exception Occurred: " + ioException.getMessage());
            }
        }

        synchronized (this.textArea)
        {
            this.textArea.appendText("\nClosed Server Socket");
        }
    }

    public void interrupt()
    {
        super.interrupt();
        try {
            this.serverSocket.close();
        } catch (IOException ioException) {
            synchronized (this.textArea)
            {
                this.textArea.appendText("\nSocket Exception Occurred: " + ioException.getMessage());
            }
        }
    }

}
