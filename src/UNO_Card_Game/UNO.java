package UNO_Card_Game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
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
    private static JFrame frame = new JFrame("UNO_Card_Game | OFFLINE");

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
        createBoard();
    }

    /**
     * Create Board GUI
     */
    private static void createBoard(){
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        JLabel title = new JLabel("UNO_Card_Game");
        boardCentre.add( title, BorderLayout.CENTER);
        boardCentre.setSize(new Dimension(200, 200));
        boardCentre.setVisible(true);
        title.setFont(new Font( "Backslash", Font.BOLD, 48));
        title.setVisible(true);
        title.setHorizontalAlignment(JLabel.CENTER);

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
        shuffleShareCards();
        playedCards.push(playDeck.pop());
        updateBoard();
        updatePlayedCard();
    }

    /**
     * Update board GUI
     * @// TODO: 4/21/2021 Implement
     */
    private static void updateBoard(){
    }

    /**
     * Update last played card
     */
    private static void updatePlayedCard(){
        playedCard = playedCards.peek();
        playedCard.setBounds(-100, 0, 100, 180);
        boardCentre.add(playedCard, BorderLayout.EAST);
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
    public static void shuffleShareCards() {
        playDeck = new CardDeck().createPlayDeck();

        for (int i = 0; i < numberOfPlayers; i++) {
            for (int j = 0; j < 7; j++) {
                players.get(i).addCard(playDeck.pop());
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
}
