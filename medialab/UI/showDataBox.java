package UI;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class showDataBox() {
    public void display(String title, String message, List Data, Integer type){
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        Label label = new Label();
        label.setText(message);
        List<Label> elements = new ArrayList<>();
        VBox layout =  new VBox(10);
        Button cButton = new Button("Confirm");

        cButton.setOnAction(e -> {
            window.close();
        });
        switch (type) {
            case 1:
                Label sizeletterwords = new Label("Words with 6 letters: ");
                Label val0 = new Label(String.valueOf(Data.get(0)));
                Label nineletterwords = new Label("Words from 7 to 9 letters: ");
                Label val1 = new Label(String.valueOf(Data.get(1)));
                Label tenletterwords = new Label("Words with at least 10 letters: ");
                Label val2 = new Label(String.valueOf(Data.get(2)));
                layout.getChildren().addAll(label, sizeletterwords, val0, nineletterwords, val1, tenletterwords, val2 );

        }




        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

    }

}
