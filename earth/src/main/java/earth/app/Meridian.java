package earth.app;

import java.awt.Color;
import java.awt.geom.Arc2D;

import earth.app.figures.MyArc;

/*Klasa Meridian.*/
public class Meridian extends MyArc {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /* Konstruktor. */
    public Meridian(Color color, int thickness, double x, double y, double r, double shift, double gamma) {
        this.color = color;
        this.thickness = thickness;
        this.shift = shift;

        setArcType(Arc2D.OPEN);
        setPosition(x, y, r, gamma);
        setExtent(gamma);
    }

    /* Zwraca kolor. */
    public Color getColor(double newGamma) {

        double newAngle = getInterval(newGamma + shift);

        if ((newAngle) % (2 * Math.PI) <= Math.PI)
            return color.darker();
        else
            return color;
    }

    /* Zwraca grubosc. */
    public int getThickness(double newGamma) {
        return thickness;
    }

    /* Ustawia kolor. */
    public void setColor(Color newColor) {
        color = newColor;
    }

    /* Ustawia grubosc. */
    public void setThickness(int newThickness) {
        thickness = newThickness;
    }

    /* UStawia pozycje i rozmiar. */
    public void setPosition(double newX, double newY, double newR, double newGamma) {
        x = newX - newR * Math.abs(Math.cos(newGamma + shift));
        y = newY - newR;
        width = 2 * newR * Math.abs(Math.cos(newGamma + shift));
        height = 2 * newR;
        setExtent(newGamma);
    }

    /* Ustawia przesuniecie, zmienia pozycje wgz. przesuniecia. */
    public void setShift(double newShift, double newR, double newGamma) {
        shift = newShift;
        setPosition(x, y, newR, newGamma);
    }

    /* Ustawia poczatek i koniec luku. */
    public void setExtent(double newGamma) {

        double newAngle = getInterval(newGamma + shift);

        if ((newAngle) % (2 * Math.PI) <= 0.5 * Math.PI || (newAngle) % (2 * Math.PI) >= 1.5 * Math.PI) {
            start = 90;
            extent = 180;
        } else {
            start = 270;
            extent = 180;
        }
    }

    /* Zwraca kat w przedziale (0, 2PI). */
    public double getInterval(double angle) {
        double newAngle = angle % (2 * Math.PI);
        return newAngle >= 0 ? newAngle : newAngle + 2 * Math.PI;
    }

}
