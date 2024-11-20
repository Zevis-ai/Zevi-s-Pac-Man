package game.Frame;
import javax.swing.*;
import java.awt.*;

public class MyJPanel extends JPanel {

    public MyJPanel(){
        setLayout(new GridLayout(D_Map.D_Map1.length, D_Map.D_Map1[0].length));
        setBounds(0, 0, D_Map.MAP_WIDTH_SIZE , D_Map.MAP_HEIGHT_SIZE * 2);
        setPreferredSize(new Dimension(D_Map.MAP_WIDTH_SIZE , D_Map.MAP_HEIGHT_SIZE+ 200));
        Build_a_map.buildMap(this); // העברת הפאנל הנוכחי לבניית המפה
    }
}
