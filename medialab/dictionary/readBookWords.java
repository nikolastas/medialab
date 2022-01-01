package dictionary;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.*;

public class readBookWords {
    public String[] words;
    public void readFile(String book) throws FileNotFoundException {
        List<String> res = new ArrayList<>();

            File myObj = new File("./Dictionaries/hangman_DICTIONARΥ - "+book+".txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
                res.add(data);

            }
            myReader.close();


        String [] newWords = new String[res.size()];
        int c=0;
        for(String word: res){
            newWords[c]=word;
            c++;
        }
        words = newWords;
    }
}
