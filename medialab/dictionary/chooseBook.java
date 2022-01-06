
package dictionary;

public class chooseBook{
    
    public void getdictionary()  {
        
        System.out.println("please insert the ID dictionary of book, from https://openlibrary.org/");
    
        bookIdScanner bookID = new bookIdScanner();
        

        bookID.IdScanner();
        System.out.println("you gave me " + bookID.ID);
        try{

            readBookWords reader = new readBookWords();

            reader.readFile(bookID.ID);
            System.out.println("book already exists no need to create dictionary");
            words = reader.words;
        }
        catch(Exception e){
            try {
                System.out.println("book dictionary is now beeing created");
                jsonBook json = new jsonBook();
                json.jsonresult(bookID.ID);
                System.out.println("the desc is: " + json.allBookDescription);
                validBook dict = new validBook();
                dict.replace(json.bookDescription, bookID.ID);
                System.out.println("replace desc with: " + dict.getWords());
                if (dict.limitations()) {
                    dict.createAndWriteFile();
                }
                readBookWords reader = new readBookWords();
                reader.readFile(bookID.ID);
                words = reader.words;
            }
            catch (Exception ex){
                System.out.println(ex);
                ex.printStackTrace();
            }
        }


    }

    public String[] words;
    
}