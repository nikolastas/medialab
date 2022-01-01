package game;

public class object {
    private char text;
    private int number;

    public String getText() { return String.valueOf(text); }
    public void set(char text, int value) { this.text = text; this.number = value; }
    public String getNumber() { return String.valueOf(number); }
}
