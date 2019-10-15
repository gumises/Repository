package earth.app;

//keyboard
import java.awt.event.KeyEvent;

import earth.app.screens.Screen;

import java.awt.event.KeyAdapter;

/*Obsluga klawiatury.*/
public class MyKeyAdapter extends KeyAdapter {

    Screen parent;
    double betaVelocity;
    double gammaVelocity;
    double newBeta;
    double newGamma;

    // konstruktor
    public MyKeyAdapter(Screen parent, double betaVelocity, double gammaVelocity) {
        this.parent = parent;
        this.betaVelocity = betaVelocity;
        this.gammaVelocity = gammaVelocity;
    }

    @Override
    public void keyPressed(KeyEvent event) {

        switch (event.getKeyCode()) {

            case KeyEvent.VK_UP:
                newBeta = parent.getBeta() + betaVelocity;
                parent.setBeta(getInterval(newBeta));
                break;
            case KeyEvent.VK_DOWN:
                newBeta = parent.getBeta() - betaVelocity;
                parent.setBeta(getInterval(newBeta));
                break;
            case KeyEvent.VK_LEFT:
                newGamma = parent.getGamma() - gammaVelocity;
                parent.setGamma(getInterval(newGamma));
                break;
            case KeyEvent.VK_RIGHT:
                newGamma = parent.getGamma() + gammaVelocity;
                parent.setGamma(getInterval(newGamma));
                break;
            default:
            
        }
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
