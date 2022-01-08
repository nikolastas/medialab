package dictionary;
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
    public boolean limitations() {
        int counter=0;

        List<String> res = new ArrayList<>() ;
        Map<String, String> map = new HashMap<>();
        try{
            for(String word : words){
                if (map.containsKey(word)){

//                    throw new InvalidCountExeception("THE DICTIONARY DOESNT MEET THE LIMITATIONS FOR WORD FREQUENCY");
//                System.out.println("THE DICTIONARY DOESNT MEET THE LIMITATIONS FOR WORD FREQUENCY");
//                    if this would throw error the program would stop :(

                }else {
                    if(word.length() < 6){
                        continue;
//                        throw new InvalidRangeException("THE DICTIONARY DOESNT MEET THE LIMITATIONS FOR 6 LETTERS WORDS");
//                        System.out.println("THE DICTIONARY DOESNT MEET THE LIMITATIONS FOR 6 LETTERS WORDS");
//                        if this would throw error the program would stop :(

                    }

                    if(word.length() >= 9){
                        counter++;
                    }
                    map.put(word,"yes");
                    res.add(word);

                }
            }
            if(map.size() < 20){
//                continue;
                throw new UndersizeException("THE DICTIONARY DOESNT MEET THE LIMITATIONS FOR 20 VALID WORDS");
//                System.out.println("THE DICTIONARY DOESNT MEET THE LIMITATIONS FOR 20 VALID WORDS");

            }
            if(map.size()/5 > counter ){
                throw new UnbalancedException("THE DICTIONARY DOESNT MEET THE LIMITATIONS FOR 20% OF DICT VALID WORDS HAVE 9 LETTERS");
//                System.out.println("THE DICTIONARY DOESNT MEET THE LIMITATIONS FOR 20% OF DICT VALID WORDS HAVE 9 LETTERS");

            }
        }catch (Exception e){
            System.err.println(e);
            e.printStackTrace();
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
        StringBuilder str= new StringBuilder();
        for (String s:words){
            str.append(" ").append(s);
        }
        return str.toString();
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
