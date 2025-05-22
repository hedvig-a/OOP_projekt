package ui.dialogs;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ui.components.Buttons;
import ui.utils.DialogUtils;
import javafx.scene.paint.Color;

public class AlertBox {

    public static void show(Stage owner, String title, String message) {
        Stage dialog = new Stage(StageStyle.TRANSPARENT);
        dialog.initOwner(owner);
        dialog.initModality(javafx.stage.Modality.APPLICATION_MODAL);
        dialog.setTitle(title);

        VBox vbox = new VBox(15);
        vbox.setPadding(new Insets(25));
        vbox.setAlignment(Pos.CENTER);
        vbox.setStyle(DialogUtils.DIALOG_BG_STYLE +
                "-fx-border-color: #995FA3; -fx-border-width: 2;");

        Label label = DialogUtils.createLabel(message);
        label.setWrapText(true);

        javafx.scene.control.Button okBtn = Buttons.create("OK", e -> dialog.close());

        HBox buttonBox = new HBox(okBtn);
        buttonBox.setAlignment(Pos.CENTER);

        vbox.getChildren().addAll(label, buttonBox);

        Scene scene = new Scene(vbox);
        scene.setFill(Color.TRANSPARENT);

        dialog.setScene(scene);
        dialog.showAndWait();
    }
}
