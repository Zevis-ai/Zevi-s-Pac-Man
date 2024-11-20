package game.Frame;

import game.objects.Player.PacManPlayer;
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
        
        // וידוא שהשחקן נראה
        player.setVisible(true);
        mapPanel.setVisible(true);
    }
    
    public PacManPlayer getPlayer() {
        return player;
    }
    
    public MyJPanel getMapPanel() {
        return mapPanel;
    }
}
