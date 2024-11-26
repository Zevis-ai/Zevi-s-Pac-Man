package game.objects.coin;

import javax.swing.*;

import game.Frame.Build_a_map;
import game.Frame.D_Map;
import game.Frame.MyJLayeredPane;
import game.Game;
import game.key.MyKeyListener;
import game.messages.Messages;
import game.objects.monsters.Blue_Ghost;
import game.objects.monsters.GhostSettings;

public class CoinArray {
    public static JPanel[][] coins;
    public static JPanel[][] bigCoin;
    public static boolean move = false;


    public static void initCoins() {
        coins = new JPanel[D_Map.D_Map1.length][D_Map.D_Map1[0].length];
        bigCoin = new JPanel[D_Map.D_Map1.length][D_Map.D_Map1[0].length];
    }

    public static void removeCoin(int mapX, int mapY){
        if (coins[mapY][mapX] != null) {

            if (coins[mapY][mapX] instanceof Coin){
                System.out.println("אכלת מטבע גדול");
                GhostSettings.delay = 500;
            }
            // סאונד אכילת מטבע
            MyJLayeredPane.playSE(2);

            coins[mapY][mapX].setVisible(false);
            coins[mapY][mapX] = null;
            Build_a_map.score += 10;
            Messages.scoreLabel.setText("ניקוד: " + Build_a_map.score);
            Messages.livesLabel.setText("חיים: ");



            if (Build_a_map.score == Build_a_map.allScore) {
                Messages.victoryAnnouncement();
            }
        }
    }

    public static int getSceore(){
        return Build_a_map.score;
    }


}
