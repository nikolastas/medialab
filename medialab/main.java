import UI.javafx;
import game.valid;
public class main {

    public static void main(String[] args){
        javafx gui = new javafx();
//        gui.app(args);
        dictionary.chooseBook book = new dictionary.chooseBook();
        book.getdictionary();
        valid game = new valid();
        game.run(book.words);





    }
}
