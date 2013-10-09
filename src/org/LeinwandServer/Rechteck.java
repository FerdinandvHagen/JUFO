/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.LeinwandServer;

//import org.TextureListServer.Texture;
import static org.lwjgl.opengl.GL11.*;
import org.newdawn.slick.opengl.Texture;

/**
 *
 * @author ADMIN
 */
public class Rechteck extends OBJECT_2D
{

    private double w, h;
    private Texture texture;
    private String texturePath;
    private int scalex, scaley;

    public Rechteck(int x, int y, int w, int h)
    {
        super();
        this.texturePath = null;
        this.texture = null;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.scalex = 1;
        this.scaley = 1;
        setzeFarbe("schwarz");
    }

    public Rechteck(double x, double y, double w, double h)
    {
        super();
        this.texturePath = null;
        this.texture = null;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.scalex = 1;
        this.scaley = 1;
        setzeFarbe("schwarz");
    }

    public void scaleup(int x, int y)
    {
        this.scalex = x;
        this.scaley = y;
        update();
    }

    public void zeichnen(double factor)
    {
        double pointx, pointy;
        if (rotation != 0)
        {
            transx = x + (w / 2);
            transy = y + (h / 2);
            pointx = -w / 2;
            pointy = -h / 2;

            processTranslationsOne();
        }
        else
        {
            pointx = x;
            pointy = y;
        }

        //Hier wird das Objekt gezeichnet
        if (texturePath == null)
        {
            glColor4d(colorrd, colorgr, colorbl, alpha);
            glBegin(GL_QUADS);
            glVertex2d(pointx, pointy);
            glVertex2d(pointx + w, pointy);
            glVertex2d(pointx + w, pointy + h);
            glVertex2d(pointx, pointy + h);
            glEnd();
        }
        else
        {
            glColor4d(1f, 1f, 1f, 1f);
            glDisable(GL_TEXTURE_2D);
            glEnable(GL_TEXTURE_2D);
            if (texture == null)
            {
                texture = LeinwandServer.gibLeinwand().loadTexture(texturePath);
            }
            texture.bind();
            
            glBegin(GL_TRIANGLES);

            double scw = this.w / this.scalex;
            double sch = this.h / this.scaley;

            for (int scx = this.scalex; scx > 0; scx--)
            {
                for (int scy = this.scaley; scy > 0; scy--)
                {
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
        if (rotation != 0)
        {
            processTranslationsTwo();
        }
    }

    public void bewegen(int x, int y)
    {
        this.x = x;
        this.y = y;
        update();
    }

    public void ladeTextur(String textur)
    {
        this.texturePath = textur;
        update();
    }

    public double getXd()
    {
        return this.x;
    }

    public int getX()
    {
        return (int) this.x;
    }

    public double getYd()
    {
        return this.y;
    }

    public int getY()
    {
        return (int) this.y;
    }

    public double getWd()
    {
        return this.w;
    }

    public int getW()
    {
        return (int) this.w;
    }

    public double getHd()
    {
        return this.h;
    }

    public int getH()
    {
        return (int) this.h;
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

    public void setzeW(double w)
    {
        this.w = w;
        update();
    }

    public void setzeH(double h)
    {
        this.h = h;
        update();
    }

    public boolean schneidet(OBJECT_2D obj)
    {
        return PhysicEngine.checkviolation(obj, this);
    }
}
