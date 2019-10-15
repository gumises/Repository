package earth.app.figures;

import java.awt.Color;
import earth.app.interfaces.GettingAppearance;
import earth.app.interfaces.SettingAngle;
import earth.app.interfaces.SettingAppearance;
import earth.app.interfaces.SettingPosition;

import java.awt.geom.Arc2D;

/*Klasa bazowa Parallel, Meridian.*/
public abstract class MyArc extends Arc2D.Double
        implements GettingAppearance, SettingAppearance, SettingPosition, SettingAngle {

    public Color color;
    public int thickness;
    public double shift;
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    public MyArc() {
        super();
    }


}
