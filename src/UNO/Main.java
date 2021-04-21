package UNO;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame{


    public static void main(String[] args) {

        UnoGame UNO = new UnoGame();
        UNO.createPlayer(new Player("Dorian", Color.MAGENTA));
        UNO.createPlayer(new Player("Amir", Color.cyan));
        UNO.createPlayer(new Player("Ash", Color.ORANGE));
        UNO.createPlayer(new Player("Sheri", Color.pink));
        UNO.run();
        UNO.start();

    }
}
