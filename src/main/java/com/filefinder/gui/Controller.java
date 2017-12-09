package com.filefinder.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.controlsfx.control.textfield.CustomTextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    final static Logger LOGGER = LogManager.getLogger(Controller.class);

    @FXML
    CustomTextField searchBox = null;

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        initKeyPressedEvent();
        initHiddenEvent();
    }

    private void initHiddenEvent()
    {
        // clear search box when hiding primary stage
        GUI.getPrimaryStage().setOnHidden(event -> searchBox.clear());
    }

    private void initKeyPressedEvent()
    {
        GUI.getPrimaryStage().addEventHandler(KeyEvent.KEY_PRESSED, (event) -> {
            // Hide on escape
            if(KeyCode.ESCAPE.equals(event.getCode()))
            {
                GUIUtils.toggleShow();
            }
        });
    }

    public void handleSearchboxKeyTyped(KeyEvent keyEvent)
    {

    }

}
