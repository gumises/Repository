package earth.app;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Font;

//components
import javax.swing.JPanel;

//layouts
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

/*Klasa CoordinatesPanel.*/
class CoordinatesPanel extends JPanel {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;
    Earth parent;
    int amountExceptions;

    // tables
    double coordinates[];
    String texts[];
    ImageIcon icons[];
    ImageIcon iconsColor[];

    // constants
    public static final int ICONNWIDTH = 65;
    public static final int ICONHEIGHT = 65;
    public static final String PATHS[] = { "x", "y", "r", "alpha", "beta", "gamma", "parallels", "meridians",
        "colorTrue", "colorFalse" };
    public static final String COLORPATHS[] = { "colorTrue", "colorFalse" };

    // fonts
    // DPL = DataPanel.Label
    // DPF = DataPanel.Field
    // DL = DataLabel
    // DC = DataConfirm
    public static final Font fontDPL = new Font("Courier New", Font.PLAIN, 14);
    public static final Font fontDPF = new Font("Courier New", Font.BOLD, 14);
    public static final Font fontDL = new Font("Courier New", Font.BOLD, 16);
    public static final Font fontDC = new Font("Courier New", Font.BOLD, 14);

    // DataLabels
    DataLabel positionLabel;
    DataLabel rotationLabel;
    DataLabel displayLabel;

    // DataPanels
    DataPanel xPanel;
    DataPanel yPanel;
    DataPanel rPanel;
    DataPanel alphaPanel;
    DataPanel betaPanel;
    DataPanel gammaPanel;
    DataPanel parallelsPanel;
    DataPanel meridiansPanel;

    // DataColor
    DataColor dataColor;

    // DataConfirm
    DataConfirm dataConfirm;

    /* Konstruktor. */
    public CoordinatesPanel(Earth parent) {

        this.parent = parent;

        // tables
        texts = getTexts();
        icons = getIcons();
        iconsColor = getIconsColor();

        // DataConfirm
        dataConfirm = new DataConfirm(this, fontDC);

        // DataColor
        dataColor = new DataColor(this, iconsColor[0], iconsColor[1], "color =", fontDPL);

        // DataLabels
        positionLabel = new DataLabel("position", fontDL);
        rotationLabel = new DataLabel("rotation", fontDL);
        displayLabel = new DataLabel("display", fontDL);

        // DataPanels
        xPanel = new DataPanel(this, icons[0], texts[0], fontDPL, fontDPF);
        yPanel = new DataPanel(this, icons[1], texts[1], fontDPL, fontDPF);
        rPanel = new DataPanel(this, icons[2], texts[2], fontDPL, fontDPF);
        alphaPanel = new DataPanel(this, icons[3], texts[3], fontDPL, fontDPF);
        betaPanel = new DataPanel(this, icons[4], texts[4], fontDPL, fontDPF);
        gammaPanel = new DataPanel(this, icons[5], texts[5], fontDPL, fontDPF);
        parallelsPanel = new DataPanel(this, icons[6], texts[6], fontDPL, fontDPF);
        meridiansPanel = new DataPanel(this, icons[7], texts[7], fontDPL, fontDPF);

        // setScreenCoordinates(getScreenCoordinates());
        amountExceptions = 0;

        // gridBagLayout
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        setLayout(layout);

        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;

        // positionLabel
        gbc.gridy = 0;
        gbc.gridx = 0;
        add(positionLabel, gbc);

        // xPanel
        gbc.gridy = 1;
        gbc.gridx = 0;
        add(xPanel, gbc);

        // xPanel
        gbc.gridy = 2;
        gbc.gridx = 0;
        add(yPanel, gbc);

        // rPanel
        gbc.gridy = 3;
        gbc.gridx = 0;
        add(rPanel, gbc);

        // rotationLabel
        gbc.gridy = 0;
        gbc.gridx = 1;
        add(rotationLabel, gbc);

        // alphaPanel
        gbc.gridy = 1;
        gbc.gridx = 1;
        add(alphaPanel, gbc);

        // betaPanel
        gbc.gridy = 2;
        gbc.gridx = 1;
        add(betaPanel, gbc);

        // gammaPanel
        gbc.gridy = 3;
        gbc.gridx = 1;
        add(gammaPanel, gbc);

        // displayLabel
        gbc.gridy = 0;
        gbc.gridx = 2;
        add(displayLabel, gbc);

        // parallelsPanel
        gbc.gridy = 1;
        gbc.gridx = 2;
        add(parallelsPanel, gbc);

        // meridiansPanel
        gbc.gridy = 2;
        gbc.gridx = 2;
        add(meridiansPanel, gbc);

        // dataColor
        gbc.gridy = 3;
        gbc.gridx = 2;
        add(dataColor, gbc);

        // dataConfirm
        gbc.gridy = 0;
        gbc.gridx = 3;
        gbc.gridwidth = 1;
        gbc.gridheight = 4;
        add(dataConfirm, gbc);

        setForeground(Color.WHITE);
        setBackground(Color.DARK_GRAY);
        setPreferredSize(new Dimension(800, 300));
        setVisible(true);

    }

    /* Zwraca napisy. */
    public String[] getTexts() {

        String textsTemp[] = new String[PATHS.length];
        for (int i = 0; i < PATHS.length; i++)
            textsTemp[i] = PATHS[i] + " =";

        return textsTemp;
    }

    /* Zwraca ikony akcji o rozmiarach (ICONWIDTH, ICONHEIGHT). */
    public ImageIcon[] getIcons() {

        ImageIcon iconsTemp[] = new ImageIcon[PATHS.length];
        for (int i = 0; i < PATHS.length; i++)
            iconsTemp[i] = new ImageIcon(new ImageIcon("earth/icons/" + PATHS[i] + ".png").getImage()
                    .getScaledInstance(ICONNWIDTH, ICONHEIGHT, Image.SCALE_DEFAULT));

        return iconsTemp;
    }

    /* Zwraca ikony koloru o rozmiarach (ICONWIDTH, ICONHEIGHT). */
    public ImageIcon[] getIconsColor() {

        ImageIcon iconsColorTemp[] = new ImageIcon[COLORPATHS.length];
        for (int i = 0; i < COLORPATHS.length; i++)
            iconsColorTemp[i] = new ImageIcon(new ImageIcon("earth/icons/" + COLORPATHS[i] + ".png").getImage()
                    .getScaledInstance(ICONNWIDTH, ICONHEIGHT, Image.SCALE_DEFAULT));

        return iconsColorTemp;
    }

    /* Zwraca parametry od Screen. */
    public double[] getScreenCoordinates() {
        return parent.getScreenCoordinates();
    }

    /* Zwraca parametry od CoordinatesPanel. */
    public double[] getPanelCoordinates() {

        amountExceptions = 0;
        double coordinates[] = new double[PATHS.length];

        // xPanel
        try {
            coordinates[0] = getPositionNoumber(xPanel.getText());
        } catch (Exception exception) {
            xPanel.setExceptionTrue();
            addException(1);
        }

        // yPanel
        try {
            coordinates[1] = getPositionNoumber(yPanel.getText());
        } catch (Exception exception) {
            yPanel.setExceptionTrue();
            addException(1);
        }

        // rPanel
        try {
            coordinates[2] = getPositionNoumber(rPanel.getText());
        } catch (Exception exception) {
            rPanel.setExceptionTrue();
            addException(1);
        }

        // alphaPanel
        try {
            coordinates[3] = getAngleNoumber(alphaPanel.getText());
        } catch (Exception exception) {
            alphaPanel.setExceptionTrue();
            addException(1);
        }

        // betaPanel
        try {
            coordinates[4] = getAngleNoumber(betaPanel.getText());
        } catch (Exception exception) {
            betaPanel.setExceptionTrue();
            addException(1);
        }

        // gammaPanel
        try {
            coordinates[5] = getAngleNoumber(gammaPanel.getText());
        } catch (Exception exception) {
            gammaPanel.setExceptionTrue();
            addException(1);
        }

        // parallelsPanel
        try {
            coordinates[6] = getPositionNoumber(parallelsPanel.getText());
        } catch (Exception exception) {
            parallelsPanel.setExceptionTrue();
            addException(1);
        }

        // meridiansPanel
        try {
            coordinates[7] = getPositionNoumber(meridiansPanel.getText());
        } catch (Exception exception) {
            meridiansPanel.setExceptionTrue();
            addException(1);
        }

        return coordinates;
    }

    /* Dodaje liczbe wyjatkow. */
    public void addException(int value) {
        amountExceptions += value;
        if (amountExceptions == 0)
            dataConfirm.setExceptionFalse();
        else
            dataConfirm.setExceptionTrue();
    }

    /* Ustawia parametry od Screen. */
    public void setScreenCoordinates(double[] coordinates) {
        xPanel.setText(getPositionText(coordinates[0]));
        yPanel.setText(getPositionText(coordinates[1]));
        ;
        rPanel.setText(getPositionText(coordinates[2]));
        alphaPanel.setText(getAngleText(coordinates[3]));
        betaPanel.setText(getAngleText(coordinates[4]));
        gammaPanel.setText(getAngleText(coordinates[5]));
        parallelsPanel.setText(getPositionText(coordinates[6]));
        meridiansPanel.setText(getPositionText(coordinates[7]));
    }

    /* Ustawia parametry od CoordinatesPanel. */
    public void setPanelCoordinates(double[] coordinates) {
        if (amountExceptions == 0)
            parent.setPanelCoordinates(coordinates);
        else
            dataConfirm.setExceptionTrue();
        setFocus();
    }

    /* Aktywuje screen. */
    public void setFocus() {
        parent.setFocus();
    }

    /* Ustawia opcje koloru. */
    public void setIsColor(boolean isColor) {
        parent.setIsColor(isColor);
    }

    /* Zamienia double na String. */
    public String getPositionText(double value) {
        return Integer.toString((int) value);
    }

    /*
     * Zamienia double na String, zamienia stopnie na radiany, dodaje symbol kata.
     */
    public String getAngleText(double value) {
        return Integer.toString((int) Math.toDegrees(value)) + "°";
    }

    /* Zamienia String na double. */
    public double getPositionNoumber(String value) throws Exception {

        // pusty napis
        if (value == "")
            throw new Exception();

        return Double.parseDouble(value);
    }

    /* Zamienia String na double. */
    public double getAngleNoumber(String value) throws Exception {

        String valueTemp = value;
        if (valueTemp.charAt(valueTemp.length() - 1) == '°')
            valueTemp = valueTemp.substring(0, valueTemp.length() - 1);

        // pusty napis
        if (valueTemp == "")
            throw new Exception();

        return Math.toRadians(Double.parseDouble(valueTemp));
    }
}
