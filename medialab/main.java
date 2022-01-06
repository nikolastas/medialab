import UI.chooseDictionaryBox;
import UI.createDictionaryBox;
import UI.wordAndProp;
import game.object;
import game.valid;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

public class main extends Application {
    public Pane tableView = new Pane() ;
    public BorderPane pane = new BorderPane();
    public MenuItem start;
    public static dictionary.chooseBook book = new dictionary.chooseBook();
    public static void main(String[] args){
        book.initializeDictionary();
        launch(args);

//      gui.app(args);
//        dictionary.chooseBook book = new dictionary.chooseBook();
//        book.dictionary();
//        valid game = new valid();
//        game.run(book.words);
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = null;
        try{
            URL url = new File("medialab/UI/fx.fxml").toURI().toURL();
            root = FXMLLoader.load(url);
        } catch(Exception e){e.printStackTrace();}

        primaryStage.setTitle("Medialab Hangman");

//        tableView = this.tableView;
        Label label1 = new Label();
        label1.setText("new me ");
        tableView.getChildren().add(label1);
        pane.setCenter(tableView);
        primaryStage.setScene(new Scene(root, 800, 800));
        primaryStage.show();

    }
    public valid game = new valid();
    public void startGame(){
        game.initialize(book.words);
    }

    public void createDictionary(){
        UI.createDictionaryBox creator = new createDictionaryBox();
        String s = creator.open("Dictionary name", "Type your Book ID to create a dictionary");
        System.out.println(s);
       book.createDictionary(s);
    }
    public void loadDictionary(){
        UI.chooseDictionaryBox selector = new chooseDictionaryBox();
        String s = selector.open("Dictionary name", "Please type in your dictionary ID");

        book.getDictionary(s);
    }
    @FXML
    private void exitapp(){
        System.out.println("this should exit");
    }

    public ObservableList<UI.wordAndProp> getdata(){
        ObservableList<UI.wordAndProp> data = FXCollections.observableArrayList();
        HashMap< object , Float> map = game.getProp();
        List<String> selectedwords = game.getSelectedWords();
        int length = selectedwords.get(0).length();
        for(int i=0;i<length;i++){
            for(String s:selectedwords){
                char c = s.charAt(i);
                object myobject = new object(c, i);
                data.add( new wordAndProp().characterAndPlace(c, i, map.get(myobject) ) );
            }
        }
        return data;
    }


}
