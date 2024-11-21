package game;

import game.Frame.MyFrame;
import game.Frame.StartScreen;
import javax.swing.*;

public class Game {
    public static void main(String[] args) {
        new MyFrame();

//        הודעת פתיחה
//        SwingUtilities.invokeLater(() -> {
//            StartScreen startScreen = new StartScreen();
//            startScreen.setVisible(true);
//        });
    }
}
