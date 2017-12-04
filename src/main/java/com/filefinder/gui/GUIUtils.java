package com.filefinder.gui;

import javafx.application.Platform;
import javafx.scene.Node;

public class GUIUtils {

    public static void toggleShow()
    {
        if(GUI.getPrimaryStage().isShowing()) {
            Platform.runLater(() -> GUI.getPrimaryStage().hide());
        }
        else{
            Platform.runLater(() -> GUI.getPrimaryStage().show());
        }
    }

    public static void repeatFocus(Node node) {
        if (!node.isFocused()) {
            node.requestFocus();
            repeatFocus(node);
        }
    }
}
