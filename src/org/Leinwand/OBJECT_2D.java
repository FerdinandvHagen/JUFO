/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.Leinwand;

import java.util.List;
import static org.lwjgl.opengl.GL11.*;
/**
 *
 * @author ADMIN
 */
public abstract class OBJECT_2D
{

    private Leinwand lein;

    public OBJECT_2D()
    {
        lein = Leinwand.gibLeinwand();
    }
    //Variablendeklaration, die überall gleich ist
    protected double x, y;
    protected double colorgr, colorrd, colorbl, alpha;
    protected boolean background = false;
    protected boolean typetext = false;
    protected int rotation = 0;
    protected double transx, transy;
    protected boolean sichtbar;
    protected String farbe;

    //die abstrakten Methoden, die sich in jeder Klasse ändern
    public abstract void zeichnen(double factor);

    public abstract void bewegen(int x, int y);
    //jetzt allgemeine Methoden, die wir immer brauchen

    public double x()
    {
        return this.x;
    }

    public double y()
    {
        return this.y;
    }

    public void relbewegen(int x, int y)
    {
        this.x = this.x + x;
        this.y = this.y + y;
    }

    public void relbewegen(double x, double y)
    {
        this.x = this.x + x;
        this.y = this.y + y;
    }

    public void setzeSichtbarkeit(boolean sicht)
    {
        Leinwand t = Leinwand.gibLeinwand();
        if (sicht)
        {
            t.addObject(this);
            sichtbar=true;
        }
        else
        {
            t.removeObject(this);
            sichtbar=false;
        }
    }
    public boolean istSichtbar(){
        return sichtbar;
    }
    
    public void setzeFarbe(float red, float green, float blue, float alpha)
    {
        colorrd = red;
        colorgr = green;
        colorbl = blue;
        this.alpha = alpha;
    }

    public void setzeFarbe(String farbe)
    {
        if (farbe.equals("blau"))
        {
            colorgr = 0.0;
            colorrd = 0.0;
            colorbl = 1.0;
            farbe="blau";
        }
        else if (farbe.equals("rot"))
        {
            colorgr = 0.0;
            colorrd = 1.0;
            colorbl = 0.0;
            farbe="rot";
        }
        else if (farbe.equals("gruen"))
        {
            colorgr = 1.0;
            colorrd = 0.0;
            colorbl = 0.0;
            farbe="gruen";
        }
        else if (farbe.equals("weiss"))
        {
            colorgr = 1.0;
            colorrd = 1.0;
            colorbl = 1.0;
            farbe="weiss";
        }
        else if (farbe.equals("schwarz"))
        {
            colorgr = 0.0;
            colorrd = 0.0;
            colorbl = 0.0;
            farbe="schwarz";
        }
        else if (farbe.equals("braun"))
        {
            colorgr = 0.27;
            colorrd = 0.55;
            colorbl = 0.07;
            farbe="braun";
        }
        else if (farbe.equals("grau"))
        {
            colorgr = 0.53;
            colorrd = 0.466;
            colorbl = 0.60;
            farbe="grau";
        }
        else if (farbe.equals("orange"))
        {
            colorgr = 0.65;
            colorrd = 1.0;
            colorbl = 0.0;
            farbe="orange";
        }
        else if (farbe.equals("gelb"))
        {
            colorgr = 1.0;
            colorrd = 1.0;
            colorbl = 0.0;
            farbe="gelb";
        }
        else if (farbe.equals("violett"))
        {
            colorgr = 0.51;
            colorrd = 0.93;
            colorbl = 0.93;
            farbe="violett";
        }
        else
        {
            colorgr = 0.0;
            colorrd = 0.0;
            colorbl = 0.0;
        }
        if (farbe.equals("durchsichtig"))
        {
            alpha = 0.0;
        }
        else
        {
            alpha = 1.0;
        }
    }
    
    public String getFarbe(){
        return farbe;
    }

    public void setzeRotation(int winkel)
    {
        this.rotation = winkel;
    }

    public void setzeAlsHintergrund()
    {
        background = true;
    }
    
    public boolean isBackground()
    {
        return background;
    }
    
    public void setzeAlsText()
    {
        typetext = true;
    }
    
    public boolean isText()
    {
        return typetext;
    }
    
    protected void processTranslationsOne()
    {
        glPushAttrib(GL_TRANSFORM_BIT);
        glMatrixMode(GL_MODELVIEW);
        glTranslatef((float) transx, (float) transy, 0f);
        glRotatef((float) rotation, 0f, 0f, 1f);
        glPopAttrib();
    }
    
    protected void processTranslationsTwo()
    {
        glPushAttrib(GL_TRANSFORM_BIT);
        glMatrixMode(GL_MODELVIEW);
        glRotatef((float) (360 - rotation), 0f, 0f, 1f);
        glTranslatef((float) (-1*transx), (float) (-1*transy), 0f);
        glPopAttrib();
    }
    
    //Hier kommt jetzt das ganze mit den BoundingSachen
    public int bx()
    {
        return (int)this.x;
    }
    public int by()
    {
        return (int)this.y;
    }
    
    public abstract boolean schneidet(OBJECT_2D obj);
}
