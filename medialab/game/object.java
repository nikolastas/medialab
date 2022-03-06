package game;

public class object {
    private char text;
    private int number;
    public object( char text, int number){
        this.text = text;
        this.number = number;
    }
    public String getText() { return String.valueOf(text); }
    public char getTextChar(){ return text;}
    public void set(char text, int value) { this.text = text; this.number = value; }
    public String getNumber() { return String.valueOf(number); }
    public int getNumberInt(){
        return number;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        Character c = text;
        result = prime * result+ c.hashCode();
        Integer n = number;
        result = prime * result+ n.hashCode();
//        result = ((x + y) << 234234) % 21354205;
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        object that = (object) obj;
        return text == that.text && number == that.number;

    }
}
