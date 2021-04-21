package UNO_Card_Game;

import java.awt.*;

public class Main{

    public static void main(String[] args) {

        UNO.createPlayer(new Player("Ash", Color.ORANGE));
        UNO.createPlayer(new Player("Sheri", Color.pink));
        UNO.run();
        UNO.start();

    }
}
