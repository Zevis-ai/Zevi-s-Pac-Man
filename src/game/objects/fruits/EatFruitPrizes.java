package game.objects.fruits;

import game.Frame.Build_a_map;
import game.Frame.MyJLayeredPane;
import game.key.MyKeyListener;
import game.messages.Messages;

public class EatFruitPrizes {
    public static void removeFruit() {
        // בדיקת התנגשות עם הדובדבן
        if (MyJLayeredPane.persiaCherry.isVisible()) {
            int x = Math.abs(MyKeyListener.player.getX() - MyJLayeredPane.persiaCherry.getX());
            int y = Math.abs(MyKeyListener.player.getY() - MyJLayeredPane.persiaCherry.getY());

            if (x < 20 && y < 20) {
                MyJLayeredPane.persiaCherry.setVisible(false);
                Build_a_map.score += 50;
                Messages.scoreLabel.setText("ניקוד: " + Build_a_map.score);
                MyJLayeredPane.playSE(1);
            }
        }

        // בדיקת התנגשות עם התפוח
        if (MyJLayeredPane.apple.isVisible()) {
            int x = Math.abs(MyKeyListener.player.getX() - MyJLayeredPane.apple.getX());
            int y = Math.abs(MyKeyListener.player.getY() - MyJLayeredPane.apple.getY());

            if (x < 20 && y < 20) {
                MyJLayeredPane.apple.setVisible(false);
                Build_a_map.score += 100;  // התפוח שווה 100 נקודות
                Messages.scoreLabel.setText("ניקוד: " + Build_a_map.score);
                MyJLayeredPane.playSE(1);
            }
        }
    }
}
