package UNO;

import javax.swing.*;
import java.awt.*;

public class Main{

    public static void main(String[] args) {

        UnoGame UNO = new UnoGame();
        UNO.createPlayer(new Player("Ash", Color.ORANGE));
        UNO.createPlayer(new Player("Sheri", Color.pink));
        UNO.run();
        UNO.start();

    }
}
