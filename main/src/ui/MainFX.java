package ui;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ui.dialogs.AlertBox;

public class MainFX extends Application {

    private static final String BUTTON_STYLE_BASE = """
        -fx-background-color: #995FA3;
        -fx-text-fill: white;
        -fx-font-weight: bold;
        -fx-background-radius: 10;
        """;

    @Override
    public void start(Stage primaryStage) {
        Scene mainScene = createMainScene(primaryStage);
        primaryStage.setScene(mainScene);
        primaryStage.setTitle("Relationship Diary");
        primaryStage.setMinWidth(900);
        primaryStage.setMinHeight(600);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    public Scene createMainScene(Stage primaryStage) {
        Label welcomeLabel = new Label("Hi! Welcome to your relationship diary!");
        Label questionLabel = new Label("What would you like to do?");

        Button manageEntriesButton = new Button("Manage Entries");
        Button manageIdeasButton = new Button("Manage Ideas");
        Button quitButton = new Button("Quit");

        Button[] buttons = {manageEntriesButton, manageIdeasButton, quitButton};

        HBox buttonRow = new HBox();
        buttonRow.getChildren().addAll(buttons);
        buttonRow.setAlignment(Pos.CENTER);

        VBox centerBox = new VBox();
        centerBox.getChildren().addAll(questionLabel, buttonRow);
        centerBox.setAlignment(Pos.CENTER);

        VBox contentBox = new VBox();
        contentBox.getChildren().addAll(welcomeLabel, centerBox);
        contentBox.setAlignment(Pos.TOP_CENTER);

        VBox root = new VBox(contentBox);
        root.setAlignment(Pos.TOP_CENTER);
        root.setStyle("-fx-background-color: linear-gradient(to right, #A7C7E7, #F7C6C7);");

        Scene scene = new Scene(root, 1000, 800);

        welcomeLabel.styleProperty().bind(
                Bindings.concat("-fx-font-size: ", scene.heightProperty().divide(20).asString(), "px;")
        );
        questionLabel.styleProperty().bind(
                Bindings.concat("-fx-font-size: ", scene.heightProperty().divide(30).asString(), "px;")
        );

        contentBox.spacingProperty().bind(scene.heightProperty().multiply(0.1));
        centerBox.spacingProperty().bind(scene.heightProperty().multiply(0.06));
        buttonRow.spacingProperty().bind(scene.widthProperty().multiply(0.02));
        centerBox.paddingProperty().bind(Bindings.createObjectBinding(() ->
                new Insets(scene.heightProperty().get() * 0.05, 0, 0, 0), scene.heightProperty()));
        root.paddingProperty().bind(Bindings.createObjectBinding(() ->
                new Insets(scene.heightProperty().get() * 0.15, 0, 0, 0), scene.heightProperty()));

        for (Button btn : buttons) {
            btn.styleProperty().bind(Bindings.concat(
                    BUTTON_STYLE_BASE,
                    "-fx-font-size: ",
                    scene.heightProperty().divide(60).asString(),
                    "px;"));
            btn.prefWidthProperty().bind(scene.widthProperty().multiply(0.12));
            btn.prefHeightProperty().bind(scene.heightProperty().multiply(0.045));
        }

        manageEntriesButton.setOnAction(e -> AlertBox.show(primaryStage, "Manage Entries", "Opening Manage Entries GUI... (implement this)"));
        manageIdeasButton.setOnAction(e -> {
            ManageIdeasUI ideasUI = new ManageIdeasUI();
            Scene ideaScene = new Scene(ideasUI.createManageIdeasPane(primaryStage), 1000, 800);
            primaryStage.setScene(ideaScene);
        });
        quitButton.setOnAction(e -> primaryStage.close());

        return scene;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
