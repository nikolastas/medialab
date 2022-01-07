import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class main extends Application {
    public static void main(String[] args){
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("./UI/fx.fxml"));
        primaryStage.setTitle("Medialab Hangman");

        primaryStage.setScene(new Scene(root, 800, 800));
        primaryStage.show();

    }




}
