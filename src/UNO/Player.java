package UNO;

import javax.swing.*;
import java.awt.*;

public class Player extends JPanel {
    private int score;
    private String name;
    private CardHolder cardHolder;
    private Color playerColor;

    /**
     * Default constructor
     */
    Player(){
        name = null;
        cardHolder = null;
        score = -1;
        playerColor = null;
        createPlayerGFX();
    }

    /**
     * @param name name of player
     * @param playerColor player color
     */
    Player(String name, Color playerColor){
        this.name = name;
        cardHolder = new CardHolder();
        this.score = 0;
        this.playerColor = playerColor;
        createPlayerGFX();
    }

    /**
     * Create UI for player
     */
    private void createPlayerGFX() {
        setPreferredSize(new Dimension(490,140));
        setMaximumSize(new Dimension(490,140));
        setVisible(true);
        setFocusable(false);
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(playerColor, 2, true));

        JLabel playerNameTag = new JLabel();
        playerNameTag.setText(name);
        playerNameTag.setVisible(true);
        playerNameTag.setPreferredSize(new Dimension(700,20));
        playerNameTag.setBorder(BorderFactory.createLineBorder(Color.GREEN,2, true));

        add(playerNameTag, BorderLayout.SOUTH);
        add(cardHolder, BorderLayout.CENTER);
    }

    /**
     *
     * @return card holder containing all cards player has
     */
    public CardHolder getCardHolder() {
        return cardHolder;
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
    public void setCardHolder(CardHolder cardHolder) {
        this.cardHolder = cardHolder;
    }

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
}
