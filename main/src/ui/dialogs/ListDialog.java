package ui.dialogs;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Idea;
import ui.components.Buttons;
import ui.utils.DialogUtils;

import java.util.List;
import java.util.stream.Collectors;

public class ListDialog {

    public static void show(Stage owner, String title, List<Idea> ideas) {
        Stage dialog = DialogUtils.createDialogStage(owner, title);

        VBox contentBox = new VBox(10);
        contentBox.setPadding(new Insets(15));
        contentBox.setStyle(DialogUtils.DIALOG_BG_STYLE);

        Label titleLabel = DialogUtils.createLabel(title);

        Label contentLabel = new Label(ideas.isEmpty() ? "No ideas found." :
                ideas.stream().map(Object::toString).collect(Collectors.joining("\n\n")));
        contentLabel.setWrapText(true);
        contentLabel.setStyle(DialogUtils.TEXT_STYLE);

        VBox textContainer = new VBox(contentLabel);
        textContainer.setPadding(new Insets(10));

        ScrollPane scrollPane = new ScrollPane(textContainer);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefHeight(300);

        Button closeBtn = Buttons.create("Close", e -> dialog.close());
        HBox buttonBox = new HBox(closeBtn);
        buttonBox.setAlignment(Pos.CENTER);

        contentBox.getChildren().addAll(titleLabel, scrollPane, buttonBox);
        Scene scene = new Scene(contentBox, 500, 450);
        dialog.setScene(scene);
        dialog.showAndWait();
    }
}
