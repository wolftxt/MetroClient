
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Card {

    private BufferedImage[] cards;

    public Card() {
        loadImages();
    }
    
    public BufferedImage getCard(int index){
        return cards[index];
    }

    private void loadImages() {
        try {
            BufferedImage image = ImageIO.read(Card.class.getResourceAsStream("/resources/cards.png"));
            int rowCards = 6; // Cards in a row in the source image.
            int columnCards = 10; // Cards in a column in the source image.
            cards = new BufferedImage[rowCards * columnCards];
            
            int xScaling = image.getWidth() / rowCards;
            int yScaling = image.getHeight() / columnCards;

            int index = 0;
            for (int x = 0; x < rowCards; x++) {
                for (int y = 0; y < columnCards; y++) {
                    cards[index] = image.getSubimage(x * xScaling, y * yScaling, xScaling, yScaling);
                    index++;
                }
            }
        } catch (IOException ex) {
            throw new RuntimeException("Error while reading the card images.");
        }
    }
}
