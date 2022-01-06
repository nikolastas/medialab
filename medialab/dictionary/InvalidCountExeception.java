package dictionary;



public class InvalidCountExeception extends Exception {
    public InvalidCountExeception(String errorMessage){
//        System.out.println("THE DICTIONARY DOESNT MEET THE LIMITATIONS FOR WORD FREQUENCY");
        super(errorMessage);
    }
}
