package game.objects.coin;

import javax.swing.*;
import game.Frame.D_Map;

public class CoinArray {
    public static JPanel[][] coins;


    public static void initCoins() {
        coins = new JPanel[D_Map.D_Map1.length][D_Map.D_Map1[0].length];
    }

    public static void removeCoin(int mapX, int mapY){
        if (coins[mapY][mapX] != null) {
            coins[mapY][mapX].setVisible(false);
            coins[mapY][mapX] = null;
//            score++;
//            Messages.scoreLabel.setText("ניקוד: " + Game.score);
//            Messages.livesLabel.setText("חיים: ");
//            if (Game.score == Game.allScore) {
//                Messages.victoryAnnouncement();
//            }
        }
    }
}
