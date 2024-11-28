package game.data;

import java.io.*;
import java.util.List;

public class HighScores {

//    File file = new File("src/game/data/high scores");

    public static void saveScores(String playerName, int score) {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("src/game/data/high scores", true)))) {
            out.println(playerName + "," + score);
        } catch (IOException e) {
            System.err.println("Error writing to high scores file: " + e.getMessage());
        }
    }

}
