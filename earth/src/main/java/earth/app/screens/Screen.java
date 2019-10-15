package earth.app.screens;

import earth.app.*;
import earth.app.interfaces.GettingCoordinates;
import earth.app.interfaces.SettingCoordinates;
import earth.app.interfaces.SettingIsColor;

import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.RenderingHints;
import java.awt.BasicStroke;

/*Panel rysunku.*/
public class Screen extends JPanel implements SettingCoordinates, GettingCoordinates, SettingIsColor {

    // parent
    Earth parent;

    // constants
    public static final int SCREENWIDTH = 800;
    public static final int SCREENHEIGHT = 700;
    public static final int THICKNESS = 5;

    // variables
    double xCoord;
    double yCoord;
    double r;

    double alpha;
    double beta;
    double gamma;

    int amountParallels;
    int amountMeridians;

    double rVelocity;
    double alphaVelocity;
    double betaVelocity;
    double gammaVelocity;

    boolean isColor;
    Color colorParallel;
    Color colorMeridian;

    // objects
    Circle circle;
    Parallel[] parallels;
    Meridian[] meridians;

    // adapters
    MyKeyAdapter keyAdapter;
    MyMouseAdapter mouseAdapter;

    /* Konstruktor. */
    public Screen(Earth parent) {

        // parent
        this.parent = parent;

        // variables
        xCoord = SCREENWIDTH / 2;
        yCoord = SCREENHEIGHT / 2;
        r = 300;

        alpha = 0;
        beta = 0.18;
        gamma = 0.18;

        amountParallels = -1;
        amountMeridians = -1;

        rVelocity = 5;
        alphaVelocity = 0.02;
        betaVelocity = 0.02;
        gammaVelocity = 0.02;

        isColor = true;
        colorParallel = Color.GREEN.brighter().brighter();
        colorMeridian = Color.BLUE.brighter();

        // objects
        circle = new Circle(Color.BLACK, THICKNESS, xCoord, yCoord, r);
        setParallels(11);
        setMeridians(11);

        // adapters
        keyAdapter = new MyKeyAdapter(this, betaVelocity, gammaVelocity);
        mouseAdapter = new MyMouseAdapter(this, rVelocity, THICKNESS);

        // adding listeners
        addKeyListener(keyAdapter);
        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
        addMouseWheelListener(mouseAdapter);

        setPreferredSize(new Dimension(SCREENWIDTH, SCREENHEIGHT));
        setVisible(true);
        setFocusable(true);
        setFocus();
    }

    /* Drukuje odswierzona grafike na ekranie. */
    public void paint(Graphics g) {

        g.clearRect(0, 0, SCREENWIDTH, SCREENHEIGHT);
        Graphics2D g2D = (Graphics2D) g.create();

        g2D.setColor(Color.BLACK);
        // g2D.setRenderingHint(
        // RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // g2D.setRenderingHint(
        // RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

        // circle
        if (isColor)
            g2D.setColor(circle.getColor(alpha));
        g2D.setStroke(new BasicStroke(circle.getThickness(alpha)));
        g2D.draw(circle);

        g2D.rotate(alpha, xCoord, yCoord);

        // meridians
        for (int i = 0; i < amountMeridians; i++) {
            if (isColor)
                g2D.setColor(meridians[i].getColor(gamma));
            g2D.setStroke(new BasicStroke(meridians[i].getThickness(gamma)));
            g2D.draw(meridians[i]);
        }

        // parallels
        for (int i = 0; i < amountParallels; i++) {
            if (isColor)
                g2D.setColor(parallels[i].getColor(beta));
            g2D.setStroke(new BasicStroke(parallels[i].getThickness(beta)));
            g2D.draw(parallels[i]);
        }
    }

    /* Zwraca wspolrzdna x. */
    public double getXCoord() {
        return xCoord;
    }

    /* Zwraca wspolrzedna y. */
    public double getYCoord() {
        return yCoord;
    }

    /* Zwraca dlugosc promienia. */
    public double getR() {
        return r;
    }

    /* Zwraca wartosc kata alpha. */
    public double getAlpha() {
        return alpha;
    }

    /* Zwraca wartosc kata beta. */
    public double getBeta() {
        return beta;
    }

    /* Zwraca wartosc kata gamma. */
    public double getGamma() {
        return gamma;
    }

    /* Zmienia wspolrzedne x, y, zmienia circle, parallels, meridians. */
    public void setPosition(double newX, double newY) {

        this.xCoord = newX;
        this.yCoord = newY;

        // circle
        circle.setPosition(xCoord, yCoord, r, alpha);

        // parallels
        for (int i = 0; i < amountParallels; i++)
            parallels[i].setPosition(xCoord, yCoord, r, beta);

        // meridians
        for (int i = 0; i < amountMeridians; i++)
            meridians[i].setPosition(xCoord, yCoord, r, gamma);

        setScreenCoordinates(getScreenCoordinates());
        repaint();
    }

    /* Zmienia dlugosc promienia, zmienia circle, parallels, meridians. */
    public void setR(double newR) {

        r = newR >= 0 ? newR : 0;

        // circle
        circle.setPosition(xCoord, yCoord, r, alpha);

        // parallels
        for (int i = 0; i < amountParallels; i++)
            parallels[i].setPosition(xCoord, yCoord, r, beta);

        // meridians
        for (int i = 0; i < amountMeridians; i++)
            meridians[i].setPosition(xCoord, yCoord, r, gamma);

        setScreenCoordinates(getScreenCoordinates());
        repaint();
    }

    /* Zmienia wartosc kata alpha, obraca parallels, meridians. */
    public void setAlpha(double newAlpha) {
        this.alpha = newAlpha;
        setScreenCoordinates(getScreenCoordinates());
        repaint();
    }

    /* Zmienia wartosc kata beta, obraca parallels. */
    public void setBeta(double newBeta) {

        this.beta = newBeta;

        for (int i = 0; i < amountParallels; i++)
            parallels[i].setPosition(xCoord, yCoord, r, beta);

        setScreenCoordinates(getScreenCoordinates());
        repaint();
    }

    /* Zmienia wartosc kata gamma, obraca meridians. */
    public void setGamma(double newGamma) {

        this.gamma = newGamma;

        for (int i = 0; i < amountMeridians; i++)
            meridians[i].setPosition(xCoord, yCoord, r, gamma);

        setScreenCoordinates(getScreenCoordinates());
        repaint();
    }

    /* Ustawia rownolezniki. */
    public void setParallels(int newAmountParallels) {

        if (newAmountParallels == 0)
            amountParallels = 0;
        else if (amountParallels != newAmountParallels) {

            amountParallels = newAmountParallels;
            parallels = new Parallel[amountParallels];

            for (int i = 0; i < amountParallels; i++) {
                parallels[i] = new Parallel(colorParallel, THICKNESS, xCoord, yCoord, r,
                        (2 * Math.PI * i) / amountParallels, beta);
            }
        }

        setScreenCoordinates(getScreenCoordinates());
        repaint();
    }

    /* Ustawia poludniki. */
    public void setMeridians(int newAmountMeridians) {

        if (newAmountMeridians == 0)
            amountMeridians = 0;
        else if (amountMeridians != newAmountMeridians) {

            amountMeridians = newAmountMeridians;
            meridians = new Meridian[amountMeridians];

            for (int i = 0; i < amountMeridians; i++) {
                meridians[i] = new Meridian(colorMeridian, THICKNESS, xCoord, yCoord, r,
                        (2 * Math.PI * i) / amountMeridians, beta);
            }
        }

        setScreenCoordinates(getScreenCoordinates());
        repaint();
    }

    /* Zwraca parametry od Screen. */
    public double[] getScreenCoordinates() {
        double coordinates[] = { xCoord, yCoord, r, alpha, beta, gamma, amountParallels, amountMeridians, };
        return coordinates;
    }

    /* Zwraca parametry od CoordinatesPanel. */
    public double[] getPanelCoordinates() {
        return parent.getPanelCoordinates();
    }

    /* Ustawia parametry od Screen. */
    public void setScreenCoordinates(double[] coordinates) {
        parent.setScreenCoordinates(coordinates);
    }

    /* Ustawia parametry od CoordinatesPanel. */
    public void setPanelCoordinates(double[] coordinates) {

        setPosition(coordinates[0], coordinates[1]);
        setR(coordinates[2]);
        setAlpha(coordinates[3]);
        setBeta(coordinates[4]);
        setGamma(coordinates[5]);
        setParallels((int) coordinates[6]);
        setMeridians((int) coordinates[7]);
    }

    /* Aktywuje kompoenent. */
    public void setFocus() {
        requestFocus();
    }

    /* Ustawia opcje koloru. */
    public void setIsColor(boolean isColor) {
        this.isColor = isColor;
        setFocus();
        repaint();
    }
}
