package org.jaysonfong.socketreader;

import javafx.application.Application;
import javafx.stage.Stage;
import org.jaysonfong.socketreader.controller.Controller;
import org.jaysonfong.socketreader.controller.ReceiveController;

public class Main extends Application {

    protected Controller controller;

    /**
     * Displays the interface.
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        this.controller = new ReceiveController();
        controller.buildStage(primaryStage);
        primaryStage.show();
    }

    /**
     * Run the program.
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    public void stop()
    {
        this.controller.stop();
    }

}
