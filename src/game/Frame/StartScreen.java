package game.Frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class StartScreen extends JFrame {
    private static final int SCREEN_WIDTH = 500;
    private static final int SCREEN_HEIGHT = 500;

    public StartScreen() {
        setTitle("Pac-Man Game");
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT+50);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // יצירת פאנל ראשי עם Layout מוחלט
        JPanel mainPanel = new JPanel(null);
        mainPanel.setBackground(Color.BLACK);

        // הוספת תמונת הרקע
        try {
            String imagePath = "src/game/img/5904435870348461557.jpg";
            File imageFile = new File(imagePath);
            if (imageFile.exists()) {
                ImageIcon logoIcon = new ImageIcon(imagePath);
                // התאמת גודל התמונה
                Image image = logoIcon.getImage();
                Image newimg = image.getScaledInstance(SCREEN_WIDTH, SCREEN_HEIGHT, java.awt.Image.SCALE_SMOOTH);
                logoIcon = new ImageIcon(newimg);
                
                JLabel backgroundLabel = new JLabel(logoIcon);
                backgroundLabel.setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
                mainPanel.add(backgroundLabel);
            } else {
                System.err.println("Image file not found: " + imageFile.getAbsolutePath());
            }
        } catch (Exception e) {
            System.err.println("Error loading image: " + e.getMessage());
        }

        // יצירת כפתור התחלה
        JButton startButton = new JButton("Start Game");
        startButton.setFont(new Font("Arial", Font.BOLD, 20));
        startButton.setBackground(Color.YELLOW);
        startButton.setForeground(Color.BLACK);
        startButton.setFocusPainted(false);
        startButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

        // מיקום הכפתור בתחתית המסך
        int buttonWidth = 200;
        int buttonHeight = 40;
        int buttonX = (SCREEN_WIDTH - buttonWidth) / 2;
        int buttonY = SCREEN_HEIGHT - buttonHeight - 20; // 20 פיקסלים מהתחתית
        startButton.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        
        // הוספת אירוע לחיצה על הכפתור
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
                SwingUtilities.invokeLater(() -> {
                    MyFrame game = new MyFrame();
                    game.setVisible(true);
                });
            }
        });

        // הוספת הכפתור מעל התמונה
        mainPanel.add(startButton);
        mainPanel.setComponentZOrder(startButton, 0); // הכפתור יהיה מעל התמונה

        add(mainPanel);
    }
}
