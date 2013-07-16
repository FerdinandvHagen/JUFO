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

    public Kreis(int x, int y, int r) {
        this.x = x;
        this.y = y;
        this.r = r;
        setzeFarbe("schwarz");
    }

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

    public void bewegen(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double getXd() {
        return this.x;
    }

    public int getX() {
        return (int) this.x;
    }

    public double getYd() {
        return this.y;
    }

    public int getY() {
        return (int) this.y;
    }

    public double getRd() {
        return this.r;
    }

    public int getR() {
        return (int) this.r;
    }
}
