package earth.app;

import java.awt.Color;
import java.awt.geom.Arc2D;

import earth.app.figures.MyArc;

/*Klasa Parallel.*/
public class Parallel extends MyArc {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /* Konstruktor. */
    public Parallel(Color color, int thickness, double x, double y, double r, double shift, double beta) {
        this.color = color;
        this.thickness = thickness;
        this.shift = shift;

        setArcType(Arc2D.OPEN);
        setPosition(x, y, r, beta);
        setExtent(beta);
    }

    /* Zwraca kolor. */
    public Color getColor(double newBeta) {

        double newAngle = getInterval(newBeta + shift);

        if ((newAngle) % (2 * Math.PI) <= Math.PI)
            return color.darker();
        else
            return color;
    }

    /* Zwraca grubosc. */
    public int getThickness(double newBeta) {
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
    public void setPosition(double newX, double newY, double newR, double newBeta) {
        x = newX - newR;
        y = newY - newR * Math.abs(Math.cos(newBeta + shift));
        width = 2 * newR;
        height = 2 * newR * Math.abs(Math.cos(newBeta + shift));
        setExtent(newBeta);
    }

    /* Ustawia przesuniecie, zmienia pozycje wgz. przesuniecia. */
    public void setShift(double newShift, double newR, double newBeta) {
        shift = newShift;
        setPosition(x, y, newR, newBeta);
    }

    /* Ustawia poczatek i koniec luku. */
    public void setExtent(double newBeta) {

        double newAngle = getInterval(newBeta + shift);

        if ((newAngle) % (2 * Math.PI) <= 0.5 * Math.PI || (newAngle) % (2 * Math.PI) >= 1.5 * Math.PI) {
            start = 0;
            extent = 180;
        } else {
            start = 180;
            extent = 180;
        }
    }

    /* Zwraca kat w przedziale (0, 2PI). */
    public double getInterval(double angle) {
        double newAngle = angle % (2 * Math.PI);
        return newAngle >= 0 ? newAngle : newAngle + 2 * Math.PI;
    }

}
