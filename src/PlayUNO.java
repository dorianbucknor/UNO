import UNO_Card_Game.UNO;

public class PlayUNO {
    public static void main(String[] args) {
        UNO.getInstance().run(); //create new game
        UNO.Players().addPlayers(UNO.createPlayers());//add players
        UNO.start();//start match
    }
}
