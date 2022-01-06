package UI;

public class wordAndProp {
    private char character;
    private int place;
    private float prop;

    public wordAndProp characterAndPlace(){
        this.character=0;
        this.place=-1;
        this.prop=0;
        return null;
    }
    public wordAndProp characterAndPlace(char c, int i, float f){
        this.character=c;
        this.prop=f;
        this.place=i;
        return null;
    }
    public void show(){

    }

    public Float getProp() {
        return prop;
    }

    public void setProp(Float prop) {
        this.prop = prop;
    }

    public Integer getPlace() {
        return place;
    }

    public void setPlace(Integer place) {
        this.place = place;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }
}
