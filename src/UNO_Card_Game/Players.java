package UNO_Card_Game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class Players {
    private Player player1;
    private Player player2;
    private ArrayList<Player> allPlayers = new ArrayList<>(4);
    private ArrayList<Player> playOrder = new ArrayList<>(4);

    private int numberOfPlayers = 0;
    private Player nextPlayer;
    private Player previousPlayer;
    private Player currentPlayer;

    /**
     * Adds new players
     *
     * @param playersToAdd array of players to add
     * @see Player Player
     */
    public void addPlayers(Player[] playersToAdd) {
        for (Player player :
                playersToAdd) {
            if (player1 == null) {
                player1 = player;
                UNO.getBoard().add(player1, BorderLayout.SOUTH);
                numberOfPlayers++;
                allPlayers.add(player1);
                UNO.refresh();
            } else if (player2 == null) {
                player2 = player;
                UNO.getBoard().add(player2, BorderLayout.NORTH);
                numberOfPlayers++;
                allPlayers.add(player2);
                UNO.refresh();
            }
        }
        playOrder = allPlayers;
        updatePlayerTurn();
    }

    public void add4(Stack<Card> playDeck) {
        addCards(nextPlayer, 4, playDeck);
    }
    private void addCards(Player player, int numberOfCards, Stack<Card> deck){
        for (int i = 0; i < numberOfCards; i++) {
            Card card = deck.pop();
            card.setPlayer(player);
            player.getCards().add(card);
        }
        player.setNumberOfCards(player.getNumberOfCards() + numberOfCards);
        updatePlayerCards();
        player.updatePlayerUI();
        nextPlayerTurn();
        updatePlayerTurn();
    }

    public void add2(Stack<Card> playDeck) {
        addCards(nextPlayer, 2, playDeck);
    }

    public void skip() {
        Collections.rotate(playOrder, 2);
        updatePlayerTurn();
    }

    public void reverse() {
        Collections.reverse(playOrder);
        updatePlayerTurn();
    }

    /**
     * Set player 1
     *
     * @param player player to set
     * @see Player Player
     */
    public void setPlayer1(Player player) {
        player1 = player;
    }

    /**
     * Set player 2
     *
     * @param player player to set
     * @see Player Player
     */
    public void setPlayer2(Player player) {
        player2 = player;
    }

    /**
     * @return list of players in round.match
     * @see java.util.ArrayList ArrayList
     */
    public ArrayList<Player> getAllPlayers() {
        return allPlayers;
    }

    /**
     * @return number of players in round/match
     */
    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNextPlayer(Player nextPlayer) {
        this.nextPlayer = nextPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void setPreviousPlayer(Player previousPlayer) {
        this.previousPlayer = previousPlayer;
    }

    public void nextPlayerTurn() {
        Collections.rotate(playOrder, 1);
    }


    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player getNextPlayer() {
        return nextPlayer;
    }

    public Player getPreviousPlayer() {
        return previousPlayer;
    }

    /**
     * @return player 1
     * @see Player Player
     */
    public Player getPlayer1() {
        return player1;
    }

    public void resetCards() {
        for (Player player :
                allPlayers) {
            player.getCards().clear();
            player.getCardHolder().removeAll();
            player.updatePlayerUI();
        }
    }

    /**
     * @return player 2
     * @see Player Player
     */
    public Player getPlayer2() {
        return player2;
    }

    public void updatePlayerTurn() {
        nextPlayer = playOrder.get(1);

        for (Player player:
                playOrder) {
            player.setPlayerTurn(false);
        }
        currentPlayer = playOrder.get(0);
        currentPlayer.setPlayerTurn(true);
        previousPlayer = playOrder.get(playOrder.size() - 1);
        updatePlayerCards();
    }

    public void updatePlayerCards() {
        for (Player player :
                allPlayers) {
            for (Card c :
                    player.getCards()) {
                c.updateCard();
            }
        }
    }

    public void resetPlayOrder() {
        playOrder = allPlayers;
    }
}
