package UNO_Card_Game;

import javax.swing.*;
import java.awt.*;
import java.util.EmptyStackException;
import java.util.Objects;

public class Card extends JPanel {
    private Color color;
    private int number;
    // private String action;
    private boolean isActionCard;
    private boolean isWildCard;
    private boolean canPlay;
    private Player player;
    private CardAction cardAction;
    private JButton playCardBtn = new JButton("Play Card");
    private JLabel unoLbl = new JLabel();
    private JLabel cardLbl2 = new JLabel();
    private JLabel cardLbl1 = new JLabel();


    /**
     * Default Constructor
     */
    Card() {
        color = null;
        number = -1;
        canPlay = false;
        isWildCard = false;
        isActionCard = false;
        createCardGFX();
    }

    /**
     * Creates a normal card
     *
     * @param color  card color
     * @param number card number
     */
    Card(Color color, int number) {
        this.color = color;
        this.number = number;
        canPlay = false;
        isWildCard = false;
        isActionCard = false;
        createCardGFX();
    }

    /**
     * Creates an action card or a wild card
     *
     * @param color      card color
     * @param cardAction card action
     * @see CardAction CardAction
     */

    Card(Color color, CardAction cardAction) {
        this.color = color;
        //this.action = cardAction.action;
        this.cardAction = cardAction;
        canPlay = false;

        if (this.cardAction.equals(CardAction.SKIP) || this.cardAction.equals(CardAction.REVERSE) || this.cardAction.equals(CardAction.PLUS_2)) {
            isActionCard = true;
        } else {
            isWildCard = true;
        }

        createCardGFX();
    }

    /**
     * Creates card graphics
     *
     * @see java.awt.Color for color
     */
    private void createCardGFX() {

        String labelText;

        if (isActionCard || isWildCard) {
            labelText = cardAction.action;
        } else {
            labelText = Integer.toString(number);
        }

        /*
         *Card properties
         */
        setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
        setPreferredSize(new Dimension(180, 200));
        setMaximumSize(new Dimension(180, 200));
        setBackground(Color.white);
        setLayout(new BorderLayout());
        setVisible(true);

        /*
         * Card number or action label at top
         */
        cardLbl1.setText(labelText);
        cardLbl1.setBackground(color);
        cardLbl1.setForeground(color == Color.yellow || color == Color.green ? Color.BLACK : Color.WHITE);
        cardLbl1.setOpaque(true);
        cardLbl1.setVisible(true);
        add(cardLbl1, BorderLayout.NORTH);

        /*
         * Card number or action label at bottom
         */
        cardLbl2.setText(labelText);
        cardLbl2.setBackground(color);
        cardLbl2.setForeground(color == Color.yellow || color == Color.green ? Color.BLACK : Color.WHITE);
        cardLbl2.setHorizontalAlignment(JLabel.RIGHT);
        cardLbl2.setOpaque(true);
        cardLbl2.setVisible(true);
        add(cardLbl2, BorderLayout.SOUTH);


        /*
         * UNO_Card_Game Label
         */
        unoLbl.setText("UNO");
        unoLbl.setHorizontalAlignment(JLabel.CENTER);
        unoLbl.setLayout(new BorderLayout());
        unoLbl.setVisible(true);

        /*
         * Card play button
         */
        playCardBtn.setForeground(Color.BLACK);
        playCardBtn.setVisible(true);
        playCardBtn.setEnabled(canPlay());
        add(unoLbl, BorderLayout.CENTER);

        /**
         * Adds action listener to button to listen for button click
         * @see java.awt.event.ActionListener ActionListener
         */
        playCardBtn.addActionListener(e -> {
            try {
                playCard();
            } catch (EmptyStackException ex) {
                ex.printStackTrace();
                playCardBtn.setEnabled(false);
            }
        });
        unoLbl.add(playCardBtn, BorderLayout.SOUTH);
    }

    /**
     * Plays the card
     */
    private void playCard() {
        if (canPlay()) UNO.makePlay(player, this);
    }

    /**
     * @return card number
     */
    public int getNumber() {
        return number;
    }

    /**
     * @return card color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets card player
     * @param player player attached to card
     * @see Player Player
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * @return player player attached to card
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * @return action attached to card
     */
    public CardAction getCardAction() {
        return cardAction;
    }

    /**
     * Sets card action
     * @param cardAction action to attach to card
     * @see CardAction CardAction
     */
    public void setCardAction(CardAction cardAction) {
        this.cardAction = cardAction;
    }

    /**
     * @param color card color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * @param number card number
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Checks if card can be played
     * @return true if card can be played, false otherwise
     */
    public boolean canPlay() {
        if (player == null) return false;

        if (!player.isPlayerTurn()) return false;

        return canPlayNumCard() || canPlayActionCard() || isWildCard || playedCardIsWildCard() ;
    }

    /**
     * @return true if last played card is a wild card, false otherwise
     */
    private boolean playedCardIsWildCard() {
        if (!UNO.getPlayedCards().isEmpty()) {
            return UNO.getPlayedCards().peek().isWildCard;
        }
        else {
            return false;
        }
    }

    /**
     * @return true if action card can be played, false otherwise
     */
    private boolean canPlayActionCard(){
       return isActionCard && (cardMatchesColor() || cardMatchesAction());
    }

    /**
     * @return true if this card matches last played card's action
     */
    private boolean cardMatchesAction() {
        if (!UNO.getInstance().getPlayedCards().isEmpty()) {
            return UNO.getInstance().getPlayedCards().peek().getCardAction() == cardAction;
        }
        else {
            return false;
        }
    }

    /**
     * @return true if this number card can be played, false otherwise
     */
    private boolean canPlayNumCard(){
        return (cardMatchesColor() || cardMatchesNumber()) && !isActionCard;
    }

    /**
     * @return true if thic card matches last played card's color, false otherwise
     */
    private boolean cardMatchesColor() {
        if (!UNO.getInstance().getPlayedCards().isEmpty()) {

            return UNO.getInstance().getPlayedCards().peek().getColor() == color;
        }
        else {
            return false;
        }
    }

    /**
     * @return true if this card's number matches last played card's number
     */
    private boolean cardMatchesNumber() {
        if (!UNO.getInstance().getPlayedCards().isEmpty()) {
            return UNO.getInstance().getPlayedCards().peek().getNumber() == number;
        }
        else {
            return false;
        }
    }

    /**
     * @return if card is action card
     */
    public boolean isActionCard() {
        return isActionCard;
    }

    /**
     * @return if card is wild card
     */
    public boolean isWildCard() {
        return isWildCard;
    }

    /**
     * Updates card
     */
    public void updateCard() {
        playCardBtn.setEnabled(canPlay());
    }

    /**
     * helper functions
     * @param o Card
     * @return if this card is equal to o
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;
        Card card = (Card) o;
        return getNumber() == card.getNumber() && isActionCard() == card.isActionCard() && isWildCard() == card.isWildCard() && canPlay == card.canPlay && Objects.equals(getColor(), card.getColor()) && Objects.equals(getPlayer(), card.getPlayer()) && getCardAction() == card.getCardAction() && Objects.equals(playCardBtn, card.playCardBtn) && Objects.equals(unoLbl, card.unoLbl) && Objects.equals(cardLbl2, card.cardLbl2) && Objects.equals(cardLbl1, card.cardLbl1);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getColor(), getNumber(), isActionCard(), isWildCard(), canPlay, getPlayer(), getCardAction(), playCardBtn, unoLbl, cardLbl2, cardLbl1);
    }

    /**
     * Enum for storing card actions
     */
    public enum CardAction {
        PLUS_2("+2"),
        PLUS_4("+4"),
        REVERSE("reverse"),
        SKIP("skip"),
        CHANGE_COLOR("change-color");

        private String action;

        CardAction(String action) {
            this.action = action;
        }
    }
}
