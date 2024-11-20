package game.messages;

import javax.swing.*;
import java.awt.*;

public class Messages {
    public static JLabel scoreLabel;
    public static JLabel livesLabel; //  חיים

    static {
        scoreLabel = new JLabel("ניקוד: 0");
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        scoreLabel.setForeground(Color.black);
        scoreLabel.setBounds(10, 10, 200, 30);
        scoreLabel.setLocation(430,625);

        livesLabel = new JLabel("חיים: 3");
        livesLabel.setFont(new Font("Arial", Font.BOLD, 20));
        livesLabel.setForeground(Color.red);
        livesLabel.setBounds(10, 50, 200, 30);
        livesLabel.setLocation(350, 625);

    }

    public static void victoryAnnouncement() {
        JOptionPane.showMessageDialog(null, "מזל טוב! הניקוד הסופי שלך: "); //+ Game.score
        System.exit(0); // מפסיק משחק
    }
}
