import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class MyWindow extends JFrame {

    private ArrayList<JTextField> jTextFields = new ArrayList<>();
    private static TextFields leader;
    public Box box;


    public MyWindow() {
        setTitle("Cockroach races");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1100, 800);                                     //параметры окна
        setResizable(false);
        setLocationRelativeTo(null);
        setBackground(Color.WHITE);



        Button newRace = new Button("Новый забег");
        newRace.setBounds(10, 400, 80, 20);                 //кнопка нового забега
        add(newRace);


        DrawingCockroach drawingCockroach = new DrawingCockroach();
        drawingCockroach.setBackground(Color.WHITE);                                                                 //отрисовка таракана
        drawingCockroach.setPreferredSize(new Dimension(900, Main.getNumbOfTrack() * 180));


        leader = new TextFields("Лидер");
        leader.setBounds(600, 20, 300, 25);                              //текстовое поле для отображения имени лидирующего таракана


        box = Box.createVerticalBox();
        drawingCockroach.setLayout(new FlowLayout(FlowLayout.RIGHT));
        box.add(leader);
        box.add(Box.createRigidArea(new Dimension(180, 150)));                     //текстовые поля сбоку для имен тараканов
        for (int i = 0; i < Main.getNumbOfTrack(); i++) {
            TextFields textField = new TextFields(i + 1);
            box.add(textField);
            box.add(Box.createRigidArea(new Dimension(150, 127)));
            drawingCockroach.add(box);
        }


        JScrollPane jScrollPane = new JScrollPane(drawingCockroach);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);     //компонент, который, в случае большего количества дорожек, будет позволять
        add(jScrollPane);                                                                         // прокручивать картинку с бегами.
        jScrollPane.setVisible(true);


        setVisible(true);


        newRace.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {                            //обработка нажатия на кнопку "Новый забег"
                for (Cockroach cockroach : LocationData.setOfCoordinates) {
                    cockroach.setX(145);
                    cockroach.kill();
                    cockroach = null;
                }

                drawingCockroach.kill();
                LocationData.setOfCoordinates.clear();
                Races.plusCountOfTheRace();

                if (!Races.getIsFinished())
                    System.out.println("Забег прерван");

                leader.setText("Лидер");
                drawingCockroach.startAgain();
            }
        });
    }


    //для изменения имени лидирующего таракана на текстовом поле
    public static void setLeader(String str) {
        leader.setText("Лидер - таракан " + str);
    }
}

