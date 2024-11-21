package game.Frame;

import game.objects.Player.PacManPlayer;
import game.messages.Messages;
import javax.swing.*;
import java.awt.*;

import game.objects.monsters.Blue_Ghost;
import game.objects.monsters.Orange_Ghost;
import game.objects.monsters.Pink_Ghost;
import game.objects.monsters.Red_Ghost;

public class MyJLayeredPane extends JLayeredPane {
    private MyJPanel mapPanel;
    private PacManPlayer player;
    private Blue_Ghost blue_ghost;
    private Orange_Ghost orange_ghost;
    private Pink_Ghost pink_ghost;
    private Red_Ghost red_ghost;

    public MyJLayeredPane(){
        setLayout(null);  // חשוב! כדי שה-bounds יעבדו
        setPreferredSize(new Dimension(D_Map.MAP_WIDTH_SIZE, D_Map.MAP_HEIGHT_SIZE));
        
        // יצירת המפה
        mapPanel = new MyJPanel();
        mapPanel.setBounds(0, 0, D_Map.MAP_WIDTH_SIZE, D_Map.MAP_HEIGHT_SIZE);
        
        // יצירת השחקן
        player = new PacManPlayer();
        // מכשפות
        blue_ghost = new Blue_Ghost();
        orange_ghost = new Orange_Ghost();
        pink_ghost = new Pink_Ghost();
        red_ghost = new Red_Ghost();
        
        // הוספת השכבות
        add(mapPanel, Integer.valueOf(1));    // שכבת המפה
        add(player, Integer.valueOf(2));

        add(blue_ghost, Integer.valueOf(4));// שכבת השחקן מעל המפה
        add(orange_ghost, Integer.valueOf(4));
        add(pink_ghost, Integer.valueOf(4));
        add(red_ghost, Integer.valueOf(4));

        
        // הוספת תצוגת ניקוד וחיים
        add(Messages.scoreLabel, Integer.valueOf(3));
        add(Messages.livesLabel, Integer.valueOf(3));
        
        // וידוא שהשחקן והתצוגה נראים
        player.setVisible(true);
        mapPanel.setVisible(true);
        Messages.scoreLabel.setVisible(true);
        Messages.livesLabel.setVisible(true);

        blue_ghost.setVisible(true);
        orange_ghost.setVisible(true);
        pink_ghost.setVisible(true);
        red_ghost.setVisible(true);

        // Set player reference for ghosts
        pink_ghost.setPlayer(player);
    }
    
    public PacManPlayer getPlayer() {
        return player;
    }
    
    public MyJPanel getMapPanel() {
        return mapPanel;
    }
}
