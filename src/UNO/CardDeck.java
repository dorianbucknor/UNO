package UNO;

import java.awt.*;
import java.util.*;
import java.util.List;

public class CardDeck {
    private static HashMap<String, Card> fullDeck = new HashMap<>(108);

    CardDeck(){
        for (int i = 1; i < 10; i++) {
            fullDeck.put("Red"+i+"-1", new Card(Color.red, i));
            fullDeck.put("Red"+i+"-2", new Card(Color.red, i));
            fullDeck.put("Blue"+i+"-1", new Card(Color.blue, i));
            fullDeck.put("Blue"+i+"-2", new Card(Color.blue, i));
            fullDeck.put("Green"+i+"-1", new Card(Color.green, i));
            fullDeck.put("Green"+i+"-2", new Card(Color.green, i));
            fullDeck.put("Yellow"+i+"-1", new Card(Color.yellow, i));
            fullDeck.put("Yellow"+i+"-2", new Card(Color.yellow, i));
        }

        fullDeck.put("Red0",new Card(Color.red, 0));
        fullDeck.put("Blue0",new Card(Color.blue, 0));
        fullDeck.put("Green0",new Card(Color.yellow, 0));
        fullDeck.put("Yellow0", new Card(Color.green, 0));

        for (int i = 1; i < 3; i++) {
            fullDeck.put("Red+2-"+i, new Card(Color.red, "+2"));
            fullDeck.put("Blue+2"+i,new Card(Color.blue, "+2"));
            fullDeck.put("Yellow+2-"+i,new Card(Color.yellow, "+2"));
            fullDeck.put("Green+2-"+i,new Card(Color.green, "+2"));

            fullDeck.put("RedReverse-"+i, new Card(Color.red, "reverse"));
            fullDeck.put("BlueReverse-"+i,new Card(Color.blue, "reverse"));
            fullDeck.put("YellowReverse"+i,new Card(Color.yellow, "reverse"));
            fullDeck.put("GreenReverse"+i,new Card(Color.green, "reverse"));

            fullDeck.put("RedSkip-"+i, new Card(Color.red, "skip"));
            fullDeck.put("BlueSkip-"+i,new Card(Color.blue, "skip"));
            fullDeck.put("YellowSkip"+i,new Card(Color.yellow, "skip"));
            fullDeck.put("GreenSkip"+i,new Card(Color.green, "skip"));
        }

        for (int i = 0; i < 4; i++) {
            fullDeck.put("Wild+4"+i, new Card(Color.black, "+4"));
            fullDeck.put("WildChangeColor"+i ,new Card(Color.black, "change-color"));
        }
    }

    /**
     * Creates a deck of cards for round
     * @return deck of cards as a Stack
     * @see java.util.Stack Stack
     */
    public Stack<Card> createPlayDeck(){
        ArrayList<Card> playDeck = new ArrayList<Card>(fullDeck.values());
        return shuffleCards( playDeck);
    }

    /**
     * Shuffle Cards
     * @param cards list of cards to shuffle
     * @return Stack of shuffled cards
     * @see java.util.Stack Stack
     */
    public Stack shuffleCards( List<Card> cards) {
        Stack<Card> shuffledCards = new Stack<>();
        List<Card> allCards = cards;
        Collections.shuffle(allCards);
        shuffledCards.addAll(allCards);
        return shuffledCards;
    }
    public HashMap getFullDeck() {
        return fullDeck;
    }
}

