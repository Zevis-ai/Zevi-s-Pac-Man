package game.Frame;

import game.objects.Player.ImagePacMan;
import game.objects.Player.PacManPlayer;
import game.messages.Messages;
import javax.swing.*;
import java.awt.*;

import game.objects.fruits.*;
import game.objects.monsters.Blue_Ghost;
import game.objects.monsters.OrangeGhost;
import game.objects.monsters.Pink_Ghost;
import game.objects.monsters.Red_Ghost;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyJLayeredPane extends JLayeredPane {
    public static MyJLayeredPane instance;
    public static MyJPanel mapPanel;
    private PacManPlayer player;
    private Blue_Ghost blue_ghost;
    private OrangeGhost orange_ghost;
    private Pink_Ghost pink_ghost;
    public static Red_Ghost red_ghost;  //
    private Cherry cherry;
    public static Cherry persiaCherry;
    public static Apple apple;
    public static Apple persiaApple;
    public static Orange orange;
    public static Strawberry strawberry;
    private ImagePacMan imagePacMan1;
    private ImagePacMan imagePacMan2;
    private ImagePacMan imagePacMan3;
    Timer cherryTimer;
    Timer appleTimer;

    public static game.sound.Sound sound = new game.sound.Sound();

    public MyJLayeredPane(){
        instance = this;
        setLayout(null);  // חשוב! כדי שה-bounds יעבדו
        setPreferredSize(new Dimension(D_Map.MAP_WIDTH_SIZE, D_Map.MAP_HEIGHT_SIZE));
        
        // יצירת המפה
        mapPanel = new MyJPanel();
        mapPanel.setBounds(0, 0, D_Map.MAP_WIDTH_SIZE, D_Map.MAP_HEIGHT_SIZE);
        
        // יצירת השחקן
        player = new PacManPlayer();
        // מכשפות
        blue_ghost = new Blue_Ghost();
        orange_ghost = new OrangeGhost();
        pink_ghost = new Pink_Ghost();
        red_ghost = new Red_Ghost();  // יצירת הרוח האדומה
        cherry = new Cherry();
        persiaCherry = new Cherry();
        apple = new Apple();
        persiaApple = new Apple();
        orange = new Orange();
        strawberry = new Strawberry();
        
        // יצירת תמונות חיים
        imagePacMan1 = new ImagePacMan();
        imagePacMan2 = new ImagePacMan();
        imagePacMan3 = new ImagePacMan();

        // הגדרת מיקום לתמונות החיים
        imagePacMan1.setBounds(40, 620, 20, 20);
        imagePacMan2.setBounds(70, 620, 20, 20);
        imagePacMan3.setBounds(100, 620, 20, 20);

        persiaCherry.setBounds(260, 340, 20, 20);
        apple.setBounds(60, 640, 20, 20);
        persiaApple.setBounds(260, 340, 20, 20);
        cherry.setBounds(40,640,35,35);
        orange.setBounds(80,640,20,20);
        strawberry.setBounds(100,640,20,20);



        // הוספת השכבות
        add(mapPanel, Integer.valueOf(1));    // שכבת המפה
        add(player, Integer.valueOf(2));

        add(blue_ghost, Integer.valueOf(4));// שכבת השחקן מעל המפה
        add(orange_ghost, Integer.valueOf(4));
        add(pink_ghost, Integer.valueOf(4));
        add(red_ghost, Integer.valueOf(4));

        add(cherry,Integer.valueOf(5));
        add(persiaCherry,Integer.valueOf(5));
        add(apple,Integer.valueOf(5));
        add(persiaApple,Integer.valueOf(5));
        add(orange, Integer.valueOf(5));
        add(strawberry, Integer.valueOf(5));

        add(imagePacMan1,Integer.valueOf(6));
        add(imagePacMan2,Integer.valueOf(6));
        add(imagePacMan3,Integer.valueOf(6));

        
        // הוספת תצוגת ניקוד וחיים
        add(Messages.scoreLabel, Integer.valueOf(3));
        add(Messages.livesLabel, Integer.valueOf(3));
        add(Messages.nameLabel, Integer.valueOf(3));

        // וידוא שהשחקן והתצוגה נראים
        player.setVisible(true);
        mapPanel.setVisible(true);
        Messages.scoreLabel.setVisible(true);
        Messages.livesLabel.setVisible(true);

        blue_ghost.setVisible(true);
        orange_ghost.setVisible(true);
        pink_ghost.setVisible(true);
        red_ghost.setVisible(true);

        updateLifeImages();

        cherry.setVisible(true);
        persiaCherry.setVisible(false);
        apple.setVisible(true);
        persiaApple.setVisible(false);
        strawberry.setVisible(true);


        cherryTimer = new Timer(15000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                persiaCherry.setVisible(true);
                cherry.setVisible(false);
                ((Timer)e.getSource()).stop();
            }
        });

        appleTimer = new Timer(25000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                persiaApple.setVisible(true);
                apple.setVisible(false);
                ((Timer)e.getSource()).stop();
            }
        });

        cherryTimer.setRepeats(false);
        cherryTimer.start();
        appleTimer.setRepeats(false);  // רק פעם אחת
        appleTimer.start();  // מפעילים את הטיימר

//        // Set player reference for ghosts
//        blue_ghost.setPlayer(player);
//        orange_ghost.setPlayer(player);
//        pink_ghost.setPlayer(player);
//        red_ghost.setPlayer(player);
    }
    
    public PacManPlayer getPlayer() {
        return player;
    }
    
    public MyJPanel getMapPanel() {
        return mapPanel;
    }

    public static void playMusic(int i){
        sound.setFile(i);
        sound.play();
        sound.loop();
    }

    public static void playSE(int i){
        sound.setFile(i);
        sound.play();
    }
    
    public void updateLifeImages() {
        // עדכון נראות תמונות החיים בהתאם למספר החיים הנוכחי
        switch (Build_a_map.life) {
            case 3:
                imagePacMan1.setVisible(true);
                imagePacMan2.setVisible(true);
                imagePacMan3.setVisible(true);
                break;
            case 2:
                imagePacMan1.setVisible(false);
                imagePacMan2.setVisible(true);
                imagePacMan3.setVisible(true);
                break;
            case 1:
                imagePacMan1.setVisible(false);
                imagePacMan2.setVisible(false);
                imagePacMan3.setVisible(true);
                break;
            default:
                imagePacMan1.setVisible(false);
                imagePacMan2.setVisible(false);
                imagePacMan3.setVisible(false);
                break;
        }
    }

    // סינגלטון
    public static MyJLayeredPane getInstance() {
        return instance;
    }

    public static void setLoc(JPanel ghost, int x, int y) {
        SwingUtilities.invokeLater(() -> {
            ghost.setLocation(x, y);
            ghost.revalidate();
            ghost.repaint();
            if (instance != null) {
                instance.revalidate();
                instance.repaint();
            }
        });
    }
}
