package earth.app;

//mouse
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

//wheel
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import earth.app.screens.Screen;

/*Obsluga myszy.*/
public class MyMouseAdapter extends MouseAdapter implements MouseWheelListener {

    Screen parent;
    double rVelocity;

    double xTemp;
    double yTemp;

    double xParent;
    double yParent;
    double rParent;
    double alphaParent;

    boolean isRotation;
    int thickness;

    // konstruktor
    public MyMouseAdapter(Screen parent, double rVelocity, int thickness) {
        this.parent = parent;
        this.rVelocity = rVelocity;
        this.thickness = thickness;
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent event) {
        double newR = parent.getR() - event.getWheelRotation() * rVelocity;
        parent.setR(newR);
    }

    @Override
    public void mousePressed(MouseEvent event) {
        xTemp = event.getX();
        yTemp = event.getY();

        xParent = parent.getXCoord();
        yParent = parent.getYCoord();
        rParent = parent.getR();
        alphaParent = parent.getAlpha();

        isRotation = getDistance(xTemp, yTemp, xParent, yParent) <= rParent + thickness / 2 ? false : true;
    }

    @Override
    public void mouseDragged(MouseEvent event) {
        if (isRotation)
            parent.setAlpha(alphaParent + getAngle(xTemp, yTemp, event.getX(), event.getY(), xParent, yParent));
        else
            parent.setPosition(xParent + event.getX() - xTemp, yParent + event.getY() - yTemp);
    }

    /* Zwraca odleglosc pomiedzy A(x1,y1) i B(x2, y2). */
    public double getDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    /* Zwraca wartosc kata pomiedzy A(x1, x2), B(x2, y2) i O(x, y). */
    public double getAngle(double x1, double y1, double x2, double y2, double x, double y) {
        double alpha = getArcTan(x1 - x, y1 - y);
        double beta = getArcTan(x2 - x, y2 - y);
        return getInterval(beta - alpha);
    }

    /* Zwraca wartosc kata skierowanego w wektorze u = [dx, dy]. */
    public double getArcTan(double dx, double dy) {
        if (dx == 0)
            if (dy == 0)
                return 0;
            else if (dy > 0)
                return 0.5 * Math.PI;
            else
                return 1.5 * Math.PI;
        else if (dx > 0)
            return Math.atan(dy / dx) + 0.5 * Math.PI;
        else
            return Math.atan(dy / dx) + 1.5 * Math.PI;
    }

    /* Zwraca kat w odpowiednim przedziale. */
    public double getInterval(double value) {

        double valueTemp = value % (2 * Math.PI);

        if (valueTemp >= 0)
            return valueTemp;
        else
            return valueTemp + 2 * Math.PI;
    }
}
