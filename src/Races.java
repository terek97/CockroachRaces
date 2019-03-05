import java.util.ArrayList;

//класс для подсчета результатов гонки и выводе их в консоль

public abstract class Races {

    private static int countOfCockr = 0;    // счетчик тараканов, пришедших к финишу в конкретном забеге
    private static int countOfTheRace = 1;  // счетчик забегов
    private static boolean isFinished = false;

    private static ArrayList<Cockroach> cockroaches = new ArrayList<>();   //список тараканов, пришедших к финишу в конкретном забеге.



    //Метод вызывается каждый раз, когда поток таракана "умирает" (таракан приходит к финишу)
    public static void resultOfRace(Cockroach cockroach) {

        countOfCockr++;
        cockroaches.add(cockroach);       //увеличиваем счетчик, добавляем таракана в список.

        if (countOfCockr == Main.getNumbOfTrack()) {
            isFinished = true;
            System.out.println("Забег № " + countOfTheRace + "!");
            for(int i = 0; i <cockroaches.size(); i++){                                                         //если гонка закончена, в консоль выводится номер забега и результаты гонки
                System.out.println(i + 1 + " место занимает таракан " + cockroaches.get(i).getName());
            }
            MyWindow.setLeader("Лидер");
            countOfCockr = 0;
            cockroaches.clear();                   //сбрасывается счетчик и очищается список тараканов
        }

    }


    public static void plusCountOfTheRace() {
        countOfTheRace++;
        countOfCockr = 0;
    }

    public static boolean getIsFinished() {
        return isFinished;
    }

    public static void setIsFinished(boolean isFinished) {
        Races.isFinished = isFinished;
    }
}
