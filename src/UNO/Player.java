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
        score = 0;
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
        setPreferredSize(new Dimension(200,260));
        setMaximumSize(new Dimension(200,260));
        setVisible(true);
        setFocusable(false);
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(playerColor, 2, true));

        JPanel playerInfoBar = new JPanel(new GridLayout(1, 5));
        playerInfoBar.setVisible(true);
        playerInfoBar.setPreferredSize(new Dimension(700,30));


        JLabel playerNameTag = new JLabel();
        playerNameTag.setText("Player: "+name);
        playerNameTag.setVisible(true);
        playerNameTag.setBorder(BorderFactory.createLineBorder(Color.GREEN,2, true));

        JLabel cardCountLbl = new JLabel();
        cardCountLbl.setText("# of Cards: " + (cardHolder.getNumberOfCards() + 1));
        cardCountLbl.setHorizontalTextPosition(JLabel.CENTER);
        cardCountLbl.setForeground(Color.BLACK);
        cardCountLbl.setVisible(true);

        JLabel scoreLbl = new JLabel();
        scoreLbl.setText("Score: "+score);
        scoreLbl.setHorizontalTextPosition(JLabel.CENTER);
        scoreLbl.setForeground(Color.BLACK);
        scoreLbl.setVisible(true);

        JButton drawBtn = new JButton("Draw Card");
        drawBtn.setForeground(Color.BLACK);
        drawBtn.setVisible(true);

        JButton UNOBtn = new JButton("UNO");
        UNOBtn.setForeground(Color.BLACK);
        UNOBtn.setEnabled(canUNO());
        UNOBtn.setVisible(true);

        playerInfoBar.add(playerNameTag);
        playerInfoBar.add(scoreLbl);
        playerInfoBar.add(cardCountLbl);
        playerInfoBar.add(drawBtn);
        playerInfoBar.add(UNOBtn);


        add(playerInfoBar, BorderLayout.SOUTH);
        add(cardHolder, BorderLayout.CENTER);
    }

    /**
     *
     * @return card holder containing all cards player has
     */
    public CardHolder getCardHolder() {
        return cardHolder;
    }

    public boolean canUNO(){
        return cardHolder.getNumberOfCards() == 1;
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
