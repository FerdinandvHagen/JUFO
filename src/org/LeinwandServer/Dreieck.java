/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.LeinwandServer;

//import org.TextureListServer.Texture;
import static org.lwjgl.opengl.GL11.*;

/**
 *
 * @author ADMIN
 */
public class Dreieck extends OBJECT_2D {

    private double w, h;
    private char direction;
    //private Texture textur;
    private int scalex;

    public Dreieck(double x, double y, double w, double h) {
        createDreieck(x, y, w, h, 'a');
    }

    private void createDreieck(double x, double y, double w, double h, char dir) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.direction = dir;
        setzeFarbe("schwarz");
        //textur = null;
        this.scalex = 1;
    }

    public Dreieck(double x, double y, double w, double h, char dir) {
        createDreieck(x, y, w, h, dir);
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

        /*if (textur != null) {
            glColor4d(1f, 1f, 1f, 1f);
            glDisable(GL_TEXTURE_2D);
            glEnable(GL_TEXTURE_2D);
            textur.bind();
            glBegin(GL_TRIANGLES);
        } else {*/
            //Hier wird das Objekt gezeichnet
            glColor4d(colorrd, colorgr, colorbl, alpha);
            glBegin(GL_TRIANGLES);
        //}
        
        double scw = this.w / this.scalex;
        double sch = this.h / this.scalex;

        int line, row;
        line = 0;
        row = 0;

        for (int scy = this.scalex; scy > 0; scy--) {
            for (int scx = this.scalex; scx > 0; scx--) {
                double scbx = pointx + ((scx - 1) * scw);
                double scby = pointy + ((scy - 1) * sch);

                if (this.direction == 'a') {
                    if (scx > scy) {
                        continue;
                    }
                    if (scx == scy) {
                        displaya(scbx, scby, scw, sch);
                    } else {
                        displayRechteck(scbx, scby, scw, sch);
                    }
                } else if (this.direction == 'b') {
                    if(scx < scalex - scy + 1) {
                        continue;
                    }
                    if (scx == scalex - scy + 1) {
                        displayb(scbx, scby, scw, sch);
                    } else {
                        displayRechteck(scbx, scby, scw, sch);
                    }
                } else if (this.direction == 'c') {
                    if(scx < scy)
                    {
                        continue;
                    }
                    if (scx == scy) {
                        displayc(scbx, scby, scw, sch);
                    } else {
                        displayRechteck(scbx, scby, scw, sch);
                    }
                } else if (this.direction == 'd') {
                    if(scx > scalex - scy + 1) {
                        continue;
                    }
                    if (scx == scalex - scy + 1) {
                        displayd(scbx, scby, scw, sch);
                    } else {
                        displayRechteck(scbx, scby, scw, sch);
                    }
                } else {
                    if (scx > scy) {
                        continue;
                    }
                    if (scx == scy) {
                        displaya(scbx, scby, scw, sch);
                    } else {
                        displayRechteck(scbx, scby, scw, sch);
                    }
                }
            }
        }
        glEnd();
        glDisable(GL_TEXTURE_2D);


        if (rotation != 0) {
            processTranslationsTwo();
        }
    }

    public void turn(char turn) {
        this.direction = turn;
        update();
    }

    public void bewegen(int x, int y) {
        this.x = x;
        this.y = y;
        update();
    }

    /*public void ladeTextur(String textur) {
        this.textur = Leinwand.gibLeinwand().loadTexture(textur);
        update();
    }*/

    public void scaleup(int scale) {
        this.scalex = scale;
        update();
    }

    private void displaya(double pointx, double pointy, double w, double h) {
        //if (textur == null) {
            glVertex2d(pointx, pointy);
            glVertex2d(pointx, pointy + h);
            glVertex2d(pointx + w, pointy + h);
        /*} else {
            glTexCoord2f(0, 0);
            glVertex2d(pointx, pointy);
            glTexCoord2f(0, 1);
            glVertex2d(pointx, pointy + h);
            glTexCoord2f(1, 1);
            glVertex2d(pointx + w, pointy + h);
        }*/
    }

    private void displayb(double pointx, double pointy, double w, double h) {
        //if (textur == null) {
            glVertex2d(pointx + w, pointy);
            glVertex2d(pointx + w, pointy + h);
            glVertex2d(pointx, pointy + h);
        /*} else {
            glTexCoord2f(1, 0);
            glVertex2d(pointx + w, pointy);
            glTexCoord2f(1, 1);
            glVertex2d(pointx + w, pointy + h);
            glTexCoord2f(0, 1);
            glVertex2d(pointx, pointy + h);
        }*/
    }

    private void displayc(double pointx, double pointy, double w, double h) {
        //if (textur == null) {
            glVertex2d(pointx, pointy);
            glVertex2d(pointx + w, pointy);
            glVertex2d(pointx + w, pointy + h);
        /*} else {
            glTexCoord2f(0, 0);
            glVertex2d(pointx, pointy);
            glTexCoord2f(1, 0);
            glVertex2d(pointx + w, pointy);
            glTexCoord2f(1, 1);
            glVertex2d(pointx + w, pointy + h);
        }*/
    }

    private void displayd(double pointx, double pointy, double w, double h) {
        //if (textur == null) {
            glVertex2d(pointx, pointy);
            glVertex2d(pointx, pointy + h);
            glVertex2d(pointx + w, pointy);
        /*} else {
            glTexCoord2f(0, 0);
            glVertex2d(pointx, pointy);
            glTexCoord2f(0, 1);
            glVertex2d(pointx, pointy + h);
            glTexCoord2f(1, 0);
            glVertex2d(pointx + w, pointy);
        }*/
    }

    private void displayRechteck(double scbx, double scby, double scw, double sch) {
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
    
    //Boundingzeug
    public int bh()
    {
        return (int) this.h;
    }

    public int bw()
    {
        return (int) this.w;
    }
    
    public char btype()
    {
        return this.direction;
    }
    
    public double getYd()
    {
        return this.y;
    }
    
    public double getXd()
    {
        return this.x;
    }
    
    public void setzeW(double w){
        this.w=w;
        update();
    }
    public void setzeH(double h){
        this.h=h;
        update();
    }
    
    public boolean schneidet(OBJECT_2D obj)
    {
        return PhysicEngine.checkviolation(obj, this);
    }
}
