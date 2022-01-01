package game;

public class visuallize {

    public void getPointsWin(int points, int p, int lifes){
        points+=p;
        System.out.println("Found the character  and the rightplace!");
        System.out.println("Points: "+points+" lifes: "+lifes);
    }
    public void getPointsLose(int lifes){

        if (lifes>0){
            System.out.println("You still have :"+ lifes);

        }
        else{
            System.out.println("visualizer said: You have  lost :(");
        }
    }
}
