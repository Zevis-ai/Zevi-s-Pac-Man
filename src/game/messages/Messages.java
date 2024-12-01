package game.messages;

import game.Frame.MyJLayeredPane;
import game.Frame.StartScreen;
import game.data.HighScores;

import javax.swing.*;
import java.awt.*;
import static game.Frame.Build_a_map.*;
import static game.objects.coin.CoinArray.coins;

// מחלקה האחריית על ההודעות של אבוד והניקוד וכו

public class Messages {
    public static JLabel scoreLabel;// ניקוד
    public static JLabel livesLabel; //  חיים
    public static JLabel nameLabel; //  שם
    public static MyJLayeredPane frame;

    static {
        scoreLabel = new JLabel("ניקוד: 0");
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        scoreLabel.setForeground(Color.black);
        scoreLabel.setBounds(10, 10, 200, 30);
        scoreLabel.setLocation(420,625);

        livesLabel = new JLabel("חיים: 3");
        livesLabel.setFont(new Font("Arial", Font.BOLD, 20));
        livesLabel.setForeground(Color.red);
        livesLabel.setBounds(10, 50, 200, 30);
        livesLabel.setLocation(340, 625);

        nameLabel = new JLabel("שם");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        nameLabel.setForeground(Color.black);
        nameLabel.setBounds(10, 50, 200, 30);
        nameLabel.setLocation(235, 625);
    }

    // הודעת ניצחון
    public static void victoryAnnouncement() {
        JOptionPane.showMessageDialog(null, " מזל טוב  " + StartScreen.getUserName() + "! הניקוד הסופי שלך הוא:  " + score);
        HighScores.saveScores(StartScreen.getUserName(), score);
        System.exit(0); // מפסיק משחק
    }

    // הודעת אבוד
    public static void gameOver(int score) {
        // שמירת הניקוד לפני הצגת ההודעה
        HighScores.saveScores(StartScreen.getUserName(), score);
        JOptionPane.showMessageDialog(null, StartScreen.getUserName() + " 💀💀 " + "הניקוד שלך הוא " + score);
//        StartScreen.createWelcomeFrame();
        System.exit(0);


    }

    // הודעת ציון
    public static void ScoreAnnouncement(int mapX, int mapY){
        if (coins[mapY][mapX] != null) {
            coins[mapY][mapX].setVisible(false);
            coins[mapY][mapX] = null;
            score++;
            Messages.scoreLabel.setText("ניקוד: " + score);
          //  Messages.livesLabel.setText("חיים: " + life);
            if (score == allScore) {
                Messages.victoryAnnouncement();
            }
        }
    }
}
