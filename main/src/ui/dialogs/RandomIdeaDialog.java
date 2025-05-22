package ui.dialogs;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Idea;
import model.IdeaGenerator;
import ui.components.Buttons;
import ui.utils.DialogUtils;

import java.util.List;
import java.util.Random;

public class RandomIdeaDialog {

    public static void show(Stage owner, IdeaGenerator generator) {
        List<Idea> ideas = generator.getFilteredIdeas("random");
        if (ideas.isEmpty()) {
            AlertBox.show(owner, "No Ideas", "No random ideas available.");
            return;
        }

        Idea randomIdea = ideas.get(new Random().nextInt(ideas.size()));
        Stage dialog = DialogUtils.createDialogStage(owner, "Random Idea");

        VBox vbox = new VBox(15);
        vbox.setAlignment(Pos.CENTER_LEFT);
        vbox.setPadding(new Insets(20));
        vbox.setStyle(DialogUtils.DIALOG_BG_STYLE);

        Label ideaLabel = new Label(randomIdea.toString());
        ideaLabel.setWrapText(true);
        ideaLabel.setStyle(DialogUtils.TEXT_STYLE);

        Button saveBtn = Buttons.create("Save Idea", e -> {
            boolean saved = generator.addIdea(randomIdea.getActivity(), randomIdea.getCategory(), randomIdea.getDescription());
            AlertBox.show(owner, saved ? "Saved" : "Error", saved ? "Idea saved successfully." : "Failed to save idea.");
            dialog.close();
        });

        Button closeBtn = Buttons.create("Close", e -> dialog.close());

        HBox buttons = new HBox(15, saveBtn, closeBtn);
        buttons.setAlignment(Pos.CENTER);

        vbox.getChildren().addAll(ideaLabel, buttons);

        dialog.setScene(new Scene(vbox, 450, 250));
        dialog.showAndWait();
    }
}
