import gui.menu.MainMenuPanel;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException e) {
            UIManager.getCrossPlatformLookAndFeelClassName();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        JFrame frame = new MainMenuPanel("StegTools");
        //frame.setLocationRelativeTo(null);

        frame.getContentPane().setBackground(new Color(72, 0, 0));

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (dimension.width * 0.3);
        int height = (int) (dimension.width * 0.3);
        frame.setSize(width, height);
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);

        frame.setVisible(true);

    }

}
