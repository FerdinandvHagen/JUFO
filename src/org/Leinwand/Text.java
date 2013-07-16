/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.Leinwand;

import static org.lwjgl.opengl.GL11.*;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import java.awt.Color;

/**
 *
 * @author Ferdinand von Hagen ferdinand.vonhagen@altmuehlnet.de
 */
public class Text extends OBJECT_2D
{

    private static UnicodeFont font;
    private String text;
    private boolean hooked = false;
    private String fontart;
    private int size;
    private Color color;

    public Text(int x, int y, String text)
    {
        this.typetext = true;
        this.hooked = true;
        this.x = x;
        this.y = y;
        this.text = text;
        this.fontart = "Arial";
        this.size = 18;
        this.color = Color.BLACK;
        this.setUpFonts();
    }
    
    public Text(double x, double y, String text)
    {
        this.typetext = true;
        this.hooked = true;
        this.x = x;
        this.y = y;
        this.text = text;
        this.fontart = "Arial";
        this.size = 18;
        this.color = Color.BLACK;
        this.setUpFonts();
    }

    public void setFontColor(Color color)
    {
        this.color = color;
        this.setUpFonts();
    }

    public void setFontArt(String fontart)
    {
        this.fontart = fontart;
        this.setUpFonts();
    }

    public void setSize(int pixel)
    {
        this.size = pixel;
        this.setUpFonts();
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public void bewegen(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public void hook(boolean hook)
    {
        this.hooked = hook;
    }

    public void zeichnen(double factor)
    {
        if (hooked)
        {
            glMatrixMode(GL_MODELVIEW);
            glPushMatrix();
            glLoadIdentity();
            font.drawString((int) x, (int) y, text);
            glPopMatrix();
            glMatrixMode(GL_MODELVIEW);
        }
        else
        {
            font.drawString((int) x, (int) y, text);
        }
    }

    private void setUpFonts()
    {
        java.awt.Font awtFont = new java.awt.Font(this.fontart, java.awt.Font.BOLD, this.size);
        font = new UnicodeFont(awtFont);
        font.getEffects().add(new ColorEffect(this.color));
        font.addAsciiGlyphs();
        try
        {
            font.loadGlyphs();
        }
        catch (SlickException e)
        {
            e.printStackTrace();
        }
    }
    
    public double getXd()
    {
        return this.x;
    }
    
    public int getX()
    {
        return (int)this.x;
    }
    
    public double getYd()
    {
        return this.y;
    }
    
    public int getY()
    {
        return (int)this.y;
    }
}
