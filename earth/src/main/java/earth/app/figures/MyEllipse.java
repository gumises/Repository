package earth.app.figures;

import java.awt.geom.Ellipse2D;

import earth.app.interfaces.GettingAppearance;
import earth.app.interfaces.SettingPosition;

import java.awt.Color;

/*Klasa bazowa Circle.*/
public abstract class MyEllipse extends Ellipse2D.Double implements GettingAppearance, SettingPosition {

    private static final long serialVersionUID = 1L;
    
    public Color color;
    public int thickness;
    
    public MyEllipse() {
        super();
    }

}
