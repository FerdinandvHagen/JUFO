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
public class Text extends OBJECT_2D {

    private static UnicodeFont font;
    private String text;
    private boolean hooked = false;
    private String fontart;
    private int size;
    private Color color;

    /**
     * Erstellt einen neuen Text
     *
     * @param x x-Koordinate des Texts
     * @param y y-Koordinate des Texts
     * @param text Text, der angezeigt werden soll
     */
    public Text(int x, int y, String text) {
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

    /**
     * Erstellt einen neuen Text
     *
     * @param x x-Koordinate des Texts
     * @param y y-Koordinate des Texts
     * @param text Text, der angezeigt werden soll
     */
    public Text(double x, double y, String text) {
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

    /**
     * Setzt die Schriftfarbe
     *
     * @param color neue Schriftfarbe
     */
    public void setzeSchriftfarbe(Color color) {
        this.color = color;
        this.setUpFonts();
    }

    /**
     * Setzt die Schriftart
     *
     * @param fontart neue Schriftart
     */
    public void setzeSchriftart(String fontart) {
        this.fontart = fontart;
        this.setUpFonts();
    }

    /**
     * Setzt die Schriftgröße
     *
     * @param pixel neue Schriftgröße
     */
    public void setzeGröße(int pixel) {
        this.size = pixel;
        this.setUpFonts();
    }

    /**
     * Setzt den Text, der angezeigt wird
     *
     * @param text neuer Text zum Anzeigen
     */
    public void setzeText(String text) {
        this.text = text;
    }

    /**
     * Verbindet Rahmen und Text Wird die Position relativ zu Fenster gemessen,
     * hat eine Verschiebung des Koordinatensystems keinen Einfluss auf die
     * Position des Texts Wird die Position relativ zum Koordinatensystem
     * gemessen, vershciebt sich der Text zusammen mit dem Koordinatensystem
     *
     * @param hook true: Position wird relativ zum Fenster eingestellt; false:
     * Position wird relativ zum Koordiantensystem eingestellt
     */
    public void verbinden(boolean hook) {
        this.hooked = hook;
    }

    public void zeichnen(double factor) {
        if (hooked) {
            glMatrixMode(GL_MODELVIEW);
            glPushMatrix();
            glLoadIdentity();
            font.drawString((int) x, (int) y, text);
            glPopMatrix();
            glMatrixMode(GL_MODELVIEW);
        } else {
            font.drawString((int) x, (int) y, text);
        }
    }

    private void setUpFonts() {
        java.awt.Font awtFont = new java.awt.Font(this.fontart, java.awt.Font.BOLD, this.size);
        font = new UnicodeFont(awtFont);
        font.getEffects().add(new ColorEffect(this.color));
        font.addAsciiGlyphs();
        try {
            font.loadGlyphs();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean schneidet(OBJECT_2D obj) {
        return false;
    }

    /**
     * Ermittelt den angezeigten Text
     * @return Text, der gerade angezeigt wird
     */
    public String holeText() {
        return text;
    }
}
