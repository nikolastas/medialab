package game;

import java.util.HashMap;
import java.util.List;

public class valid {
    private int lifes;
    private int charachters_found;
    private int points;
    private final game game = new game();
    public valid(){
    this.lifes=6;
    this.charachters_found=0;
    this.points=0;
}
public void initialize(String[] bookWords){
    game.init(bookWords);
    game.randomselect();
//    game.selectSpecific("DELIGHT");
    System.out.println("My selected word is: "+game.getWord());
    game.initializePropabilities();
    System.out.println(game.selectedWords);
    for (object o: game.propability.keySet()) {
        String key_char = o.getText();
        String key_char_place = o.getNumber();
        String value = game.propability.get(o).toString();
//        System.out.println(key_char + key_char_place + " " + value);
    }
}
public void run(char c, int n){
    visuallize vis = new visuallize();
    if((charachters_found >= 0 && charachters_found < game.getLen()) && lifes>0){

        System.out.println("Give me character...");

        System.out.println("You gave: "+ c+ " for the "+n+" character");
        int p = game.refactorPoint(c, n);
        if(p>0){
            charachters_found+=1;
            points+=p;
            vis.getPointsWin(points, p, lifes);
        }
        else{
            lifes-=1;
            vis.getPointsLose(lifes);
        }
    }
    if(lifes<=0 ){
        System.out.println("the selected word was "+ game.getWord());
    }
}
public HashMap< object , Float> getProp(){
        return game.propability;
}
public List<String> getSelectedWords(){
        return game.selectedWords;
}
public int getCharachters_found(){
        return charachters_found;
}
public int getPoints(){
        return this.points;
}
public int getLifes(){
        return this.lifes;
}
public String getWord() {
    return game.getWord();
}
public void setLifes(Integer l){
        this.lifes=l;
}

}
