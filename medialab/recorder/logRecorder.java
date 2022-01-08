package recorder;


public class logRecorder {
    private String word;
    private String winner;
    private Integer tries;
    public logRecorder(){
        this.word="WORD";
        this.winner="NONE";
        this.tries=-1;
    }
    public logRecorder(String wo, String wi, Integer t){
        this.tries=t;
        this.winner=wi;
        this.word=wo;
        createRecord();
    }
    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public Integer getTries() {
        return tries;
    }

    public void setTries(Integer tries) {
        this.tries = tries;
    }
    public void createRecord(){
        write w = new write();
        w.createAndWriteFile(this.word, this.winner, this.tries);
    }
}
