package com.filefinder.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GUI extends Application {

    private final static Logger LOGGER = LogManager.getLogger(GUI.class);
    private static Stage primaryStage = null;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        GUI.primaryStage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        root.setStyle("-fx-background-color: rgba(255, 255, 255, 0.0);");

        Scene scene = new Scene(root, 300, 275);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);

        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setTitle("File Finder");
        primaryStage.setAlwaysOnTop(true);

        // show first so that the search box gets the focus correctly the first time
        primaryStage.show();
        primaryStage.hide();

        // Do not exit when hiding
        Platform.setImplicitExit(false);

        LOGGER.info("GUI launched.");
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }
}