package UNO_Card_Game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.EmptyStackException;

public class Player extends JPanel {
    private int score;
    private String name;
    private Color playerColor;
    final private JPanel playerInfoBar = new JPanel(new GridLayout(1, 5));
    final private JLabel playerNameTag = new JLabel();
    final private JLabel cardCountLbl = new JLabel();
    final private JLabel scoreLbl = new JLabel();
    final private JButton UNOBtn = new JButton("UNO");
    final private JButton drawBtn = new JButton("Draw Card");
    final private JPanel cardHolder = new JPanel();
    protected ArrayList<Card> cards ;
    protected int numberOfCards;
    private boolean isPlayerTurn;

    /**
     * Creates a new player
     * @param name name of player
     * @param playerColor player color
     */
    Player(String name, Color playerColor){
        this.name = name;
        //cardHolder = new CardHolder();
        this.score = 0;
        isPlayerTurn = false;
        cards = new ArrayList<>(7);
        this.playerColor = playerColor;
        createPlayerGFX();
    }

    /**
     * Create UI for player
     */
    private void createPlayerGFX() {
        /*
         * Player panel properties
         */
        setVisible(true);
        setFocusable(false);
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(playerColor, 2, true));

        /*
         * Player info
         */
        playerInfoBar.setVisible(true);
        playerInfoBar.setPreferredSize(new Dimension(700,30));

        /*
         * Player name
         */
        playerNameTag.setText("Player: " + name);
        playerNameTag.setVisible(true);
        playerNameTag.setBorder(BorderFactory.createLineBorder(Color.GREEN,2, true));
        playerInfoBar.add(playerNameTag);

        /*
         * Card count
         */
        cardCountLbl.setText("# of Cards: " + (numberOfCards));
        cardCountLbl.setHorizontalTextPosition(JLabel.CENTER);
        cardCountLbl.setForeground(Color.BLACK);
        cardCountLbl.setVisible(true);
        playerInfoBar.add(cardCountLbl);

        /*
         * Player score
         */
        scoreLbl.setText("Score: " + score);
        scoreLbl.setHorizontalTextPosition(JLabel.CENTER);
        scoreLbl.setForeground(Color.BLACK);
        scoreLbl.setVisible(true);
        playerInfoBar.add(scoreLbl);

        /*
         * Draw button
         */
        drawBtn.setForeground(Color.BLACK);
        drawBtn.setVisible(true);
        drawBtn.setEnabled(false);
        drawBtn.addActionListener(e -> {
            try {
                drawCard();
            } catch (EmptyStackException ex) {
                ex.printStackTrace();
                drawBtn.setEnabled(false);
            }
        });
        playerInfoBar.add(drawBtn);

        /*
         * UNO Button
         */
        UNOBtn.setForeground(Color.BLACK);
        UNOBtn.setEnabled(canUNO());
        UNOBtn.setVisible(true);
        UNOBtn.addActionListener( (e) -> UNO.winRound(this));
        playerInfoBar.add(UNOBtn);

        /*
         * displays player cards
         */
        cardHolder.setPreferredSize(new Dimension(490,180));
        cardHolder.setLayout(new GridLayout());
        cardHolder.setVisible(true);
        cardHolder.setName("cardHolder");

        add(cardHolder, BorderLayout.CENTER);
        add(playerInfoBar, BorderLayout.SOUTH);
    }

    /**
     * @return player's cardholder
     */
    public JPanel getCardHolder() {
        return cardHolder;
    }

    /**
     * Checks if player can call UNO to win a round
     * @return true if player can call UNO, false otherwise
     */
    public boolean canUNO(){
        return numberOfCards <= 1;
    }

    /**
     * @return player's score
     */
    public int getScore() {
        return score;
    }

    /**
     * @return player's name
     */
    public String getName() {
        return name;
    }

    /**
     * @return player's color
     */
    public Color getPlayerColor() {
        return playerColor;
    }

    /**
     *Sets player name
     * @param name set player name
     */
    public void setName(String name) {
        this.name = name;
        updatePlayerUI();
    }

    /**
     *Sets player score
     * @param score set player score
     */
    public void setScore(int score) {
        this.score = score;
        updatePlayerUI();
    }

    /**
     *Sets player color
     * @param playerColor set player color
     */
    public void setPlayerColor(Color playerColor) {
        this.playerColor = playerColor;
        updatePlayerUI();
    }

    /**
     * Increment player score by 1. Normally called when player wins a round.
     */
    public void incrementScore(){
        score += 1;
        updatePlayerUI();
    }

    /**
     * Draws a card from playing deck
     */
    public void drawCard(){
        Card newCard = UNO.getPlayDeck().pop();
        newCard.setPlayer(this);
        addCard(newCard);
        UNO.updateBoard();
        UNO.updatePlayers();
    }

    /**
     *Adds a card to player's list of cards
     * @param card card to add
     */
    public void addCard(Card card){
        cards.add(card);
        numberOfCards++;
        cardHolder.removeAll();
        updatePlayerUI();
    }

    /**
     * Removes an individual card from card holder by index
     * @param cardIndex index of card
     */
    public void removeCard(int cardIndex){
        cards.remove(cardIndex);
        numberOfCards--;
        updatePlayerUI();
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

        for (int i = cards.size() - 1; i > 0 ; i--) {
            if(cards.get(i).getNumber() == number && cards.get(i).getColor() == color ){
                indexOfCard = i;
            }
        }
        return indexOfCard;
    }

    /**
     * @return list of cards that player has
     * @see java.util.ArrayList ArrayList
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
    public void removeCard(Card card) {
        cardHolder.removeAll(); //container to hold current user cards
       // cardHolder.validate();

        for (Card c :
                cards) {
            if (c.equals(card)) {
                cards.remove(c);
                break;
            }
        }
        numberOfCards--;
       updatePlayerUI();
    }

    /**
     * @return number of card player currently has
     */
    public int getNumberOfCards() {
        return numberOfCards;
    }

    /**
     * Updates player UI
     */
    public void updatePlayerUI(){
        updateCardHolder();

        cardCountLbl.setText("# of Cards: " + numberOfCards);
        UNOBtn.setEnabled(canUNO());
        drawBtn.setEnabled(!UNO.isStackEmpty() && isPlayerTurn);
        scoreLbl.setText("Score: " + score);
        playerNameTag.setText("Player: " + name);

        revalidate();
        repaint();
        UNO.refresh();
    }

    /**
     *Updates cards
     */
    public void updateCardHolder(){
        for (Card card :
                cards) {
            cardHolder.add(card);
            cardHolder.validate();
            cardHolder.repaint();
        }
    }

    /**
     * Sets number of cards in cardholder
     * @param numberOfCards
     */
    public void setNumberOfCards(int numberOfCards) {
        this.numberOfCards = numberOfCards;
    }

    /**
     * Sets this player's turn
     * @param playerTurn
     */
    public void setPlayerTurn(boolean playerTurn) {
        isPlayerTurn = playerTurn;
    }

    /**
     * @return true if its thisplayer's turn, false otherwise
     */
    public boolean isPlayerTurn() {
        return isPlayerTurn;
    }
}


