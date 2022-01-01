package game;

import java.util.Scanner;

public class valid {
    private int lifes;
    private int charachters_found;
    private int points;
    public valid(){
    this.lifes=6;
    this.charachters_found=0;
    this.points=0;
}
public void run(String[] bookWords){
    game game = new game();
    game.init(bookWords);
//    game.randomselect();
    game.selectSpecific("DELIGHT");
    System.out.println("My selected word is: "+game.getWord());
    game.initializePropabilities();
    System.out.println(game.selectedWords);
    for (object o: game.propability.keySet()) {
        String key_char = o.getText();
        String key_char_place = o.getNumber();
        String value = game.propability.get(o).toString();
        System.out.println(key_char + key_char_place + " " + value);
    }
    visuallize vis = new visuallize();
    while((charachters_found >= 0 && charachters_found < game.getLen()) && lifes>0){

        System.out.println("Give me character...");
        Scanner sc = new Scanner(System.in);
        char c = sc.next().charAt(0);
        System.out.println("You gave: "+ c+ " for the "+getCharachters_found()+" character");
        int p = game.refactorPoint(c, getCharachters_found());
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
}
public int getCharachters_found(){
        return charachters_found;
}

}
