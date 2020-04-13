package ui.components.textfield;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class JNumberTextField extends JTextField {
    private static final long serialVersionUID = 1L;

    /***
     * Override processKeyEvent
     *  -> only allow numbers backspace and delete key
     */
    @Override
    public void processKeyEvent(KeyEvent evt) {
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
            evt.consume();
        }
        super.processKeyEvent(evt);
    }

    /**
     * As the user is not even able to enter a dot ("."), only integers (whole numbers) may be entered.
     */
    public int getNumber() {
        int result = 0;
        String text = getText();
        if (text != null && !text.isEmpty()) {
            result = Integer.parseInt(text);
        }
        return result;
    }
}
