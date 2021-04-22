package UNO_Card_Game;

import java.awt.*;

public class Main{

    public static void main(String[] args) {


        UNO.run();
        UNO.addPlayers(UNO.createPlayers());
        UNO.start();

    }
}
