package UNO;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Stack;

public class UnoGame extends JFrame {
   private Player player1;
    private Player player2;
    private Player player3;
    private Player player4;
    private ArrayList<Player> players = new ArrayList<>(4);
    private int numberOfPlayers = 0;
   private JPanel board = new JPanel(new BorderLayout());
    public void run(){
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setTitle("UNO | Online");




        JPanel boardCentre = new JPanel(new BorderLayout());
        JLabel title = new JLabel("UNO");
        boardCentre.add( title, BorderLayout.CENTER);
        boardCentre.setSize(new Dimension(200, 200));
        boardCentre.setVisible(true);
        title.setFont(new Font( "Backslash", Font.BOLD, 48));
        title.setVisible(true);
        title.setHorizontalAlignment(JLabel.CENTER);
        board.add(boardCentre, BorderLayout.CENTER);
        add(board);
    }

    public void createPlayer(Player player) {
        if (player1 == null) {
            player1 = player;
            board.add(player1, BorderLayout.SOUTH);
            numberOfPlayers++;
            players.add(player1);
            refresh();
        }
        else if (player2 == null) {
            player2 = player;
            board.add(player2, BorderLayout.WEST);
            numberOfPlayers++;
            players.add(player2);
            refresh();
        }
        else if (player3 == null) {
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
        }
        //add(new JOptionPane("Game is full!", JOptionPane.INFORMATION_MESSAGE, JOptionPane.OK_OPTION));
        refresh();
    }

    public void start(){
        shuffleShareCards();
    }

    public void refresh(){
        revalidate();
        repaint();
    }

    public void shuffleShareCards() {
        Stack<Card> playDeck = new CardDeck().createPlayDeck();

        for (int i = 0; i < numberOfPlayers; i++) {
            for (int j = 0; j < 7; j++) {
                players.get(i).getCardHolder().addCard(playDeck.pop());
            }
        }
        refresh();
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Player getPlayer3() {
        return player3;
    }

    public Player getPlayer4() {
        return player4;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public void setPlayer3(Player player3) {
        this.player3 = player3;
    }
    public void setPlayer4(Player player4) {
        this.player4 = player4;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
}
