package ui.dialogs;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ui.components.Buttons;
import ui.utils.DialogUtils;

import java.util.function.Consumer;

public class TextInputDialogBox {

    public static void show(Stage owner, String title, String header, Consumer<String> onConfirm) {
        Stage dialog = DialogUtils.createDialogStage(owner, title);

        VBox vbox = new VBox(12);
        vbox.setPadding(new Insets(20));
        vbox.setStyle(DialogUtils.DIALOG_BG_STYLE);
        vbox.setAlignment(Pos.CENTER_LEFT);

        Label label = DialogUtils.createLabel(header);
        TextField input = new TextField();

        Button confirmBtn = Buttons.create("Confirm", e -> {
            String text = input.getText().trim();
            if (text.isEmpty()) {
                AlertBox.show(owner, "Error", "Input cannot be empty.");
                return;
            }
            onConfirm.accept(text);
            dialog.close();
        });

        Button cancelBtn = Buttons.create("Cancel", e -> dialog.close());

        HBox buttons = new HBox(15, confirmBtn, cancelBtn);
        buttons.setAlignment(Pos.CENTER);

        vbox.getChildren().addAll(label, input, buttons);

        dialog.setScene(new Scene(vbox, 400, 200));
        dialog.showAndWait();
    }
}
