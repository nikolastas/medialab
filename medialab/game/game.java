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
    public List<String> selectedWords = new ArrayList<String>();
    public HashMap< object, Float> propability = new HashMap<object, Float>();
    public void randomselect(){
        Random rand = new Random();
        int random_int = rand.nextInt(leng_of_dict);
        word=words[random_int];
    }
    public void init(String[] w){
        words =w;
        leng_of_dict=w.length;

    }
    public void initializePropabilities(){

        float value;

        //open file requirelectedWo
        for(int i=0; i< words.length; i++){
            if(word.length() == words[i].length() ){
                selectedWords.add(words[i]); //https://www.codegrepper.com/code-examples/java/java+string+array+add+element
            }
        }
        for (int i=0; i<word.length() ; i++){ //https://stackoverflow.com/questions/196830/what-is-the-easiest-best-most-correct-way-to-iterate-through-the-characters-of-a
            char c = word.charAt(i);
            object myobject = new object();
            myobject.set(c, i);
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
        return leng_of_dict;
    }
    public int refactorPoint(char c, int position){
        int result= 0;

        if(word.charAt(position) == c){
            object myobject = new object();

            myobject.set(c, position);
            System.out.println("char is "+myobject.getText()+" position "+myobject.getNumber());
            System.out.println("p= "+ propability.get(myobject));
            result = points(propability.get(myobject));
        }

        return result;
    }
    public int points(Float f){
        float n = f.floatValue();
        System.out.println("propability is: "+n);
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
