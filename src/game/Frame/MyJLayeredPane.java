package game.Frame;

import game.objects.Player.PacManPlayer;
import game.messages.Messages;
import javax.swing.*;
import java.awt.*;
import game.Frame.MyJPanel;
import game.Frame.D_Map;

public class MyJLayeredPane extends JLayeredPane {
    private MyJPanel mapPanel;
    private PacManPlayer player;

    public MyJLayeredPane(){
        setLayout(null);  // חשוב! כדי שה-bounds יעבדו
        setPreferredSize(new Dimension(D_Map.MAP_WIDTH_SIZE, D_Map.MAP_HEIGHT_SIZE));
        
        // יצירת המפה
        mapPanel = new MyJPanel();
        mapPanel.setBounds(0, 0, D_Map.MAP_WIDTH_SIZE, D_Map.MAP_HEIGHT_SIZE);
        
        // יצירת השחקן
        player = new PacManPlayer();
        
        // הוספת השכבות
        add(mapPanel, Integer.valueOf(1));    // שכבת המפה
        add(player, Integer.valueOf(2));      // שכבת השחקן מעל המפה
        
        // הוספת תצוגת ניקוד וחיים
        add(Messages.scoreLabel, Integer.valueOf(3));
        add(Messages.livesLabel, Integer.valueOf(3));
        
        // וידוא שהשחקן והתצוגה נראים
        player.setVisible(true);
        mapPanel.setVisible(true);
        Messages.scoreLabel.setVisible(true);
        Messages.livesLabel.setVisible(true);
    }
    
    public PacManPlayer getPlayer() {
        return player;
    }
    
    public MyJPanel getMapPanel() {
        return mapPanel;
    }
}
