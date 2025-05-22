package ui.components;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class Buttons {
    private static final String STYLE = """
        -fx-background-color: #995FA3;
        -fx-text-fill: white;
        -fx-font-weight: bold;
        -fx-background-radius: 10;
        -fx-font-size: 14px;
    """;

    public static Button create(String text, EventHandler<ActionEvent> action) {
        Button button = new Button(text);
        button.setStyle(STYLE);
        button.setPrefWidth(240);
        button.setPrefHeight(45);
        button.setOnAction(action);
        return button;
    }
}
