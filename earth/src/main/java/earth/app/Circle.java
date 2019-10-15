package earth.app;

import java.awt.Color;

import earth.app.figures.*;

/*Klasa Circle.*/
public class Circle extends MyEllipse {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /** Konstruktor. */
    public Circle(Color color, int thickness, double x, double y, double r) {
        this.color = color;
        this.thickness = thickness;
        this.x = x - r;
        this.y = y - r;
        this.width = 2 * r;
        this.height = 2 * r;
    }

    /* Zwraca kolor. */
    @Override
    public Color getColor(double newAngle) {
        return color;
    }

    /* Zwraca grubosc. */
    public int getThickness(double newAngle) {
        return (int) thickness / 2;
    }

    /* Ustawia pozycje i rozmiar. */
    public void setPosition(double newX, double newY, double newR, double newAngle) {
        x = newX - newR;
        y = newY - newR;
        width = 2 * newR;
        height = 2 * newR;
    }
}
