import javax.swing.*;
import java.awt.event.*;

//
//текстовые поля с именами тараканов и слушатель клавиатуры
//
public class TextFields extends JTextField {

    private String str = "";
    private int number;


    public TextFields(String text) {
        this.setText(text);
    }


    public TextFields(int number) {
        this.number = number;
        this.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyPressed(KeyEvent e) {
            }


            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() != KeyEvent.VK_BACK_SPACE) {
                    str = str + e.getKeyChar();
                }                                                                    //действия при введении имени в текстовое поле
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    for(Cockroach cockroach : LocationData.setOfCoordinates){
                        if(cockroach.getNumber() == number){
                            cockroach.setName(str);
                            str = "";
                        }
                    }
                }
            }
        });
    }
}



