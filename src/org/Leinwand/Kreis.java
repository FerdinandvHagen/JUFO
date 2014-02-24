/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.Leinwand;

import static org.lwjgl.opengl.GL11.*;

/**
 *
 * @author ADMIN
 */
public class Kreis extends OBJECT_2D {

    private double r;

    /**
     * Erstellt einen neuen Kreis
     *
     * @param x x-Koordinate des Kreis
     * @param y y-Koordinate des Kreis
     * @param r Radius des Kreises
     */
    public Kreis(int x, int y, int r) {
        this.x = x;
        this.y = y;
        this.r = r;
        setzeFarbe("schwarz");
    }

    /**
     * Erstellt einen neuen Kreis
     *
     * @param x x-Koordinate des Kreis
     * @param y y-Koordinate des Kreis
     * @param r Radius des Kreises
     */
    public Kreis(double x, double y, double r) {
        this.x = x;
        this.y = y;
        this.r = r;
        setzeFarbe("schwarz");
    }

    public void zeichnen(double factor) {
        double pointx, pointy;
        if (rotation != 0) {
            transx = x + (r / 2);
            transy = y + (r / 2);
            pointx = -r / 2;
            pointy = -r / 2;

            processTranslationsOne();
        } else {
            pointx = x;
            pointy = y;
        }

        //Hier wird das Objekt gezeichnet
        glColor4d(colorrd, colorgr, colorbl, alpha);
        float xs, ys;
        glBegin(GL_POLYGON); //Begin Polygon coordinates
        for (double theta = 0; theta < (2 * 3.1416); theta += (2 * 3.1416) / 360) {
            xs = (float) pointx + (float) (Math.cos(theta) * (float) r);
            ys = (float) pointy + (float) (Math.sin(theta) * (float) r);
            glVertex2f(xs, ys);
        }
        glEnd();

        if (rotation != 0) {
            processTranslationsTwo();
        }
    }

    /**
     * Ermittelt den Radius des Kreises
     *
     * @return Radius des Kreises
     */
    public double r() {
        return this.r;
    }

    public int br() {
        return (int) this.r;
    }

    /**
     * Setzt den Radius des Kreises
     * @param r neuer Radius
     */
    public void r(double r) {
        this.r = r;
    }

    public boolean schneidet(OBJECT_2D obj) {
        return PhysicEngine.checkviolation(obj, this);
    }
}
