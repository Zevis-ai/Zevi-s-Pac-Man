package game.objects.coin;

import javax.swing.*;

import game.Frame.Build_a_map;
import game.Frame.D_Map;
import game.Frame.MyJLayeredPane;
import game.Game;
import game.messages.Messages;

public class CoinArray {
    public static JPanel[][] coins;


    public static void initCoins() {
        coins = new JPanel[D_Map.D_Map1.length][D_Map.D_Map1[0].length];
    }

    public static void removeCoin(int mapX, int mapY){
        if (coins[mapY][mapX] != null) {
            MyJLayeredPane.playSE(2);
            coins[mapY][mapX].setVisible(false);
            coins[mapY][mapX] = null;
            Build_a_map.score++;
            Messages.scoreLabel.setText("ניקוד: " + Build_a_map.score);
            Messages.livesLabel.setText("חיים: ");
            if (Build_a_map.score == Build_a_map.allScore) {
                Messages.victoryAnnouncement();
            }
        }
    }
}
