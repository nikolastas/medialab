package dictionary;

public class UndersizeException extends Exception {
    public UndersizeException(String errorMessage){
//        System.out.println("THE DICTIONARY DOESNT MEET THE LIMITATIONS FOR 6 LETTERS WORDS");
        super(errorMessage);

    }
}
