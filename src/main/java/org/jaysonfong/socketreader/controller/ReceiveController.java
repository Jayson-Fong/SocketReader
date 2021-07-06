package org.jaysonfong.socketreader.controller;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import org.jaysonfong.socketreader.process.Receive;

public class ReceiveController extends Controller {

    // Main Content
    protected final TextArea textArea;

    // Lower Toolbar
    protected final TextField portField;
    protected final Button bindButton;

    // Process
    protected Thread processThread;

    /**
     * Builds the main scene components.
     */
    public ReceiveController() {
        this.textArea = this.buildTextArea();
        this.portField = this.buildPortField();
        this.bindButton = this.buildBindButton();
    }

    /**
     * Runs stop processes.
     */
    public void stop()
    {
        this.processThread.interrupt();
    }

    /**
     * Builds the root pane for the container.
     * @return Root Pane
     */
    @Override
    protected Pane buildPane() {
        var toolbar = new ToolBar(
                this.portField,
                this.bindButton,
                new Label("Created by Jayson Fong 2021")
        );

        BorderPane pane = new BorderPane();
        pane.setCenter(this.textArea);
        pane.setBottom(toolbar);
        pane.setPadding(new Insets(15, 15, 0, 15));

        return pane;
    }

    /**
     * Build the text area for output display.
     * @return Output Display
     */
    private TextArea buildTextArea()
    {
        return new TextArea();
    }

    /**
     * Builds the port field used by the bind button.
     * @return Port Field
     */
    private TextField buildPortField()
    {
        return new TextField();
    }

    /**
     * Builds the bind button for managing the processes.
     * @return Bind Button
     */
    private Button buildBindButton()
    {
        Button button = new Button("Bind");

        button.setOnMouseClicked(event -> {
            try {
                final short port = Short.valueOf(this.portField.getText());
                var newProcessThread = new Receive(port, this.textArea);

                newProcessThread.start();

                synchronized(this)
                {
                    this.processThread = newProcessThread;
                }
            } catch (NumberFormatException numberFormatException)
            {
                synchronized (this.textArea) {
                    this.textArea.appendText("\nInvalid Port");
                }
            }
        });

        return button;
    }

    /**
     * Returns the title.
     * @return Title
     */
    protected String getTitle()
    {
        return "Receive Messages";
    }

    protected short getHeight()
    {
        return 500;
    }

    protected short getWidth()
    {
        return 800;
    }

}
