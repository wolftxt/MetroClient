
import java.awt.Point;
import java.io.Serializable;

public class Message implements Serializable {

    // Message from Server to Client:  
    private int[] trains;
    private int[][] plan;
    private int[] cardInHand;
    private boolean yourTurn;
    private int[] scores;
    private int index;
    private int time;

    private String text;

    public Message(int[] trains, int[][] plan, int[] cardInHand, boolean yourTurn, int[] scores, int index, String text, int time) {
        this.trains = trains;
        this.plan = plan;
        this.cardInHand = cardInHand;
        this.yourTurn = yourTurn;
        this.scores = scores;
        this.index = index;
        this.text = text;
        this.time = time;
    }

    public int[] getTrains() {
        return trains;
    }

    public int[][] getPlan() {
        return plan;
    }

    public int[] getCardInHand() {
        return cardInHand;
    }

    public boolean isYourTurn() {
        return yourTurn;
    }

    public int[] getScores() {
        return scores;
    }

    public int getIndex() {
        return index;
    }

    public String getText() {
        return text;
    }

    public int getTime() {
        return time;
    }

    // Message from Client to Server
    private Point tile;
    private boolean drawFromDeck;

    public Message(Point tile, boolean drawFromDeck) {
        this.tile = tile;
        this.drawFromDeck = drawFromDeck;
    }

    public Message(boolean drawFromDeck) {
        this.drawFromDeck = drawFromDeck;
    }

    public Point getTile() {
        return tile;
    }

    public boolean isDrawFromDeck() {
        return drawFromDeck;
    }
}
