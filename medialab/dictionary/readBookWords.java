package dictionary;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.*;

public class readBookWords {
    public String[] words;
    public List<Float> wordsData;
    public void readFile(String book) throws FileNotFoundException {
        List<String> res = new ArrayList<>();

            File myObj = new File("./Dictionaries/hangman_DICTIONARΥ - "+book+".txt");
            Scanner myReader = new Scanner(myObj);

            wordsData = Arrays.asList(new Float[]{0f, 0f, 0f});
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
//                System.out.println(data);
                res.add(data);
                String word =data;
                if(word.length() == 6){
                    wordsData.set(0, wordsData.get(0)+1f);
                }
                else if(word.length()<=9){
                    wordsData.set(1,wordsData.get(1)+1f);
                }
                else{
                    wordsData.set(2,wordsData.get(2)+1f);
                }

            }




        String [] newWords = new String[res.size()];
        int c=0;
        for(String word: res){
            newWords[c]=word;
            c++;
        }
        wordsData.set(0, wordsData.get(0)/((float) c));
        wordsData.set(1, wordsData.get(1)/((float) c));
        wordsData.set(2, wordsData.get(2)/((float) c));
        words = newWords;
    }
}
