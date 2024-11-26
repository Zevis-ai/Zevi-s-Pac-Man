package game.Frame;

import game.objects.Player.ImagePacMan;
import game.objects.coin.Coin;
import game.objects.coin.CoinArray;
import game.objects.fruits.Cherry;

import javax.swing.*;
import java.awt.*;

import static game.objects.coin.CoinArray.bigCoin;
import static game.objects.coin.CoinArray.coins;

public class Build_a_map {

    public static int score;
    public static int life = 3;
    public static int allScore = 0;
    public static Coin bigCoin;
    public static ImagePacMan imagePacMan;

    public static void buildMap(JPanel mapjpanel){
        score = 0;
        allScore = 0;
        CoinArray.initCoins();  // Initialize coins array before building the map
        for (int i = 0; i < D_Map.D_Map1.length; i++) {
            for (int j = 0; j < D_Map.D_Map1[0].length; j++) {
                JPanel jPanel1 = new JPanel();
                if (D_Map.D_Map1[i][j] == 0) {
                    jPanel1.setBackground(Color.BLACK);
//                    jPanel1.setLayout(new GridLayout()); // שהמטבע יהיה באמצע הפאנל
                    JPanel coin = new JPanel();
                    coin.setPreferredSize(new Dimension(6,6));
                    coin.setBackground(Color.YELLOW);
                    jPanel1.add(coin);
                    coins[i][j] = coin;
                    allScore++;

                }
                if (D_Map.D_Map1[i][j] == 1) {
                    jPanel1.setBackground(Color.BLUE);

                }
                if (D_Map.D_Map1[i][j] == 2) {
                    jPanel1.setBackground(Color.BLACK); // הגדרת רקע שחור
                    bigCoin = new Coin();
                    jPanel1.add(bigCoin);
                    coins[i][j] = bigCoin;
                }

                if (D_Map.D_Map1[i][j] == 3) {
                    jPanel1.setBackground(Color.BLACK);

                }
                if (D_Map.D_Map1[i][j] == 5) {
                    jPanel1.setBackground(Color.BLACK);

                }

                if (D_Map.D_Map1[i][j] == 9) {
                    jPanel1.setBackground(Color.cyan);

                }
                if (D_Map.D_Map1[i][j] == 9) {
                    jPanel1.setBackground(Color.cyan);

                }
                if (D_Map.D_Map1[i][j] == 10) {
                    jPanel1.setBackground(Color.cyan);
                    Cherry cherry = new Cherry();
                    jPanel1.add(cherry);

                }
                if (D_Map.D_Map1[i][j] == 11) {
                    jPanel1.setBackground(Color.cyan);
                    imagePacMan = new ImagePacMan();
                    imagePacMan.setSize(10,10);
                    jPanel1.add(imagePacMan);

                }
                mapjpanel.add(jPanel1);
            }

        } // and of loop.
    }
}
