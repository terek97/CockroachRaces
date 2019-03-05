import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static com.sun.javafx.scene.control.skin.Utils.getResource;


public class DrawingCockroach extends Panel implements Runnable{

    private static String path = new File("").getAbsolutePath();

    private Image cockr;
    private Thread draw;
    private static int y = 150;
    private static Set<Cockroach> setOfCoordinates = new HashSet<>();
    private volatile boolean isCancel = false;

    //
    //при двойном щелчке мыши на прямоугольник таракана, мы его “подгоняем” (метод rush)
    //

    private MouseListener listener = new MouseAdapter() {
        public void mousePressed(MouseEvent e) {
            if (e.getClickCount() == 2 && !e.isConsumed()){
                e.consume();
                    for (Cockroach cockroach : setOfCoordinates) {
                        if (cockroach.getR().contains(e.getPoint()) && cockroach.getEx() < 677) {
                            cockroach.rush();
                        }
                    }
            }
        }
    };



    public DrawingCockroach() {


        for (int i = 0; i < Main.getNumbOfTrack(); i++) {
            String str = Integer.toString(i + 1);
            Cockroach cockroach = new Cockroach("на " + str + " дорожке", i + 1, 150 * i);         //создание тараканов, количество соответсвует количеству дорожек
            LocationData.setOfCoordinates.add(cockroach);                                                          //и сразу инфу сохраняем в классе
        }


        this.addMouseListener(listener);
        cockr = new ImageIcon(this.getClass().getResource("Cockroach.gif")).getImage();  // загрузка картинки с диска ClassInSrcFolder.getClass.getResource("Cockroach.gif");
        System.out.println(path);

        draw = new Thread(this, "drawTarakan");
        draw.start();
    }

    @Override
    public void run() {


        if (!isCancel) {

            while (true) {

                setOfCoordinates = LocationData.setOfCoordinates;
                y = 150;

                try {
                    Thread.sleep(5);                                                                        //раз в 5 миллисек. забираем информацию о всех тараканах
                } catch (InterruptedException e) {                        // и перерисовываем их
                    e.printStackTrace();
                }
                for (Cockroach cockroach : LocationData.setOfCoordinates) {           //запускаем проверку на лидерство тараканов
                    cockroach.isLeader();

                    repaint();
                }
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;


        for (Cockroach cockroach : setOfCoordinates) {

            //отрисовка тараканов

            g2d.drawImage(cockr, cockroach.getEx(), cockroach.getNumber()*150 + 15, null);
            y += 150;
        }
        y = 150;
    }

    //"убийство" потока
    public void kill() {
        isCancel = true;
    }

    //запустить поток заново
    public void startAgain() {

        Races.setIsFinished(false);    //забег был прерван

        Thread drawAgain = new Thread(this, "drawTarakan");
        drawAgain.start();

        for (int i = 0; i < Main.getNumbOfTrack(); i++) {
            String str = Integer.toString(i + 1);


            Cockroach cockroach = new Cockroach("на " + str + " дорожке", i + 1, 150 * i);
            LocationData.setOfCoordinates.add(cockroach);
        }
    }
}
