package game;


import game.Frame.MyFrame;
import game.Frame.MyJLayeredPane;
import game.Frame.StartScreen;


import javax.swing.*;

import static game.Frame.StartScreen.createWelcomeFrame;

public class Game {
    public static void main(String[] args) throws InterruptedException {

        //new MyFrame();
        createWelcomeFrame();
//     הודעת פתיחה
//        SwingUtilities.invokeLater(() -> {
//            StartScreen startScreen = new StartScreen();
//            startScreen.setVisible(true);
//        });
//        MyJLayeredPane.playSE(1);
//        Thread.sleep(5000);

    }
}
