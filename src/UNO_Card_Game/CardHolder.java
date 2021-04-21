/**
 *
 * @// TODO: 4/21/2021 CLASS CURRENTLY NOT IN USE AND IS UNDER REVISION!!!
 *
 */


package UNO_Card_Game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CardHolder extends JPanel  {
    protected ArrayList<Card> cards ;
    protected int numberOfCards;

    /**
     * Default constructor
     */
    CardHolder(){
        cards = new ArrayList<>(7);
        numberOfCards = -1;
    }

    /**
     * @param cards list (List<Card>) of cards t be added.
     *
     */
    CardHolder (ArrayList<Card> cards) {
        this.cards = cards;
        this.numberOfCards = cards.size();
        createCardHolderGFX();
    }

    /**
     * Adds an individual card to card holder
     * @param card card to be added to card holder
     */
    public void addCard(Card card){
        cards.add(card);
        numberOfCards++;

        createCardHolderGFX();
    }

    /**
     * Removes an individual card from card holder by index
     * @param cardIndex index of card
     */
    public void removeCard(int cardIndex){
        cards.remove(cardIndex);
        numberOfCards--;
    }

    /**
     * Finds a card by color and number
     * @param color color of card
     * @see java.awt.Color Color
     * @param number number value of card
     * @return index of card in list or -1 if card is not found
     */
    public int cardIndex(Color color, int number){
        int indexOfCard = -1;

        for (int i = cards.size(); i > 0 ; i--) {
            if(cards.get(i).getNumber() == number && cards.get(i).getColor() == color ){
                indexOfCard = i;
            }
        }
        return indexOfCard;
    }

    /**
     * @return player's cards
     */
    public ArrayList<Card> getCards() {
        return cards;
    }

    /**
     * Finds index of card by reference
     * @param card card to find
     * @return index of card
     */
    public int cardIndex(Card card){
        return cards.indexOf(card);
    }

    /**
     * Removes a card by reference
     * @param card card to remove
     */
    public void removeCard(Card card){
        for (Card c:
             cards) {
            if(c.equals(card)){
                cards.remove(card);
                numberOfCards--;
                break;
            }
        }
    }

    /**
     * @return number of cards
     */
    public int getNumberOfCards() {
        return numberOfCards;
    }

    /**
     * Creates cardHolder UI
     */
    private void createCardHolderGFX() {
        System.out.println(cards.size());

        setPreferredSize(new Dimension(490,120));
        setLayout(new GridLayout(1, numberOfCards ));
        setVisible(true);
        setName("cardHolder");

        for (Card card :
                cards) {
            add(card);
        }
    }
}
