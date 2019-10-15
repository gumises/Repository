package earth.app;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

/*Klasa DataLabel.*/
class DataLabel extends JLabel {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /* Konstruktor. */
    public DataLabel(String text, Font font) {
        super(text);
        setFont(font);
        setForeground(Color.WHITE);
        setBackground(Color.DARK_GRAY);
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);
    }
}
