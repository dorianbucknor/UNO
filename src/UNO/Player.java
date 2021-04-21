package UNO;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Player extends JPanel {
    private int score;
    private String name;
    //private CardHolder cardHolder;
    private Color playerColor;
    private JPanel playerInfoBar = new JPanel(new GridLayout(1, 5));
    private JLabel playerNameTag = new JLabel();
    private JLabel cardCountLbl = new JLabel();
    private JLabel scoreLbl = new JLabel();
    private JButton UNOBtn = new JButton("UNO");
    private JButton drawBtn = new JButton("Draw Card");
    private JPanel cardHolder = new JPanel();
    protected ArrayList<Card> cards ;
    protected int numberOfCards;

    /**
     * @param name name of player
     * @param playerColor player color
     */
    Player(String name, Color playerColor){
        this.name = name;
        //cardHolder = new CardHolder();
        this.score = 0;
        cards = new ArrayList<>(7);
        this.playerColor = playerColor;
        createPlayerGFX();
    }

    /**
     * Create UI for player
     */
    private void createPlayerGFX() {
        setPreferredSize(new Dimension(200,260));
        setMaximumSize(new Dimension(200,260));
        setVisible(true);
        setFocusable(false);
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(playerColor, 2, true));

        playerInfoBar.setVisible(true);
        playerInfoBar.setPreferredSize(new Dimension(700,30));

        playerNameTag.setText("Player: " + name);
        playerNameTag.setVisible(true);
        playerNameTag.setBorder(BorderFactory.createLineBorder(Color.GREEN,2, true));

        cardCountLbl.setText("# of Cards: " + (numberOfCards));
        cardCountLbl.setHorizontalTextPosition(JLabel.CENTER);
        cardCountLbl.setForeground(Color.BLACK);
        cardCountLbl.setVisible(true);

        scoreLbl.setText("Score: " + score);
        scoreLbl.setHorizontalTextPosition(JLabel.CENTER);
        scoreLbl.setForeground(Color.BLACK);
        scoreLbl.setVisible(true);

        drawBtn.setForeground(Color.BLACK);
        drawBtn.setVisible(true);
        drawBtn.setEnabled(!UnoGame.isStackEmpty());

        UNOBtn.setForeground(Color.BLACK);
        UNOBtn.setEnabled(canUNO());
        UNOBtn.setVisible(true);

        cardHolder.setPreferredSize(new Dimension(490,120));
        cardHolder.setLayout(new GridLayout(1, 7 ));
        cardHolder.setVisible(true);
        cardHolder.setName("cardHolder");

        add(cardHolder, BorderLayout.CENTER);

        playerInfoBar.add(playerNameTag);
        playerInfoBar.add(scoreLbl);
        playerInfoBar.add(cardCountLbl);
        playerInfoBar.add(drawBtn);
        playerInfoBar.add(UNOBtn);

        add(playerInfoBar, BorderLayout.SOUTH);
        //add(, BorderLayout.CENTER);
    }

    /**
     *
     * @return card holder containing all cards player has
     */
  //  public CardHolder getCardHolder() {
  //      return cardHolder;
   // }

    public boolean canUNO(){
        return getNumberOfCards() == 1;
    }

    /**
     *
     * @return player's score
     */
    public int getScore() {
        return score;
    }

    /**
     *
     * @return player's name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return player's color
     */
    public Color getPlayerColor() {
        return playerColor;
    }

    /**
     *
     * @param cardHolder set the player's hand
     */
    //public void setCardHolder(CardHolder cardHolder) {
   //     this.cardHolder = cardHolder;
   // }

    /**
     *
     * @param name set player name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @param score set player score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     *
     * @param playerColor set player color
     */
    public void setPlayerColor(Color playerColor) {
        this.playerColor = playerColor;
    }

    /**
     * Increment player score by 1. Normally called when player wins a round.
     */
    public void incrementScore(){
        score += 1;
    }

    public void addCard(Card card){
        cards.add(card);
        numberOfCards++;
        updatePlayerUI();
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

    public int getNumberOfCards() {
        return numberOfCards;
    }
    public void updatePlayerUI(){
        cardCountLbl.setText("# of Cards: " + numberOfCards);
        UNOBtn.setEnabled(canUNO());
        drawBtn.setEnabled(!UnoGame.isStackEmpty());
        scoreLbl.setText("Score: " + score);
        playerNameTag.setText("Player: " + name);
        for (Card card :
                cards) {
            cardHolder.add(card);
        }
    }
}


