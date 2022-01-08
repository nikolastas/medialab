package UI;

import game.object;
import game.valid;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.geometry.Rectangle2D;

import java.net.URL;
import java.util.*;

public class controller implements Initializable {

    @FXML
    public Label wordsInDictionary;
    @FXML
    public Label gamePoints;
    @FXML
    public Label charactersFound;
    @FXML
    public TableView tableView= new TableView<>();
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
    public ImageView imageView;
    @FXML
    public Label wordShow;
    public String valueOFWordShow;
    public void startGame(){
        game = new valid();
        game.initialize(book.words);
        gamePoints.setText(String.valueOf(game.getPoints()));
        wrongCharacters="";
        wrongWords.setText(wrongCharacters);
        valueOFWordShow = game.getWord().replaceAll("[a-zA-Z0-9]", "_ ");
        wordShow.setText(valueOFWordShow);
        recalc();

    }
    @FXML


    public void createDictionary(){
        UI.createDictionaryBox creator = new createDictionaryBox();
        String s = creator.open("Dictionary name", "Type your Book ID to create a dictionary");
        System.out.println(s);
        if(s != null) {
            book.createDictionary(s);
        }
        else{
            System.out.println("I cant create this dictionary you gave me NULL");
        }
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

    public ObservableList<wordAndProp> getdata(){
        ObservableList<UI.wordAndProp> data = FXCollections.observableArrayList(
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
        wrongCharacters="";
        wrongWords.setText(wrongCharacters);
        book.initializeDictionary();
        game.initialize(book.words);
        valueOFWordShow = game.getWord().replaceAll("[a-zA-Z0-9]", "_ ");
        wordShow.setText(valueOFWordShow);
        gamePoints.setText(String.valueOf(game.getPoints()));

//        dropDownPlace = new ChoiceBox<>();
//        dropDownLetter = new ChoiceBox<>();
        dropDownData();
//        dropDownPlace.setOnAction(this::chooseDropDownLetter);
//        dropDownLetter.setOnAction(this::chooseDropDownPlace);
        Rectangle2D rectangle2D = new Rectangle2D(0, 7, 102, 100);
        imageView.setViewport(rectangle2D);
        charactersFound.setText(String.valueOf(game.getCharachters_found()));
        wordsInDictionary.setText(String.valueOf(game.getSelectedWords().size()));

//        tableView = new TableView<>();
        tableView.setItems( getdata() );
        tableView.getColumns().addAll(getColums());


    }
    private void dropDownData() {
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

    public ArrayList<TableColumn> getColums(){
        ArrayList<TableColumn> c = new ArrayList<>();
        TableColumn<wordAndProp, String> nameColumn = new TableColumn<>("Character");
        nameColumn.setMinWidth(50);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Character"));
        c.add(nameColumn);
        TableColumn<UI.wordAndProp, Integer> placeColumn = new TableColumn<>("Place");
        placeColumn.setMinWidth(50);
        placeColumn.setCellValueFactory(new PropertyValueFactory<>("Place"));
        c.add(placeColumn);
        TableColumn<UI.wordAndProp, Float> propColumn = new TableColumn<>("Probability");
        propColumn.setMinWidth(50);
        propColumn.setCellValueFactory(new PropertyValueFactory<>("Prop"));
        c.add(propColumn);
        return c;
    }

    public void checkPAndL(ActionEvent actionEvent) {
        Integer oldValuePoints= game.getPoints();
        int n = dropDownPlace.getValue();

        char[] tmp = valueOFWordShow.toCharArray();
        if(tmp[2*n] == dropDownLetter.getValue()){
            UI.AlertBox alertBox = new AlertBox();
            alertBox.display("wrong Character!", "You have already selected this character for that place and it is right!");
        }
        else {
            game.run(dropDownLetter.getValue(), dropDownPlace.getValue());
            Integer newValuePoints = game.getPoints();
            if (newValuePoints > oldValuePoints) {

                tmp[2 * n] = dropDownLetter.getValue();
                valueOFWordShow = String.valueOf(tmp);
            } else {
                wrongCharacters += "c[" + dropDownPlace.getValue() + "]!=" + dropDownLetter.getValue() + ", ";
                wrongWords.setText(wrongCharacters);
            }
            recalc();
        }

    }
    private void recalc(){
        tableView.setItems( getdata() );
        gamePoints.setText(String.valueOf(game.getPoints()));
        wordShow.setText(valueOFWordShow);
        charactersFound.setText(String.valueOf(game.getCharachters_found()));
        wordsInDictionary.setText(String.valueOf(game.getSelectedWords().size()));
        Rectangle2D rectangle2D = new Rectangle2D(100* (6-game.getLifes()), 7, 102, 100);
        imageView.setViewport(rectangle2D);
    }

    public void chooseDropDownLetter(ActionEvent event) {
        System.out.println(dropDownPlace.getValue());
        dropDownLetter.getItems().clear();

        Set<Character> letters_Set = new HashSet<>();



        Integer i =dropDownPlace.getValue();
            for(String s: game.getSelectedWords()){
                letters_Set.add(s.charAt(i));


            }


        dropDownLetter.getItems().addAll(letters_Set);
    }

    public void chooseDropDownPlace(ActionEvent event) {
        System.out.println(dropDownLetter.getValue());
        dropDownPlace.getItems().clear();

        char w = dropDownLetter.getValue();
        Set<Integer> numbers_Set = new HashSet<>();

        for(int i=0; i<game.getSelectedWords().get(0).length() ; i++){
            for(String s: game.getSelectedWords()){
                if(w == s.charAt(i)){
                    numbers_Set.add(i);
                }


            }
        }
        dropDownPlace.getItems().addAll(numbers_Set);

    }

    public void showDictionary(ActionEvent event) {
        UI.showDataBox box = new showDataBox();

        box.display("Word Details", " ",book., 1);
    }

    public void showRounds(ActionEvent event) {
    }

    public void showSolution(ActionEvent event) {
    }
}
