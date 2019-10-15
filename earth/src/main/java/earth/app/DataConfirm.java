package earth.app;

import java.awt.Color;
import earth.app.interfaces.*;
import javax.swing.JButton;
import javax.swing.SwingConstants;


import java.awt.Font;
import java.awt.Dimension;

//events
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/*Klasa DataConfirm.*/
class DataConfirm extends JButton implements SettingExceptions {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    CoordinatesPanel parent;

    /* Konstruktor. */
    public DataConfirm(CoordinatesPanel parent, Font font) {
        super();
        this.parent = parent;
        setFont(font);
        setForeground(Color.WHITE);
        setPreferredSize(new Dimension(100, 50));
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);
        setExceptionFalse();

        addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                parent.setPanelCoordinates(parent.getPanelCoordinates());
            }
        });
    }

    /* Wlacza opcje wyjatku w textField. */
    public void setExceptionTrue() {
        setText("INCORRECT");
        setBackground(Color.RED);
        setEnabled(false);
    }

    /* Wylacza opcje wyjatku w textField. */
    public void setExceptionFalse() {
        setText("CONFIRM");
        setBackground(Color.GREEN.darker());
        setEnabled(true);
    }
}
