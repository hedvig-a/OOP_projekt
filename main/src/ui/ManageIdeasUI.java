package ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.IdeaGenerator;
import ui.components.Buttons;
import ui.dialogs.*;

import java.util.List;

public class ManageIdeasUI {
    private final IdeaGenerator ideaGenerator = new IdeaGenerator();

    public VBox createManageIdeasPane(Stage primaryStage) {
        // Inner content pane
        VBox ideasPane = new VBox(15);
        ideasPane.setAlignment(Pos.CENTER);
        ideasPane.setPadding(new Insets(25));

        Label title = new Label("Manage Ideas");
        title.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-text-fill: #3D1766;");

        List buttons = List.of(
                Buttons.create("Add New Idea", e -> AddIdeaDialog.show(primaryStage, ideaGenerator)),
                Buttons.create("Get Random Idea", e -> RandomIdeaDialog.show(primaryStage, ideaGenerator)),
                Buttons.create("View Saved Ideas", e -> ListDialog.show(primaryStage, "Saved Ideas", ideaGenerator.getFilteredIdeas("saved"))),
                Buttons.create("View Favorite Ideas", e -> ListDialog.show(primaryStage, "Favorite Ideas", ideaGenerator.getFavorites())),
                Buttons.create("Mark Idea as Favorite", e -> TextInputDialogBox.show(primaryStage, "Mark as Favorite", "Enter the activity name to favorite:", input -> {
                    ideaGenerator.markAsFavorite(input);
                    AlertBox.show(primaryStage, "Done", "Marked as favorite if found.");
                })),
                Buttons.create("Remove Idea", e -> TextInputDialogBox.show(primaryStage, "Remove Idea", "Enter the activity name to remove:", input -> {
                    ideaGenerator.removeIdea(input);
                    AlertBox.show(primaryStage, "Done", "Removed idea if found.");
                })),
                Buttons.create("Back to Main Menu", e -> {
                    MainFX mainFX = new MainFX();
                    primaryStage.setScene(mainFX.createMainScene(primaryStage));
                })
        );

        ideasPane.getChildren().add(title);
        ideasPane.getChildren().addAll(buttons);

        VBox root = new VBox(ideasPane);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: linear-gradient(to right, #A7C7E7, #F7C6C7);");
        root.setPadding(new Insets(50));

        return root;
    }
}
