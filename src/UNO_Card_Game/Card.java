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
     * Creates special card "wild card"
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

        /**
         *Card properties
         */
        setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
        setPreferredSize(new Dimension(180, 200));
        setMaximumSize(new Dimension(180, 200));
        setBackground(Color.white);
        setLayout(new BorderLayout());
        setVisible(true);

        /**
         * Card number or action label at top
         */
        cardLbl1.setText(labelText);
        cardLbl1.setBackground(color);
        cardLbl1.setForeground(color == Color.yellow || color == Color.green ? Color.BLACK : Color.WHITE);
        cardLbl1.setOpaque(true);
        cardLbl1.setVisible(true);
        add(cardLbl1, BorderLayout.NORTH);

        /**
         * Card number or action label at bottom
         */
        cardLbl2.setText(labelText);
        cardLbl2.setBackground(color);
        cardLbl2.setForeground(color == Color.yellow || color == Color.green ? Color.BLACK : Color.WHITE);
        cardLbl2.setHorizontalAlignment(JLabel.RIGHT);
        cardLbl2.setOpaque(true);
        cardLbl2.setVisible(true);
        add(cardLbl2, BorderLayout.SOUTH);


        /**
         * UNO_Card_Game Label
         */
        unoLbl.setText("UNO");
        unoLbl.setHorizontalAlignment(JLabel.CENTER);
        unoLbl.setLayout(new BorderLayout());
        unoLbl.setVisible(true);

        /**
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
     * Plays the card and removes card from player's hand
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

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public CardAction getCardAction() {
        return cardAction;
    }

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

    public boolean canPlay() {
        if (player == null) return false;

        if (!player.isPlayerTurn()) return false;

        return canPlayNumCard() || canPlayActionCard() || isWildCard || playedCardIsWildCard() ;
    }

    private boolean playedCardIsWildCard() {
        if (!UNO.getInstance().getPlayedCards().isEmpty()) {
            return UNO.getInstance().getPlayedCards().peek().isWildCard;
        }
        else {
            return false;
        }
    }

    private boolean canPlayActionCard(){
       return isActionCard && (cardMatchesColor() || cardMatchesAction());
    }

    private boolean cardMatchesAction() {
        if (!UNO.getInstance().getPlayedCards().isEmpty()) {
            return UNO.getInstance().getPlayedCards().peek().getCardAction() == cardAction;
        }
        else {
            return false;
        }
    }

    private boolean canPlayNumCard(){
        return (cardMatchesColor() || cardMatchesNumber()) && !isActionCard;
    }

    private boolean cardMatchesColor() {
        if (!UNO.getInstance().getPlayedCards().isEmpty()) {

            return UNO.getInstance().getPlayedCards().peek().getColor() == color;
        }
        else {
            return false;
        }
    }

    private boolean cardMatchesNumber() {
        if (!UNO.getInstance().getPlayedCards().isEmpty()) {
            return UNO.getInstance().getPlayedCards().peek().getNumber() == number;
        }
        else {
            return false;
        }
    }

    public boolean isActionCard() {
        return isActionCard;
    }

    public boolean isWildCard() {
        return isWildCard;
    }

    public void updateCard() {
        playCardBtn.setEnabled(canPlay());
    }

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
