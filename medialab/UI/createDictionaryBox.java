package UI;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class createDictionaryBox {
    static String text;
    public String open(String title , String message){
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        window.setOnCloseRequest(e -> window.close());
        Label label = new Label();
        label.setText(message);

        //Create 2 buttons
        TextField bookID = new TextField("Create a dictionary from this book ID");
        Button search = new Button("Search");

        search.setOnAction(e ->{
            text = bookID.getText();
            window.close();
        });

        VBox layout =  new VBox(10);
        layout.getChildren().addAll(label, bookID, search);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        return text;

        //
    }
}
