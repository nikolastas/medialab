package UI;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;

public class chooseDictionaryBox {
    static String text="" ;
    public String open(String title, String message){
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        Label label = new Label();
        label.setText(message);

        //Create 2 buttons
        TextField bookID = new TextField("Set book ID");
        Button search = new Button("Search");

        search.setOnAction(e ->{
            text = bookID.getText();
            File myObj = new File("./Dictionaries/hangman_DICTIONARΥ - "+text+".txt");

            if (myObj.exists()){
                window.close();
            }
            else{
                AlertBox.display("Selected file doesnt exists", "please choose a file that exists");
            }
//            System.out.println(text);



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
