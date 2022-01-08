
package dictionary;

import java.util.List;

public class chooseBook{
    bookIdScanner bookID = new bookIdScanner();
    public void initializeDictionary(){
        getDictionary("OL31390631M");
    }
    public readBookWords reader;
    public void getDictionary(String ID) {
//        System.out.println("please insert the ID dictionary of book, from https://openlibrary.org/");
//        bookID.IdScanner();
//        System.out.println("you gave me " + bookID.getID());
        try{

            reader = new readBookWords();

            reader.readFile(ID);
//            System.out.println("book already exists no need to create dictionary");
            words = reader.words;
        }catch(Exception e) {
            System.out.println(e);
        }
    }
    private validBook dict;
    public void createDictionary(String ID){
        try {
            System.out.println("book dictionary is now being created");
            jsonBook json = new jsonBook();
            json.jsonresult(ID);
//            System.out.println("the desc is: " + json.allBookDescription);
            dict = new validBook();
            dict.replace(json.bookDescription, ID);
            System.out.println("replace desc with: " + dict.getWords());
            if (dict.limitations()) {
                dict.createAndWriteFile();
            }
            readBookWords reader = new readBookWords();
            reader.readFile(ID);
            words = reader.words;
        }
        catch (Exception ex){
            System.err.println(ex);
            ex.printStackTrace();
        }
    }
    public void dictionary()  {
        System.out.println("please insert the ID dictionary of book, from https://openlibrary.org/");
        bookID.IdScanner();
        System.out.println("you gave me " + bookID.getID());
        try{

            readBookWords reader = new readBookWords();

            reader.readFile(bookID.getID());
            System.out.println("book already exists no need to create dictionary");
            words = reader.words;
        }
        catch(Exception e){
            try {
                System.out.println("book dictionary is now being created");
                jsonBook json = new jsonBook();
                json.jsonresult(bookID.getID());
                System.out.println("the desc is: " + json.allBookDescription);
                validBook dict = new validBook();
                dict.replace(json.bookDescription, bookID.getID());
                System.out.println("replace desc with: " + dict.getWords());
                if (dict.limitations()) {
                    dict.createAndWriteFile();
                }
                readBookWords reader = new readBookWords();
                reader.readFile(bookID.getID());
                words = reader.words;
            }
            catch (Exception ex){
                System.err.println(ex);
                ex.printStackTrace();
            }
        }


    }
    public List<Float> getDictinaryData(){
        return reader.wordsData;
    }
    public String[] words;
    
}