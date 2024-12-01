package game.objects.coin;


import javax.swing.*;
import game.Frame.Build_a_map;
import game.Frame.D_Map;
import game.Frame.MyJLayeredPane;
import game.messages.Messages;
import game.objects.monsters.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/// מחלקה חשובה המסירה מטבעות וכן מטפלת באכילת הרוחות בעת אכילת מטבע גדול

public class CoinArray {
    public static JPanel[][] coins;
    public static JPanel[][] bigCoin;
    public static boolean move = false;

    public static Image deathImage;

    public static Image redImage;
    public static Image blueImage;
    public static Image pinkImage;
    public static Image orangeImage;

    // טעינת תמונה של רוחות מתות בעת אכילת מטבע גדול
    static {
        try {
            deathImage = javax.imageio.ImageIO.read(CoinArray.class.getResource("/game/img/GhostEatable.jpg"));

            redImage = javax.imageio.ImageIO.read(CoinArray.class.getResource("/game/img/redGhost.png"));
            blueImage = javax.imageio.ImageIO.read(CoinArray.class.getResource("/game/img/blueGhost.png"));
            pinkImage = javax.imageio.ImageIO.read(CoinArray.class.getResource("/game/img/pinkGhost.png"));
            orangeImage = javax.imageio.ImageIO.read(CoinArray.class.getResource("/game/img/orangeGhost.png"));
        } catch (Exception e) {
            System.out.println("בעיה בתמונת הרוח המתה");
        }
    }

    // הגדרת המערכים ככה א ני יודע איכן יש מטבעות
    public static void initCoins() {
        coins = new JPanel[D_Map.D_Map1.length][D_Map.D_Map1[0].length];
        bigCoin = new JPanel[D_Map.D_Map1.length][D_Map.D_Map1[0].length];
    }

    // מסיר מטבעות מעדכן ניקוד וכן מחזיק טיימר של כמה זמן יהיה מותר לאכול את המטבעות
    public static void removeCoin(int mapX, int mapY){
        if (coins[mapY][mapX] != null) {

            if (coins[mapY][mapX] instanceof Coin){
                System.out.println("אכלת מטבע גדול");


                GhostSettings.delay = 500;
                Build_a_map.score += 200;

                GhostSettings.isAteALargeCoin = true;


                Red_Ghost.setImage(deathImage);
                Blue_Ghost.setImage(deathImage);
                OrangeGhost.setImage(deathImage);
                Pink_Ghost.setImage(deathImage);

                // הזמן שמותר לאכול את המפלצות
                Timer isAteALargeCoin = new Timer(10000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        GhostSettings.isAteALargeCoin = false;

                        Red_Ghost.setImage(redImage);
                        Blue_Ghost.setImage(blueImage);
                        OrangeGhost.setImage(orangeImage);
                        Pink_Ghost.setImage(pinkImage);

                        System.out.println("נגמר");
                    }
                });

                isAteALargeCoin.setRepeats(false);
                isAteALargeCoin.start();


                // עדכון תצוגת הניקוד
                Messages.scoreLabel.setText("ניקוד: " + Build_a_map.score);
                MyJLayeredPane.playSE(1);  // השמעת צליל אכילת פרי
            }
            // סאונד אכילת מטבע
            MyJLayeredPane.playSE(2);

            coins[mapY][mapX].setVisible(false);
            coins[mapY][mapX] = null;
            Build_a_map.score += 10;
            Messages.scoreLabel.setText("ניקוד: " + Build_a_map.score);
            Messages.livesLabel.setText("חיים: ");

            Build_a_map.countEatingCoins++;


            //System.out.println(" countEatingCoins =  " + Build_a_map.countEatingCoins + ", allscore =  " + Build_a_map.allScore);
            if (Build_a_map.countEatingCoins == Build_a_map.allScore) {
                Messages.victoryAnnouncement();
            }
        }
    }
}
