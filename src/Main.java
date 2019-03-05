import javax.swing.*;

public class Main {

    public static JFrame frame;

    public static void main(String args[]){
        final int NUMTRACK;
        try {
            NUMTRACK = Integer.parseInt(args[0]);
            setNumbOfTrack(NUMTRACK);
        }
        catch (Exception e){
            System.out.println(e);
        }

         frame = new MyWindow();
        frame.setVisible(true);

    }

    private static int numbOfTrack = 0;

    public static void setNumbOfTrack(int i) {
        Main.numbOfTrack = i;
    }

    public static int getNumbOfTrack() {
        return numbOfTrack;
    }

}
