package earth.app;

import javax.swing.JFrame;
import earth.app.screens.*;

//layouts
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

/** Okno programu. */
public class Earth extends JFrame {

    /** SerialVersionUID */
    private static final long serialVersionUID = 1L;
    
    // objects
    Screen screen;
    CoordinatesPanel coordinatesPanel;

    public Earth() {

        // objects
        coordinatesPanel = new CoordinatesPanel(this);
        screen = new Screen(this);
        setScreenCoordinates(getScreenCoordinates());

        // gridBagLayout
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        setLayout(layout);

        // screen
        gbc.gridy = 0;
        gbc.gridx = 0;
        add(screen, gbc);

        // coordinatesPanel
        gbc.gridy = 1;
        gbc.gridx = 0;
        add(coordinatesPanel, gbc);

        pack();
        validate();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setEnabled(true);
        setResizable(false);
    }

    /** Zwraca parametry od Screen. */
    public double[] getScreenCoordinates() {
        return screen.getScreenCoordinates();
    }

    /** Zwraca parametry od CoordinatesPanel. */
    public double[] getPanelCoordinates() {
        return coordinatesPanel.getPanelCoordinates();
    }

    /** Ustawia parametry od Screen. */
    public void setScreenCoordinates(double[] coordinates) {
        coordinatesPanel.setScreenCoordinates(coordinates);
    }

    /** Ustawia parametry od CoordinatesPanel. */
    public void setPanelCoordinates(double[] coordinates) {
        screen.setPanelCoordinates(coordinates);
    }

    /** Ustawia opcje koloru. */
    public void setIsColor(boolean isColor) {
        screen.setIsColor(isColor);
    }

    /** Aktywuje screen. */
    public void setFocus() {
        screen.setFocus();
    }

    public static void main(String[] args) {
        new Earth();
    }
}
