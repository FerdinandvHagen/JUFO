/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.Leinwand;

import static org.lwjgl.opengl.GL11.*;
import org.newdawn.slick.opengl.Texture;

/**
 *
 * @author ADMIN
 */
public class Rechteck extends OBJECT_2D {

    private double w, h;
    private Texture textur;
    private int scalex, scaley;

    /**
     * Erstellt ein neues Rechteck
     *
     * @param x x-Koordinate des Rechtecks
     * @param y y-Koordinate des Rechtecks
     * @param w Breite des Rechtecks
     * @param h Höhe des Rechtecks
     */
    public Rechteck(int x, int y, int w, int h) {
        super();
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.scalex = 1;
        this.scaley = 1;
        setzeFarbe("schwarz");
    }

    /**
     * Erstellt ein neues Rechteck
     *
     * @param x x-Koordinate des Rechtecks
     * @param y y-Koordinate des Rechtecks
     * @param w Breite des Rechtecks
     * @param h Höhe des Rechtecks
     */
    public Rechteck(double x, double y, double w, double h) {
        super();
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.scalex = 1;
        this.scaley = 1;
        setzeFarbe("schwarz");
    }

    /**
     * Unterteilt das Rechteck in x mal y Einzelrechtecke (z.B. damit die Textur
     * feiner wird)
     *
     * @param x Anzahl der Elemente in x-Richtung
     * @param y Anzahl der ELemente in y-Richtung
     */
    public void aufskalieren(int x, int y) {
        this.scalex = x;
        this.scaley = y;
    }

    /**
     * Ermittelt die Breite des Rechtecks
     *
     * @return Breite des Rechtecks
     */
    public double w() {
        return this.w;
    }

    /**
     * Ermittelt die Höhe des Rechtecks
     *
     * @return Höhe des Rechtecks
     */
    public double h() {
        return this.h;
    }

    public void zeichnen(double factor) {
        double pointx, pointy;
        if (rotation != 0) {
            transx = x + (w / 2);
            transy = y + (h / 2);
            pointx = -w / 2;
            pointy = -h / 2;

            processTranslationsOne();
        } else {
            pointx = x;
            pointy = y;
        }

        //Hier wird das Objekt gezeichnet
        if (textur == null) {
            glColor4d(colorrd, colorgr, colorbl, alpha);
            glBegin(GL_QUADS);
            glVertex2d(pointx, pointy);
            glVertex2d(pointx + w, pointy);
            glVertex2d(pointx + w, pointy + h);
            glVertex2d(pointx, pointy + h);
            glEnd();
        } else {
            glColor4d(1f, 1f, 1f, 1f);
            glDisable(GL_TEXTURE_2D);
            glEnable(GL_TEXTURE_2D);
            textur.bind();
            glBegin(GL_TRIANGLES);

            double scw = this.w / this.scalex;
            double sch = this.h / this.scaley;

            for (int scx = this.scalex; scx > 0; scx--) {
                for (int scy = this.scaley; scy > 0; scy--) {
                    double scbx = pointx + ((scx - 1) * scw);
                    double scby = pointy + ((scy - 1) * sch);
                    glTexCoord2f(1, 0);
                    glVertex2d(scbx + scw, scby);
                    glTexCoord2f(0, 0);
                    glVertex2d(scbx, scby);
                    glTexCoord2f(0, 1);
                    glVertex2d(scbx, scby + sch);
                    glTexCoord2f(0, 1);
                    glVertex2d(scbx, scby + sch);
                    glTexCoord2f(1, 1);
                    glVertex2d(scbx + scw, scby + sch);
                    glTexCoord2f(1, 0);
                    glVertex2d(scbx + scw, scby);
                }
            }
            glEnd();
            glDisable(GL_TEXTURE_2D);
        }
        if (rotation != 0) {
            processTranslationsTwo();
        }
    }

    /**
     * Lädt die angegebene Textur auf das Rechteck
     *
     * @param textur Pfad zu der Textur
     */
    public void ladeTextur(String textur) {
        this.textur = Leinwand.gibLeinwand().loadTexture(textur);
    }

    //Boundingzeug
    public int bh() {
        return (int) this.h;
    }

    public int bw() {
        return (int) this.w;
    }

    /**
     * Setzt die Breite des Rechtecks
     *
     * @param w neue Breite des Rechtecks
     */
    public void setzeW(double w) {
        this.w = w;
    }

    /**
     * Setzt die Höhe des Rechtecks
     *
     * @param h neue Höhe des Rechtecks
     */
    public void setzeH(double h) {
        this.h = h;
    }
}
