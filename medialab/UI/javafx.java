package UI;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class javafx extends Application  {

    Stage window;
//    Scene scene1, scene2;
    Button button;
    public void app(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        // content inside windows = scene
        // stage = window
        window = primaryStage;
        window.setTitle("MediaLab Hangman");

        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });
        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        List<String> l = new ArrayList<>();
        l= List.of(new String[]{"Good", "Bad", "Ungly"});
        choiceBox.getItems().addAll(l);
        choiceBox.setValue("Good");

        //listen for selection changes
//        choiceBox.getSelectionModel().selectedItemProperty()

//        Button choiceButton = new Button();
//
//        choiceButton.setOnAction(e -> {
//        });
        //top menu---start---------------------
        HBox topMenu = new HBox();
        Button buttonA = new Button("File");
        Button buttonB = new Button("Edit");
        Button buttonC = new Button("View");

        topMenu.getChildren().addAll(buttonA, buttonB, buttonC);
        //top menu---end---------------------

        //left menu---start---------------------
        HBox leftMenu = new HBox();
        Button buttonD = new Button("D");
        Button buttonE = new Button("E");
        Button buttonF = new Button("F");

        leftMenu.getChildren().addAll(buttonD, buttonE, buttonF);
        //left menu---end---------------------

        button = new Button("Close Program?");
        button.setOnAction(e -> {

            closeProgram();
        });
        //tutorial 9- Grid
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        TextField input = new TextField();
        GridPane.setConstraints(input, 0,3);
        Button btn = new Button("get data");
        btn.setOnAction(e -> {
            isInt(input, input.getText());
        });
        GridPane.setConstraints(btn, 1,3);
        //Name Label
        Label nameLabel = new Label("Username:");
        GridPane.setConstraints(nameLabel, 0,0);

        //Name Input
        TextField nameInput = new TextField("Nikos");
        GridPane.setConstraints(nameInput, 1,0);

        //Password Label
        Label passLabel = new Label("Password:");
        GridPane.setConstraints(nameLabel, 0,1);

        //Password Input
        TextField passInput = new TextField();
        passInput.setPromptText("Type your Password");
        GridPane.setConstraints(passInput, 1,1);


        Button loginButton = new Button("Log In");
        GridPane.setConstraints(loginButton, 1,2);

        grid.getChildren().addAll(nameLabel, nameInput, passLabel, passInput, loginButton, input , btn);
        //
//        StackPane layout = new StackPane();
//        layout.getChildren().add(button);
//
//        BorderPane borderPane = new BorderPane();
//        borderPane.setTop(topMenu);
//        borderPane.setLeft(leftMenu);
//        borderPane.setBottom(layout);
//        borderPane.setCenter(grid);




        Scene scene = new Scene(grid, 300, 250);
        window.setScene(scene);
        window.show();


    }

    private void isInt(TextField input, String text) {
        try{
            int num = Integer.parseInt(text);
            System.out.println("you gave me integer: "+ num);
        }catch(NumberFormatException e){
            System.out.println("error no number given");
            e.printStackTrace();
        }
    }

    private void closeProgram() {
        Boolean close = ConfirmBox.display("Close application?","Press yes if you want to exit the application.");
        if(close){
            System.out.println("File is saved!");
            window.close();
        }

    }

}
