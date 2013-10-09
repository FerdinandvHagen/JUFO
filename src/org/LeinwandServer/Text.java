/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.LeinwandServer;

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
    private boolean fontsSetUp;

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
        this.fontsSetUp = false;
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
        this.fontsSetUp = false;
    }

    public void setFontColor(Color color)
    {
        this.color = color;
        this.fontsSetUp = false;
        update();
    }

    public void setFontArt(String fontart)
    {
        this.fontart = fontart;
        this.fontsSetUp = false;
        update();
    }

    public void setSize(int pixel)
    {
        this.size = pixel;
        this.fontsSetUp = false;
        update();
    }

    public void setText(String text)
    {
        this.text = text;
        update();
    }

    public void bewegen(int x, int y)
    {
        this.x = x;
        this.y = y;
        update();
    }

    public void hook(boolean hook)
    {
        this.hooked = hook;
        update();
    }

    public void zeichnen(double factor)
    {
        if(!this.fontsSetUp)
        {
            this.setUpFonts();
        }
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

    @Override
    public boolean schneidet(OBJECT_2D obj)
    {
        return false;
    }

    public String getText()
    {
        return text;
    }
}
