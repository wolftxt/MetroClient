
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class MetroWidget extends JComponent {

    private Card card = new Card();
    private BufferedImage boardImage;
    private int[] trains;
    private int[][] plan;
    private int[] inventory;

    private Point selected;

    // Variables used for component painting
    private int xOffset;
    private int width;
    private int height;
    private int xStart;
    private int yStart;
    private int cardWidth;
    private int cardHeight;

    // Constants for component painting
    private final double start = 0.117465224111283; // Space betwen border and where the cards are placed
    private final double trainLength = 0.034732272069465;
    private final double trainWidth = 0.020260492040521;
    private final double spaceBetween = 0.095;

    private void updateVariables() {
        xOffset = this.getWidth() - this.getHeight();
        width = this.getWidth() - xOffset;
        height = this.getHeight();
        xStart = (int) (width * start);
        yStart = (int) (height * start);
        cardWidth = (int) (width * (1 - (2 * start)) / 8);
        cardHeight = (int) (height * (1 - (2 * start)) / 8);
    }

    public MetroWidget() {
        try {
            boardImage = ImageIO.read(MetroWidget.class.getResourceAsStream("/resources/board.png"));
        } catch (IOException ex) {
            throw new RuntimeException("Error while loading the board image.");
        }
    }

    public void setPlan(Message message) {
        if (message.getTrains() == null || message.getPlan() == null || message.getCardInHand() == null) {
            this.plan = null;
            return;
        }
        this.trains = message.getTrains();
        this.plan = message.getPlan();
        this.inventory = message.getCardInHand();
    }

    public void setSelected(int x, int y) {
        updateVariables();
        if (x < xStart || x > xStart + cardWidth * 8) {
            return;
        }
        if (y < xStart || y > yStart + cardHeight * 8) {
            return;
        }
        int newX = (x - xStart) / cardWidth;
        int newY = (y - yStart) / cardHeight;
        if (newX >= 3 && newX <= 4 && newY >= 3 && newY <= 4) {
            return;
        }
        selected = new Point(newX, newY);
        this.repaint();
    }

    public Point getSelected() {
        return selected;
    }

    public static Color getColor(int playerIndex) {
        switch (playerIndex) {
            case 0 -> {
                return Color.yellow;
            }
            case 1 -> {
                return Color.blue;
            }
            case 2 -> {
                return new Color(255, 140, 0, 255); 
            }
            case 3 -> {
                return Color.green.darker();
            }
            case 4 -> {
                return new Color(102, 51, 153, 255); // purple
            }
            case 5 -> {
                return Color.black;
            }

            default ->
                throw new AssertionError();
        }
    }

    public static String getColorText(int playerIndex) {
        switch (playerIndex) {
            case 0 -> {
                return "Yellow";
            }
            case 1 -> {
                return "Blue";
            }
            case 2 -> {
                return "Orange";
            }
            case 3 -> {
                return "Green";
            }
            case 4 -> {
                return "Purple";
            }
            case 5 -> {
                return "Black";
            }
            default ->
                throw new AssertionError();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        updateVariables();
        g.drawImage(boardImage, 0, 0, width, height, this);
        if (plan == null) {
            return;
        }
        // Draw cards on the board
        g.drawImage(boardImage, 0, 0, width, height, this);
        for (int x = 0; x < plan.length; x++) {
            for (int y = 0; y < plan[0].length; y++) {
                if (LegalMoves.isMoveLegal(inventory, x, y, plan)) {
                    g.setColor(new Color(0, 255, 0, 32));
                    g.fillRect(xStart + x * cardWidth, yStart + y * cardHeight, cardWidth, cardHeight);
                }
                if (plan[x][y] == -1) {
                    continue;
                }
                g.drawImage(card.getCard(plan[x][y]), xStart + x * cardWidth, yStart + y * cardHeight, cardWidth, cardHeight, this);
            }
        }
        // Highlight the selected card
        if (selected != null) {
            g.setColor(new Color(0, 255, 0, 128));
            g.fillRect(xStart + selected.x * cardWidth, yStart + selected.y * cardHeight, cardWidth, cardHeight);
        }
        // Draw trains
        double[] startX = {0.903039073806078, 0.144717800289436, 0.078147612156295, 0.172214182344428};
        double[] startY = {0.144717800289436, 0.078147612156295, 0.172214182344428, 0.892908827785818};
        boolean[] isVertical = {true, false, true, false};

        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 8; i++) {
                int index = j < 2 ? j * 8 + (7 - i) : j * 8 + i;
                if (trains[index] == -1) {
                    continue;
                }
                g.setColor(getColor(trains[index]));

                int x = isVertical[j] ? (int) (width * startX[j]) : (int) (width * startX[j] + i * width * spaceBetween);
                int y = isVertical[j] ? (int) (height * startY[j] + i * height * spaceBetween) : (int) (height * startY[j]);
                int rectWidth = isVertical[j] ? (int) (width * trainLength) : (int) (width * trainWidth);
                int rectHeight = isVertical[j] ? (int) (height * trainWidth) : (int) (height * trainLength);

                g.fillRect(x, y, rectWidth, rectHeight);
            }
        }
        // Draw cards in hand
        int x = width + (xOffset - cardWidth) / 2;
        if (inventory[1] != -1) {
            g.drawImage(card.getCard(inventory[1]), x, height / 4, cardWidth, cardHeight, this);
            g.setColor(new Color(0, 255, 0, 128));
            g.fillRect(x, height / 4, cardWidth, cardHeight);
            g.drawImage(card.getCard(inventory[0]), x, height / 2, cardWidth, cardHeight, this);
        } else {
            if (inventory[0] != -1) {
                g.drawImage(card.getCard(inventory[0]), x, height / 4, cardWidth, cardHeight, this);
            }
        }
    }
}
