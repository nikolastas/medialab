package dictionary;
import java.util.*;

public class bookIdScanner {
    private String ID;
    public void IdScanner(){
        Scanner myObj = new Scanner(System.in);

        ID = myObj.nextLine();
        
    }
    public String getID(){ return ID ;}
    public void setID(String a){ID = a;}
    public void printID(){
        System.out.println(ID);
    }
}
