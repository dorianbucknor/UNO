package UNO_Card_Game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

/**
 * @// TODO: 4/21/2021 Revise 4 player options
 */

/**
 * Singleton Pattern class
 */

public class UNO {
   private static Player player1;
    private static Player player2;
//    private static Player player3;
//    private static Player player4;
    private static ArrayList<Player> players = new ArrayList<>(4);
    private static int numberOfPlayers = 0;
    private static Card playedCard=new Card();
    private static int cardStackCount;
    private static Stack<Card> playDeck;
    private static Stack<Card> playedCards = new Stack<>();

    private static JPanel board = new JPanel(new BorderLayout());
    private static JPanel boardCentre = new JPanel(new BorderLayout());
    private static JFrame frame = new JFrame("UNO Card Game | OFFLINE");
    private static JPanel playedCardSection = new JPanel();

    private static UNO instance;

    /**
     * Private empty constructor - IMPORTANT
     */
    private UNO(){}

    /**
     * @return instance of UNO
     */
    public static synchronized UNO getInstance(){
        if(instance == null){
            instance = new UNO();
        }
        return instance;
    }

    /**
     * Run game
     */
    public static void run(){
        createBoardGFX();
    }

    /**
     * Create Board GUI
     */
    private static void createBoardGFX(){
        /**
         * Frame properties
         */
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(1200, 700);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setLayout(new BorderLayout());

        /**
         * playdeck properties
         *
         */
        JLabel playDeckRem = new JLabel("Playing Deck - Cards Remaining: 93");
        playDeckRem.setHorizontalAlignment(JLabel.CENTER);
        board.add(playDeckRem, BorderLayout.WEST);

        /**
         * board title
         */
        JLabel title = new JLabel("UNO_Card_Game");
        title.setFont(new Font( "Backslash", Font.BOLD, 48));
        title.setVisible(true);
        title.setHorizontalAlignment(JLabel.CENTER);

        boardCentre.add( title, BorderLayout.CENTER);
        boardCentre.setSize(new Dimension(200, 200));
        boardCentre.setVisible(true);

        playedCardSection.setPreferredSize(new Dimension(400, 300));
        playedCardSection.setVisible(true);
        boardCentre.add(playedCardSection, BorderLayout.EAST);

        board.add(boardCentre, BorderLayout.CENTER);

        frame.add(board);
    }

    /**
     * Adds a new player
     * @param player player to add
     */
    public static void createPlayer(Player player) {
        if (player1 == null) {
            player1 = player;
            board.add(player1, BorderLayout.SOUTH);
            numberOfPlayers++;
            players.add(player1);
            refresh();
        }
        else if (player2 == null) {
            player2 = player;
            board.add(player2, BorderLayout.NORTH);
            numberOfPlayers++;
            players.add(player2);
            refresh();
        }
        /*else if (player3 == null) {
            player3 = player;
            board.add(player3, BorderLayout.NORTH);
            numberOfPlayers++;
            players.add(player3);
            refresh();
        }
        else if (player4 == null) {
            player4 = player;
            board.add(player4, BorderLayout.EAST);
            numberOfPlayers++;
            System.out.println(numberOfPlayers);
            players.add(player4);
            refresh();
        }*/
        //add(new JOptionPane("Game is full!", JOptionPane.INFORMATION_MESSAGE, JOptionPane.OK_OPTION));
        refresh();
    }

    /**
     * Starts game
     */
    public static void start(){
        shuffleShareCards(7);
        playedCards.push(playDeck.pop());
        updateBoard();
        updatePlayedCard();
    }

    /**
     * Update board GUI
     * @TODO: 4/21/2021 Implement
     */
    private static void updateBoard(){
    }

    /**
     * Shuffles play deck
     */
    public static void shufflePlayDeck(){
        Collections.shuffle(playDeck);
    }

    /**
     * Update last played card
     */
    private static void updatePlayedCard(){
        playedCard = playedCards.peek();
        playedCard.setCanPlay(false);
        playedCardSection.add(playedCard);
    }

    /**
     * Refresh game UI
     */
    public static void refresh(){
        frame.revalidate();
        frame.repaint();
    }

    /**
     * @return true if playing deck is empty. false otherwise
     */
    public static boolean isStackEmpty(){
        return playDeck.isEmpty();
    }

    /**
     * Shuffle cards and share equally to players
     */
    public static void shuffleShareCards(int cardsPerPlayer) {
        playDeck = new CardDeck().createPlayDeck();
        for (int i = 0; i < numberOfPlayers; i++) {
            for (int j = 0; j < 7; j++) {
                Card newCard = playDeck.pop();
                newCard.setPlayer(players.get(i));
                players.get(i).addCard(newCard);
                refresh();
            }
        }
    }

    /**
     * @return playing deck
     */
    public static Stack<Card> getPlayDeck() {
        return playDeck;
    }

    /**
     * @return number of cards left to draw from
     */
    public static int getCardStackCount() {
        return cardStackCount;
    }

    /**
     *
     * @return number of players in round/match
     */
    public static int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    /**
     * @return player 1
     * @see Player Player
     */
    public static Player getPlayer1() {
        return player1;
    }
    /**
     * @return player 2
     * @see Player Player
     */
    public static Player getPlayer2() {
        return player2;
    }
/*
    public static Player getPlayer3() {
        return player3;
    }

    public static Player getPlayer4() {
        return player4;
    }*/

    /**
     * @return list of  cards that have been played
     * @see java.util.Stack Stack
     */
    public static Stack<Card> getPlayedCards() {
        return playedCards;
    }

    /**
     * Set player 1
     * @param player player to set
     * @see Player Player
     */
    public static void setPlayer1(Player player) {
        player1 = player;
    }

    /**
     * Set player 2
     * @param player player to set
     * @see Player Player
     */
    public static void setPlayer2(Player player) {
        player2 = player;
    }

   /* public static void setPlayer3(Player player) {
        player3 = player;
    }
    public static void setPlayer4(Player player) {
        player4 = player;
    }*/

    /**
     * @return list of players in round.match
     * @see java.util.ArrayList ArrayList
     */
    public static ArrayList<Player> getPlayers() {
        return players;
    }

    public static void winRound(Player player){
        player.incrementScore();
        frame.add(new JOptionPane(player.getName() + " won the round!", JOptionPane.INFORMATION_MESSAGE, JOptionPane.OK_OPTION));
    }
}
