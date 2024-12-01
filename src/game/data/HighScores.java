package game.data;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

// מחלקה השומרת את נתוני הניקוד ושם המשתמש בקובץ

public class HighScores {

    // פונקציה השומרת ניקוד בקובץ
    public static void saveScores(String playerName, int score) {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("src/game/data/high scores", true)))) {
            out.println(playerName + ","+ score);
        } catch (IOException e) {
            System.err.println("שגיאה בקובץ");
        }
    }

    //פונקציה הקוראת מהקובץ את הנתונים
    public static List<String> readScores() {
        List<String> scores = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/game/data/high scores"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                scores.add(line);
            }
        } catch (IOException e) {
            System.err.println("שגיאה בקריאת הקובץ");
        }
        return scores;
    }
}
