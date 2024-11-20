package game;

import game.Frame.StartScreen;
import javax.swing.*;

public class Game {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StartScreen startScreen = new StartScreen();
            startScreen.setVisible(true);
        });
        System.out.println("z");
    }
}
