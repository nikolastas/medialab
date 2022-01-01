package game;

import jdk.swing.interop.SwingInterOpUtils;

import java.util.*;
import java.lang.Integer;
import java.util.Random;

public class game{
    private Integer leng_of_dict;
    private String word;
    private int points;
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

        float value;

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
        for (int i=0; i<word.length() ; i++){ //https://stackoverflow.com/questions/196830/what-is-the-easiest-best-most-correct-way-to-iterate-through-the-characters-of-a
            char c = word.charAt(i);
            object myobject = new object(c, i);
//            myobject.set(c, i);
            propability.put(myobject, 0.0f);
            System.out.println("I have setted "+propability.get(myobject)+ " to:"+ myobject.getText()+myobject.getNumber());
            for(String s:selectedWords){
                char c1 = s.charAt(i);
                if(c == c1){
                    myobject.set(c, i);
//                    value =propability.get(myobject);
                    propability.put(myobject, propability.get(myobject)+ 1.0f );
                }
            }
        }
        for (object o: propability.keySet()) {
            float p = (float) leng_of_dict;
            System.out.println(p);
            propability.put(o, propability.get(o)/p );
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
        }
        else{
            for(String w : words){
                if(w.charAt(position) == c){
                    selectedWords.remove(w);
                }

            }
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
