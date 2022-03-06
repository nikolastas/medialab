package game;

import java.util.*;

public class game{
    private Integer leng_of_dict;
    private String word;
    private String[] words;// read from validBook
    public List<String> selectedWords = new ArrayList<>();
    public HashMap< object , Float> propability = new HashMap<>();
    public void randomselect(){
        Random rand = new Random();
        int random_int = rand.nextInt(leng_of_dict);
        word=words[random_int];
    }
    public void selectSpecific(String word){
        this.word = word;
    }
    public void init(String[] w){
        words =w;
        leng_of_dict=w.length;

    }
    public void initializePropabilities() {

        selectedWords= new ArrayList<>();
        //open file requirelectedWo
        for (String s : words) {
            if (word.length() == s.length()) {
                selectedWords.add(s); //https://www.codegrepper.com/code-examples/java/java+string+array+add+element
            }
        }
        calcPropabilities();
    }
    public void calcPropabilities(){
        leng_of_dict=selectedWords.size();
        for (int i=0; i<word.length() ; i++){

            for(String s:selectedWords){
                char c = s.charAt(i);
                object myobject = new object(c, i);
                myobject.set(c, i);

                propability.merge(myobject, (1.0f) / ((float) leng_of_dict), Float::sum);

            }
        }
    }
    public String getWord(){
        return word;
    }
    public int getLen(){
        return word.length();
    }
    public int refactorPoint(char c, int position){
        int result= 0;

        if(word.charAt(position) == c){
            object o = new object(c, position);
            result = points(this.propability.get(o));
            for (Iterator<String> iterator = selectedWords.iterator(); iterator.hasNext(); ) {
                char value = iterator.next().charAt(position);
                if (value!=c) {
                    iterator.remove();
                }
            }
            this.propability.clear();
            calcPropabilities();
        }
        else{
            for (Iterator<String> iterator = selectedWords.iterator(); iterator.hasNext(); ) {
                char value = iterator.next().charAt(position);
                if (value==c) {
                    iterator.remove();
                }
            }
//            for(int i=0;i){
//                System.out.println("word= "+w +" position= "+position);
//                if(w.charAt(position) == c){
//                    selectedWords.remove(w);
//                }
//                  THROWS A JAVA ERROR
//            }
            this.propability.clear();
            calcPropabilities();
            System.out.println(selectedWords);
        }

        return result;
    }
    public int points(float n){
        if(n>= 0.6f){
            return 5;
        }
        else if (n >=0.4f){
            return 10;
        }
        else if (n >= 0.25){
            return 15;
        }
        else{
            return 30;
        }

    }

}

//city
//goal
//logo
//word
//
