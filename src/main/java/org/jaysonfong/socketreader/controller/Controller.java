package org.jaysonfong.socketreader.controller;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public abstract class Controller {

    /**
     * Runs stop processes.
     */
    public void stop() {

    }

    /**
     * Generates the root scene.
     * @return Root Scene
     */
    protected abstract Pane buildPane();

    /**
     * Returns the title.
     * @return Title
     */
    protected abstract String getTitle();

    /**
     * Returns the height.
     * @return Height
     */
    protected abstract short getHeight();

    /**
     * Returns the width.
     * @return Width
     */
    protected abstract short getWidth();

    /**
     * Sets the title and scene attributes of the stage.
     * @param stage
     */
    public void buildStage(Stage stage)
    {
        stage.setTitle(this.getTitle());
        stage.setScene(new Scene(this.buildPane(), this.getWidth(), this.getHeight()));
    }

}
