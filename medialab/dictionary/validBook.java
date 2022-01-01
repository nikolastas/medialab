package dictionary;
import java.util.Locale;
import java.util.*;
import java.io.*;
public class validBook {
    public String replace (String word){
        return  word.replaceAll("[^a-zA-Z]", "");
    }
    public void replace(String [] str, String ID){
        bookID = ID;
        String[] res = new String[str.length];
        int c=0;
        for (String word:str){
            word = replace(word.toUpperCase());
            res[c]=word;
            c++;

//            System.out.println(word);
        }
        words = res;
    }
    public boolean limitations(){
        int counter=0;
        List<String> res = new ArrayList<>() ;

        Map<String, String> map = new HashMap<String, String>();
        for(String word : words){
            if (map.containsKey(word)){
//                System.out.println("THE DICTIONARY DOESNT MEET THE LIMITATIONS FOR WORD FREQUENCY");
                continue;
            }else {
                if(word.length() < 6){
                    System.out.println("THE DICTIONARY DOESNT MEET THE LIMITATIONS FOR 6 LETTERS WORDS");
                    continue;
                }
                if(word.length() >= 9){
                    counter++;
                }
                map.put(word,"yes");
                res.add(word);

            }
        }
        if(map.size() < 20){
            System.out.println("THE DICTIONARY DOESNT MEET THE LIMITATIONS FOR 20 VALID WORDS");
            return false;
        }
        if(map.size()/5 > counter ){
            System.out.println("THE DICTIONARY DOESNT MEET THE LIMITATIONS FOR 20% OF DICT VALID WORDS HAVE 9 LETTERS");
            return false;
        }
        String [] newWords = new String[res.size()];
        int c=0;
        for(String word: res){
            newWords[c]=word;
            c++;
        }
        words = newWords;
        return true;
    }
    public String getWords(){
        String str="";
        for (String s:words){
            str=str+" "+s;
        }
        return str;
    }
    private String[] words;
    private String bookID;
    public void createAndWriteFile(){
        String filename = "./Dictionaries/hangman_DICTIONARΥ - "+bookID+".txt";
        try{

        File f= new File(filename);
        if (f.createNewFile()) {
            System.out.println("File created: " + f.getName());
        } else {
            System.out.println("File already exists.");
        }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        try {
            FileWriter myWriter = new FileWriter(filename);
            for(String word : words){
                System.out.println(word);
                myWriter.write(word+"\n");
            }

            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
