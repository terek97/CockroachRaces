import javax.swing.*;
import java.awt.*;


public abstract class
Panel extends JPanel {

    private int x1 = 100;
    private int x2 = 800;                     //для разметки дорожек
    private static int y = 150;
    private Font font = new Font("Verdana", Font.BOLD, 18);
    private Font littleFont = new Font("Verdana", Font.BOLD, 12);



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < Main.getNumbOfTrack(); i++){
            g.drawLine(x1, y, x2, y);
            g.drawLine(x1, y + 100, x2, y + 100);                                //отрисовка дорожек
            g.drawLine(x1,y, x1, y + 100);
            g.drawLine(x1 + 40, y, x1 + 40, y + 100);
            g.drawLine(x2, y, x2, y + 100);

            String str = Integer.toString(i + 1);
            g.drawString(str, x1 + 15, y + 50);
            g.setFont(font);
            g.drawString("Тараканьи Бега", 400, 80);
            g.setFont(littleFont);

            y = y +150;
        }
        y = 150;

    }
}
