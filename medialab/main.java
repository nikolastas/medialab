import dictionary.*;
import game.*;
public class main {

    public static void main(String[] args){
        dictionary.chooseBook book = new dictionary.chooseBook();
        book.getdictionary();
        valid game = new valid();
        game.run(book.words);





    }
}
