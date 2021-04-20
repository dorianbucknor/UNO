package UNO;

import org.w3c.dom.Text;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class CardHolder extends JPanel  {
    private ArrayList<Card> cards ;
    private int numberOfCards;

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
        System.out.println("add");
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
     * @see java.awt.Color for color
     * @param number number value of card
     * @return index of card in list or -1 if card is not found
     */
    public int cardIndex(Color color, int number){
        for (int i = cards.size(); i > 0 ; i--) {
            if(cards.get(i).getNumber() == number && cards.get(i).getColor() == color ){
                return i;
            }
        }
        return -1;
    }

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
     * @param queryCard card to remove
     */
    public void removeCard(Card queryCard){
        for (Card card:
             cards) {
            if(card.equals(queryCard)){
                cards.remove(queryCard);
                numberOfCards--;
                break;
            }
        }
    }

    private void createCardHolderGFX() {
        System.out.println(cards.size()+"six");

        setPreferredSize(new Dimension(490,120));

        setLayout(new BorderLayout());
        setVisible(true);

        JPanel cardPanel = new JPanel();
        cardPanel.setLayout(new GridLayout(1, numberOfCards, 2, 1));
        cardPanel.setPreferredSize(new Dimension(490,120));
        cardPanel.setForeground(Color.BLACK);
        cardPanel.setBorder(BorderFactory.createLineBorder(Color.MAGENTA, 2));
        cardPanel.setVisible(true);

        JLabel cardCountLbl = new JLabel();
        cardCountLbl.setText(Integer.toString(numberOfCards+1));
        cardCountLbl.setPreferredSize(new Dimension(490,20));
        cardCountLbl.setHorizontalTextPosition(JLabel.CENTER);
        cardCountLbl.setForeground(Color.BLACK);
        cardCountLbl.setVisible(true);


        for (Card card :
                cards) {
            cardPanel.add(card);
        }

        add(cardPanel, BorderLayout.CENTER);
        add(cardCountLbl, BorderLayout.NORTH);
    }


}
