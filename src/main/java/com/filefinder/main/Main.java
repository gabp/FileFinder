package com.filefinder.main;

import com.filefinder.gui.GUI;
import javafx.application.Application;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    final static Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        // Initialize the application
        LOGGER.info("Starting FileFinder...");
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);

        // Start the GUI
        Application.launch(GUI.class);
    }
}
