package UNO_Card_Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Objects;

public class Card extends JPanel {
    private Color color;
    private int number;
    private String action;
    private boolean isActionCard = false;
    private boolean canPlay = false;
    private Player player;

    /**
     * Default Constructor
     */
    Card() {
        color = null;
        number = -1;
        createCardGFX();
    }

    /**
     * Creates a normal card
     * @param color card color
     * @param number card number
     */
    Card(Color color, int number) {
        this.color = color;
        this.number = number;
        createCardGFX();
    }

    /**
     * Creates special card "wild card"
     * @param color card color
     * @param action card action - "+4, reverse, change color"
     */
    Card(Color color, String action) {
        this.color = color;
        this.action = action;
        isActionCard = true;
        createCardGFX();
    }

    /**
     * Creates card graphics
     * @see java.awt.Color for color
     */
    private void createCardGFX() {

        String labelText;

        if (isActionCard){
            labelText = action;
        }else{
            labelText = Integer.toString(number);
        }

        /**
         *Card properties
         */
        setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
        setPreferredSize(new Dimension(240,300));
        setMaximumSize(new Dimension(200,180));
        setBackground(Color.white);
        setLayout(new BorderLayout());
        setVisible(true);

        /**
         * Card number or action label at top
         */
        JLabel cardLbl1 = new JLabel();
        cardLbl1.setText(labelText);
        cardLbl1.setBackground(color);
        cardLbl1.setForeground(color == Color.yellow || color == Color.green ? Color.BLACK : Color.WHITE);
        cardLbl1.setOpaque(true);
        cardLbl1.setVisible(true);
        add(cardLbl1,  BorderLayout.NORTH);

        /**
         * Card number or action label at bottom
         */
        JLabel cardLbl2 = new JLabel();
        cardLbl2.setText(labelText);
        cardLbl2.setBackground(color);
        cardLbl2.setForeground(color == Color.yellow || color == Color.green ? Color.BLACK : Color.WHITE);
        cardLbl2.setHorizontalAlignment(JLabel.RIGHT);
        cardLbl2.setOpaque(true);
        cardLbl2.setVisible(true);
        add(cardLbl2,BorderLayout.SOUTH);


        /**
         * UNO_Card_Game Label
         */
        JLabel unoLbl = new JLabel();
        unoLbl.setText("UNO");
        unoLbl.setHorizontalAlignment(JLabel.CENTER);
        unoLbl.setLayout(new BorderLayout());
        unoLbl.setVisible(true);

        /**
         * Card play button
         */
        JButton playCardBtn = new JButton("Play Card");
        playCardBtn.setForeground(Color.BLACK);
        playCardBtn.setVisible(true);
        add(unoLbl,BorderLayout.CENTER);

        /**
         * Adds action listener to button to listen for button click
         * @see java.awt.event.ActionListener ActionListener
         */
        playCardBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    playCard(e);
                } catch (EmptyStackException ex) {
                    ex.printStackTrace();
                    playCardBtn.setEnabled(false);
                }
            }
        });
        unoLbl.add(playCardBtn, BorderLayout.SOUTH);
    }

    /**
     * Plays the card and removes card from player's hand
     * @param e Action Event from button
     * @see java.awt.event.ActionEvent ActionEvent e
     */
    private void playCard(ActionEvent e) {
        if (player == null) return;
        if (UNO.getPlayedCards().peek().getColor() == this.color || UNO.getPlayedCards().peek().getNumber() == this.number) {
            player.removeCard(this);
        }
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

    /**
     * Sets the action of the card
     * @param action the action of the card
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * @return card's action if car is an action card
     */
    public String getAction() {
        return isActionCard ? action : "Not an Action card";
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
     *
     * @param canPlay
     */
    public void setCanPlay(boolean canPlay) {
        this.canPlay = canPlay;
    }

    /**
     * Important helper functions
     * @param o card to check if equal
     * @return true if cards are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Card)) return false;
        Card card = (Card) o;
        return getNumber() == card.getNumber() && getColor().equals(card.getColor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getColor(), getNumber());
    }
}
