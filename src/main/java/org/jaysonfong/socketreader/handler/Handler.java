package org.jaysonfong.socketreader.handler;

import javafx.scene.control.TextArea;

abstract class Handler implements Runnable {

    protected final TextArea textArea;

    /**
     * Receives a TextArea for writing.
     * @param textArea
     */
    protected Handler(TextArea textArea)
    {
        this.textArea = textArea;
    }

    /**
     * Writes the message to the TextArea
     * @param message
     */
    protected void write(final String message)
    {
        synchronized (this.textArea)
        {
            this.textArea.appendText('\n' + message);
        }
    }

}
