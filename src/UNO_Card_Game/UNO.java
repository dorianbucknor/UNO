package UNO_Card_Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.Collections;
import java.util.Stack;


/*
 * Singleton Pattern class
 */

public class UNO {
    private static final JLabel numCardsPlayDeck = new JLabel();
    private static Card playedCard = new Card();
    private static int cardStackCount;
    private static Stack<Card> playDeck = null;
    private static Stack<Card> playedCards = new Stack<>();
     private static JPanel board = new JPanel(new BorderLayout());
     private static JPanel boardCentre = new JPanel(new BorderLayout());
     private static JFrame frame = new JFrame("UNO Card Game | OFFLINE");
    private static JPanel playedCardSection = new JPanel(new BorderLayout());
    private static Players players = new Players();

    private static UNO instance;

    /**
     * Private empty constructor - IMPORTANT
     */
    private UNO(){
        //Yes. Just an empty constructor
    }

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
         * play deck properties
         *
         */
        numCardsPlayDeck.setHorizontalAlignment(JLabel.CENTER);
        board.add(numCardsPlayDeck, BorderLayout.WEST);

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

        playedCardSection.setPreferredSize(new Dimension(180, 200));
        playedCardSection.setVisible(true);
        boardCentre.add(playedCardSection, BorderLayout.EAST);

        board.add(boardCentre, BorderLayout.CENTER);

        frame.add(board);
    }

    /**
     * Starts game
     */
    public static void start(){
        shuffleShareCards(7);
        updateBoard();
        updatePlayedCard();
        updatePlayers();
    }

    /**
     * Update board GUI
     */
    public static void updateBoard(){
        cardStackCount = playDeck.size();
        numCardsPlayDeck.setText("Playing Deck - Cards Remaining: " + cardStackCount) ;
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
    public static void updatePlayedCard(){
        playedCardSection.removeAll();
        playedCard = playedCards.peek();
        playedCardSection.add(playedCard, BorderLayout.CENTER);
        playedCardSection.validate();
        playedCardSection.repaint();
    }

    /**
     * Refresh game UI
     */
    public static void refresh(){
        frame.validate();
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
        Card startCard = playDeck.pop();
        while (startCard.isWildCard()){
            startCard = playDeck.pop();
        }
        playedCards.push(startCard);
        cardStackCount = playDeck.size();

        for (int i = 0; i < players.getNumberOfPlayers(); i++) {
            for (int j = 0; j < cardsPerPlayer; j++) {
                Card newCard = playDeck.pop();
                newCard.setPlayer(players.getAllPlayers().get(i));
                players.getAllPlayers().get(i).addCard(newCard);
                refresh();
                updateBoard();
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
     * @return list of  cards that have been played
     * @see java.util.Stack Stack
     */
    public static Stack<Card> getPlayedCards() {
        return playedCards;
    }

    /**
     * Win a round
     * @param player player that won the round
     */
    public static void winRound(Player player){
        player.incrementScore();
        JOptionPane.showMessageDialog(null, player.getName() + " won the round!",
                 "Round Won", JOptionPane.INFORMATION_MESSAGE);
        int option = JOptionPane.showConfirmDialog(null, "Wanna Play Another Round?", "Continue Match",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);

        if (option == 1){ //no
            endGame();
        }
        else{ //yes
            nextRound();
        }
    }

    /**
     * Makes a play
     * @param player player that made the play
     * @param card card player played
     */
    public static void makePlay(Player player, Card card){
        card.setPlayer(null);
        card.updateCard();
        player.removeCard(card);
        playedCards.add(card);
        playedCardSection.remove(playedCard);

        if(card.isActionCard() || card.isWildCard()){
            switch (card.getCardAction()){
                case REVERSE:
                    players.reverse();
                    break;
                case SKIP:
                    players.skip();
                    break;
                case PLUS_2:
                    players.add2(playDeck);
                    break;
                case PLUS_4:
                    players.add4(playDeck);
                    break;
                default:
                    break;
            }
        }else{
         players.nextPlayerTurn();
        }
        updateBoard();
        updatePlayedCard();
        updatePlayers();
    }

    /**
     *Ends and closes game
     */
    public static void endGame(){
        int optiom = JOptionPane.showConfirmDialog(null, "Start New Match?", "New Game", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if(optiom == 1){ //no
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        }else{//yes
            players.resetCards();
            players.getAllPlayers().clear();
            players.addPlayers(createPlayers());
            players.resetPlayOrder();
            start();
        }
    }

    /**
     * Update players and their UI
     */
    public static void updatePlayers(){
        players.updatePlayerTurn();
        for (Player player:
             players.getAllPlayers()) {
            player.updatePlayerUI();
        }
    }

    /**
     * @return Player array containing created players
     * @see Player Player
     */
    public static Player[] createPlayers(){
        String player1Name = JOptionPane.showInputDialog(null, "Enter Player 1 Name: ",
                JOptionPane.INFORMATION_MESSAGE);
        String player2Name = JOptionPane.showInputDialog(null, "Enter Player 2 Name: ",
                JOptionPane.INFORMATION_MESSAGE);
        return new Player[]{new Player(player1Name, Color.cyan), new Player(player2Name, Color.ORANGE)};
    }

    /**
     *Start next round
     */
    public static void nextRound(){
        players.resetCards();
        players.resetPlayOrder();
        start();
    }

    /**
     * @return game board
     */
    public static JPanel getBoard() {
        return board;
    }

    /**
     * @return frame
     * @see JFrame JFrame
     */
    public static JFrame getFrame() {
        return frame;
    }

    /**
     * @return card that was last played
     */
    public static Card getPlayedCard() {
        return playedCard;
    }

    /**
     * @return number of cards in play deck
     */
    public static JLabel getNumCardsPlayDeck() {
        return numCardsPlayDeck;
    }

    /**
     * @return players object
     * @see Players Players
     */
    public static Players Players() {
        return players;
    }
}
