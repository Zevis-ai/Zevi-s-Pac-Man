package game.Frame;

import javax.swing.*;
import java.awt.*;
public class Build_a_map {

    public static void buildMap(JPanel mapjpanel){
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
//                    coins[i][j] = coin;
//                    allScore++;

                }
                if (D_Map.D_Map1[i][j] == 1) {
                    jPanel1.setBackground(Color.BLUE);

                }
                if (D_Map.D_Map1[i][j] == 2) {

                    jPanel1.setBackground(Color.BLACK); // הגדרת רקע שחור
//                    coin = new Coin();
//                    jPanel1.add(coin);

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
                mapjpanel.add(jPanel1);
            }

        } // and of loop.
    }
}
