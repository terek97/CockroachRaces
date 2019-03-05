import java.awt.*;
import java.util.Collections;
import java.util.Objects;
import java.util.Random;


public class Cockroach implements Runnable {               //каждый таракан - поток


    private volatile boolean isRunning = true;
    private boolean isRushingFinished = false;
    public boolean isLeader;

    private Rectangle r;    //координаты для обработки нажатия на изображение таракана



    Random random = new Random();
    private Thread thread;



    private int y;
    private int x = 145;                //начальная координата Х
    private int number;                 // порядковый номер
    private String name;                //и имя таракана




    public Cockroach(String name, int number, int y) {

        this.y = y + 150;
        this.number = number;
        this.name = name;

        r = new Rectangle(this.x, this.y, 115, 75);              // координаты картинки


        thread = new Thread(this, "tarakan");                     //создаем и запускаем поток
        thread.start();
    }


    @Override
    public void run() {

        while (isRunning && this.x < 680) {                //до тех пор, пока таракан не дошел до финиша
            try {
                Thread.sleep(random.nextInt(7000));              //"засыпает" на рандомное время
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(this.x > 665) {
                this.x += 700 - this.x;
            }
            this.x += 20;                                               //имитация движения таракана
            r = new Rectangle(this.x, this.y ,115, 75 );
            LocationData.setOfCoordinates.add(this);                     //сохранение таракана для дальнейшей его отрисовки


        }
        if (isRunning && !isRushingFinished) {
            Races.resultOfRace(this);                         //таракан передается в другой класс для дальнейшего вывода результатов в консоль
            this.isLeader = false;
        }


        }

    public void isLeader() {
        int max = 145;
        for (Cockroach cockroach : LocationData.setOfCoordinates) {
            if (cockroach.getEx () > max){                                           //проверка на лидерство таракана
                max = cockroach.getEx();
                this.isLeader = true;
                MyWindow.setLeader(cockroach.getName());
                this.isLeader = false;

            }
        }
    }


    public void rush () {
        if(this.x < 660) {
            this.x += 20;
            LocationData.setOfCoordinates.add(this);         //"подгоняем" тараканов
        }
        else if (this.x >679 && this.x < 700) {
            this.x += 700 - this.x;
            isRushingFinished = true;
        }
    }



    public void kill() {
        isRunning = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cockroach)) return false;
        Cockroach cockroach = (Cockroach) o;
        return getNumber() == cockroach.getNumber() &&
                getName().equals(cockroach.getName());
    }
                                                               //для сравнения при добавлении тараканов в множество
    @Override
    public int hashCode() {
        return Objects.hash(getNumber(), getName());
    }


    //геттеры и сеттеры


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEx() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getYg() {
        return y;
    }

    public int getNumber() {
        return number;
    }
    public Rectangle getR() {
        return r;
    }
}
