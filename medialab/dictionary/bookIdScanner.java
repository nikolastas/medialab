package dictionary;
import java.util.*;

public class bookIdScanner {
    String ID;
    public void IdScanner(){
        Scanner myObj = new Scanner(System.in);
        String bookID = myObj.nextLine();  // Read user input

        ID = bookID;
        
    }
    public void print(){
        System.out.println(ID);
    }
}
