package ui.utils;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class DialogUtils {
    public static final String DIALOG_BG_STYLE = """
    -fx-background-color: #F7C6C7;
    -fx-padding: 20;
    -fx-background-radius: 15;
    -fx-border-color: #995FA3;
    -fx-border-width: 3;
    -fx-border-radius: 15;
""";
    public static final String LABEL_STYLE = "-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #3D1766;";
    public static final String TEXT_STYLE = "-fx-font-size: 14px; -fx-text-fill: #3D1766;";

    public static Stage createDialogStage(Stage owner, String title) {
        Stage stage = new Stage(StageStyle.TRANSPARENT);
        stage.initOwner(owner);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        return stage;
    }

    public static VBox createDialogVBox() {
        VBox vbox = new VBox(12);
        vbox.setAlignment(Pos.CENTER_LEFT);
        vbox.setStyle(DIALOG_BG_STYLE);
        vbox.setPadding(new Insets(20));
        return vbox;
    }

    public static Label createLabel(String text) {
        Label label = new Label(text);
        label.setStyle(LABEL_STYLE);
        return label;
    }
}
