package game.Frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class StartScreen{

    public static String userName;

   public static void createWelcomeFrame(){
       // יצירת הגפראם
       JFrame welcomeFrame = new JFrame("welcome");
       welcomeFrame.setSize(D_Map.MAP_WIDTH_SIZE+100, D_Map.MAP_HEIGHT_SIZE);
       welcomeFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
       welcomeFrame.setLayout(new BorderLayout());

       // יצירת הפנאל העליון
       JPanel topPanel = new JPanel();
       topPanel.setBackground(new Color(246,4,255));
       topPanel.setLayout(new BoxLayout(topPanel, 1));

       // יצירת מקום לכתיבת טקסט
       JLabel welcomeLabel = new JLabel("ברוכים הבאים למשחק פקמן !");
       welcomeLabel.setFont(new Font("Courier New", 1,40));
       welcomeLabel.setForeground(new Color(230,255,4));
       welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

       // יצירת מקום לכתיבת טקסט
       JLabel nameLabel = new JLabel("נא הכנס את שמך:");
       nameLabel.setFont(new Font("Courier New", 1,25));
       nameLabel.setForeground(new Color(230,255,4));
       nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

       // יצירת מקום לקבלת טקסט
       JTextField nameField = new JTextField(25);
       nameField.setMaximumSize(new Dimension(300,30));
       nameField.setAlignmentX(Component.CENTER_ALIGNMENT);

       // הוספה לפנאל
       topPanel.add(Box.createVerticalStrut(20));
       topPanel.add(welcomeLabel);
       topPanel.add(Box.createVerticalStrut(10));
       topPanel.add(nameLabel);
       topPanel.add(Box.createVerticalStrut(10));
       topPanel.add(nameField);
       topPanel.add(Box.createVerticalStrut(20));

       // הוספת הכל לגפראם הכללי
       welcomeFrame.add(topPanel, "North");

       // תמונה
       JPanel centerPanel = new JPanel(){
           protected void paintComponent(Graphics g){
               super.paintComponent(g);
               ImageIcon imageIcon = new ImageIcon("src/game/img/5904435870348461557.jpg");
               g.drawImage(imageIcon.getImage(), 0,0,getWidth(),getHeight(), null);
           }
       };

       centerPanel.setOpaque(true);
       welcomeFrame.add(centerPanel, BorderLayout.CENTER);

       // פנאל לחצנים
       JPanel buttonPanel = new JPanel();
       buttonPanel.setLayout(new FlowLayout());
       buttonPanel.setBackground(new Color(246,4,255));

       JButton startButton = new JButton("לחץ כאן ותתחיל להנות!");
       startButton.setFont(new Font("Courier New", 1,18));
       startButton.setBackground(new Color(246,4,255));
       startButton.setForeground(Color.yellow);
       startButton.setFocusPainted(false);

       JButton highScoresButton = new JButton("טבלת שיאים");
       highScoresButton.setFont(new Font("Courier New", 1,18));
       highScoresButton.setBackground(new Color(246,4,255));
       highScoresButton.setForeground(Color.yellow);
       highScoresButton.setFocusPainted(false);

       // הוספת הלחצנים לפנאל ולפרם
       buttonPanel.add(startButton);
       buttonPanel.add(highScoresButton);

       welcomeFrame.add(buttonPanel, BorderLayout.SOUTH);

       // הוספת מאזין ללחצן ההתחלה
       startButton.addActionListener(e -> {
           //קבלת השם
           userName = nameField.getText();
           // בדיקה האם השם לא ריק
           if (userName.isEmpty()){
               // יצירת שיאה לקבלת שוב שם
               JOptionPane.showMessageDialog(welcomeFrame, "שחכת להזין את שמך","אופס..",0);
           } else {
               //סגירת החלון והפעלת המשחק
               welcomeFrame.dispose();
               new MyFrame();
           }
       });

       // הצגת הפרם
       welcomeFrame.setLocationRelativeTo(null);
       welcomeFrame.setVisible(true);
   }

    public static String getUserName() {
        return userName;
    }
}
