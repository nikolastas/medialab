package UI;

import game.object;
import game.valid;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import recorder.logRecorder;

import java.net.URL;
import java.util.*;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class controller implements Initializable {
    public int howManyTries=0;
    @FXML
    public Label wordsInDictionary;
    @FXML
    public Label gamePoints;
    @FXML
    public Label charactersFound;
    @FXML
    public TableView<Object> tableView= new TableView<>();
    @FXML
    public ChoiceBox<Integer> dropDownPlace;
    @FXML
    public ChoiceBox<Character> dropDownLetter;
    @FXML
    public Button checkPlaceAndLetter;
    public HBox hbox ;
    public BorderPane pane = new BorderPane();
    @FXML
    public Label wrongWords;

    private String wrongCharacters;
    public static valid game = new valid();
    public static dictionary.chooseBook book = new dictionary.chooseBook();
    @FXML
    private VBox vbox;
    @FXML
    private MenuItem closeButton;
    @FXML
    public ImageView imageView;
    @FXML
    public Label wordShow;
    public String valueOFWordShow;
    public void startGame(){
        howManyTries=0;
        game = new valid();
        game.initialize(book.words);
        gamePoints.setText(String.valueOf(game.getPoints()));
        wrongCharacters="";
        wrongWords.setText(wrongCharacters);
        valueOFWordShow = game.getWord().replaceAll("[a-zA-Z0-9]", "_ ");
        wordShow.setText(valueOFWordShow);
        dropDownData();
        recalc();

    }
    @FXML


    public void createDictionary(){
        UI.createDictionaryBox creator = new createDictionaryBox();
        String s = creator.open("Dictionary name", "Type your Book ID to create a dictionary");
        System.out.println(s);
        if(s != null) {
            try {
                book.createDictionary(s);

            }
            catch(Exception e) {
                e.printStackTrace();
                AlertBox.display("error", e.getMessage());
            }

        }
        else{
            System.out.println("I cant create this dictionary you gave me NULL");
        }
    }

    public void loadDictionary(){
        UI.chooseDictionaryBox selector = new chooseDictionaryBox();
        String s = selector.open("Dictionary name", "Please type in your dictionary ID");
        System.out.println(s);
        if(s != null) {
            try {
                book.getDictionary(s);
                startGame();
            } catch (Exception e) {
                e.printStackTrace();
                AlertBox.display("error", e.getMessage());
            }
        }
        else{
            System.out.println("I cant load this dictionary you gave me NULL");
        }

    }
    @FXML
    private void exitapp(){
        Stage stage = (Stage) vbox.getScene().getWindow();
        // do what you have to do
        stage.close();
        System.out.println("this should exit");
    }

    public ObservableList<Object> getdata(){
        ObservableList<Object> data = FXCollections.observableArrayList(
//                new wordAndProp('c', 1, 0.0f)
        );
        HashMap<object, Float> map = game.getProp();
        List<String> selectedwords = game.getSelectedWords();
        int length = selectedwords.get(0).length();
        for(int i=0;i<length;i++){
            for(String s:selectedwords){
                char c = s.charAt(i);
                object myobject = new object(c, i);
                data.add( new wordAndProp(c, i, map.get(myobject) ) );
            }
        }
        return data;
    }
    @Override
    public void initialize(URL url, ResourceBundle resource){
        dropDownPlace.setValue(null);
        dropDownLetter.setValue(null);
        wrongCharacters="";
        wrongWords.setText(wrongCharacters);
        book.initializeDictionary();
        game.initialize(book.words);
        valueOFWordShow = game.getWord().replaceAll("[a-zA-Z0-9]", "_ ");
        wordShow.setText(valueOFWordShow);
        gamePoints.setText(String.valueOf(game.getPoints()));

        dropDownData();
        Rectangle2D rectangle2D = new Rectangle2D(0, 7, 102, 100);
        imageView.setViewport(rectangle2D);
        charactersFound.setText(String.valueOf(game.getCharachters_found()));
        wordsInDictionary.setText(String.valueOf(game.getSelectedWords().size()));

//        tableView = new TableView<>();
        tableView.setItems( getdata() );
        tableView.getColumns().addAll(getColums());


    }
    private void dropDownData() {
        dropDownPlace.getItems().clear();
        dropDownLetter.getItems().clear();
        Set<Character> letters_Set = new HashSet<>();
        Set<Integer> numbers_Set = new HashSet<>();

        for(int i=0; i<game.getSelectedWords().get(0).length() ; i++){
            for(String s: game.getSelectedWords()){
                letters_Set.add(s.charAt(i));
                numbers_Set.add(i);

            }
        }
        dropDownPlace.getItems().addAll(numbers_Set);
        dropDownLetter.getItems().addAll(letters_Set);

    }

    public ArrayList<TableColumn<Object, ?>> getColums(){
        ArrayList<TableColumn<Object, ?>> c = new ArrayList<>();
        TableColumn<Object, Object> nameColumn = new TableColumn<>("Character");
        nameColumn.setMinWidth(50);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Character"));
        c.add(nameColumn);
        TableColumn<Object, Object> placeColumn = new TableColumn<>("Place");
        placeColumn.setMinWidth(50);
        placeColumn.setCellValueFactory(new PropertyValueFactory<>("Place"));
        c.add(placeColumn);
        TableColumn<Object, Object> propColumn = new TableColumn<>("Probability");
        propColumn.setMinWidth(50);
        propColumn.setCellValueFactory(new PropertyValueFactory<>("Prop"));
        c.add(propColumn);
        return c;
    }

    public void checkPAndL() {
        howManyTries++;
        int oldValuePoints= game.getPoints();
        int n = dropDownPlace.getValue();

        char[] tmp = valueOFWordShow.toCharArray();
        if(tmp[2*n] == dropDownLetter.getValue()){
//            UI.AlertBox alertBox = new AlertBox();
            AlertBox.display("wrong Character!", "You have already selected this character for that place and it is right!");
        }
        else {
            game.run(dropDownLetter.getValue(), dropDownPlace.getValue());
            int newValuePoints = game.getPoints();
            if (newValuePoints > oldValuePoints) {

                if(game.getSelectedWords().size() == 1){
                    valueOFWordShow = game.getWord();
                    valueOFWordShow.replaceAll(".(?=.)", "$0 ");
//                    wordShow.setText(selectedWord);
                    wrongWords.setText("You WIN");
                }
                else {
                    tmp[2 * n] = dropDownLetter.getValue();
                    valueOFWordShow = String.valueOf(tmp);
                }
            } else {
                wrongCharacters += "c[" + dropDownPlace.getValue() + "]!=" + dropDownLetter.getValue() + ", ";
                wrongWords.setText(wrongCharacters);

            }
            recalc();
        }
        if(game.getLifes()<=0){
            valueOFWordShow = game.getWord();
            valueOFWordShow.replaceAll(".(?=.)", "$0 ");
            wordShow.setText(valueOFWordShow);
            wrongWords.setText("You LOST");
        }

    }
    private void recalc() {

        wrongWords.setMaxWidth(120);
        wrongWords.setWrapText(true);
        tableView.setItems(getdata());
        gamePoints.setText(String.valueOf(game.getPoints()));
        wordShow.setText(valueOFWordShow);
        charactersFound.setText(String.valueOf(game.getCharachters_found()));
        wordsInDictionary.setText(String.valueOf(game.getSelectedWords().size()));
        Rectangle2D rectangle2D = new Rectangle2D(100* (6-game.getLifes()), 7, 102, 100);
        imageView.setViewport(rectangle2D);

    }

    public void chooseDropDownLetter() {
        System.out.println(dropDownPlace.getValue());
        dropDownLetter.getItems().clear();

        Set<Character> letters_Set = new HashSet<>();

        Integer i;
        try{
            i = dropDownPlace.getValue();
            for(String s: game.getSelectedWords()){
                letters_Set.add(s.charAt(i));
            }
        }catch (Exception e){
            dropDownPlace.setValue(0);
            i = 0;
            for(String s: game.getSelectedWords()){
                letters_Set.add(s.charAt(i));
            }
        }



        dropDownLetter.getItems().addAll(letters_Set);
    }

    public void showDictionary() {
        UI.showDataBox box = new showDataBox();

        box.display("Word Details", " ",book.getDictinaryData(), 1);
    }

    public void showRounds() {
        UI.showDataBox box = new showDataBox();

        box.display("Round Details", " You can see the latest 5 games details",book.getDictinaryData(), 0);
    }

    public void showSolution() {
        valueOFWordShow = game.getWord();
        valueOFWordShow.replaceAll(".(?=.)", "$0 ");
        wordShow.setText(valueOFWordShow);
        wrongWords.setText("You LOST");
        game.setLifes(0);
        recorder.logRecorder recorder = new logRecorder(game.getWord(), "Computer", howManyTries);
    }
}
