/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.LeinwandServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import org.lwjgl.input.Keyboard;
//import org.newdawn.slick.opengl.Texture;

/**
 * Adaption der bekannten Java-Leinwand-Implementationen auf openGL. Sie
 * erreicht eine deutlich erhöhte Performance, ist leicht zu bedienen und ist
 * dennoch ein sehr mächtiges Werkzeug für den Unterricht
 *
 * @author Ferdinand von Hagen
 * @version 1.1
 */
public class Leinwand
{

    LeinwandData data;

    public void setLeinwandData(LeinwandData data)
    {
        this.data = data;
        try
        {
            out.writeObject("LeinwandData");
            out.writeObject(data);
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }
    //Singleton ist das erste Objekt der Klasse Leinwand
    //Hier können andere Objekte eine Instanz von Leinwand abrufen
    //und bekommen ein Handle
    public static Leinwand leinwandSingleton;
    Socket LeinwandServer;
    ObjectOutputStream out;
    ObjectInputStream in;

    private Leinwand()
    {
        try
        {
            LeinwandServer = new Socket("localhost", 3333);
            out = new ObjectOutputStream(LeinwandServer.getOutputStream());
            in = new ObjectInputStream(LeinwandServer.getInputStream());
        }
        catch (UnknownHostException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        setLeinwandData(new LeinwandData());
        create();
    }

    /**
     * Erzeugt eine Leinwand oder gibt eine zurück, falls schon eine existiert.
     *
     * @return eine geöffnete Leinwand.
     */
    public static Leinwand gibLeinwand()
    {
        if (leinwandSingleton == null)
        {
            leinwandSingleton = new Leinwand();
            System.out.println("Leinwand created");
            return leinwandSingleton;
        }
        else
        {
            return leinwandSingleton;
        }
    }

    //Konstruktoren von Leinwand
    /**
     * Setzt neue Parameter für das Fenster. Öffnet das Fenster neu. Alle
     * Elemente werden gelöscht!!
     *
     * @param height neue Höhe des Fensters.
     * @param width neue Breite des Fensters.
     * @param title neuer Titel des Fensters.
     * @param fullscreen true, wenn der Bildschirm fullscreen sein soll.
     */
    public void setWindowSettings(int height, int width, String title, boolean fullscreen)
    {
        this.data.height = height;
        this.data.width = width;
        this.data.title = title;
        this.data.fullscreen = fullscreen;
        this.reopen();
    }

    /**
     * Gibt die aktuelle Breite des Fensters zurück
     *
     * @return Breite des Fensters
     */
    public int getbreite()
    {
        try
        {
            out.writeObject("getbreite");
            out.writeObject(0);
            return (int) in.readObject();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        catch (ClassNotFoundException ex)
        {
            ex.printStackTrace();
        }
        return 0;
    }

    /**
     * Gibt die aktuelle Höhe des Fensters zurück
     *
     * @return Höhe des Fensters
     */
    public int gethoehe()
    {
        try
        {
            out.writeObject("gethoehe");
            out.writeObject(0);
            return (int) in.readObject();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        catch (ClassNotFoundException ex)
        {
            ex.printStackTrace();
        }
        return 0;
    }

    public void reopen()
    {
        setLeinwandData(data);
        try
        {
            out.writeObject("reopen");
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    private void create()
    {
        this.data.objects = new ArrayList<OBJECT_2D>();
        this.data.background_objects = new ArrayList<OBJECT_2D>();
        this.data.textList = new ArrayList<OBJECT_2D>();
    }

    /**
     * Löscht alle gespeicherten Daten und schließt das Fenster.
     */
    public void close()
    {
        //Delete all textures
        //this.data.textureList.destroy();
        try
        {
            out.writeObject("close");
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }
    /**
     * Fügt der Leinwand ein OBJECT_2D hinzu.
     *
     * @param add zeigt das Element an.
     */
    private long uniqueid = 1;

    public void addObject(OBJECT_2D add)
    {
        if (add.uniqueid == 0)
        {
            add.uniqueid = uniqueid++;
        }

        try
        {
            out.reset();
            out.writeObject("OBJECT_2D");
            out.writeObject(add);
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    /**
     * Löscht ein Objekt von der Leinwand.
     *
     * @param add Objekt zum löschen
     */
    public void removeObject(OBJECT_2D add)
    {
        try
        {
            out.writeObject("removeOBJECT_2D");
            out.writeObject(add);
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    /**
     * Zeichnet das Bild neu.
     */
    public void redraw()
    {
        try
        {
            out.writeObject("redraw");
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    /**
     * If you want to disable or enable the FPSLimiter.
     *
     * @param use false to disable the limit
     */
    public void useFPSLimiter(boolean use)
    {
        try
        {
            out.writeObject("FPSLimiter");
            out.writeObject(use);
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    /**
     * Returns current settings of the FPS-Limiter
     *
     * @return 0 if switched off; >0 the current FPSLimit;
     */
    public int getFPSLimit()
    {
        try
        {
            out.writeObject("getFPSLimit");
            out.writeObject(0);
            return (int) in.readObject();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        catch (ClassNotFoundException ex)
        {
            ex.printStackTrace();
        }
        return 0;
    }

    /**
     * Enables the FPSLimiter and sets it to the value defined by fps.
     *
     * @param fps The limit of fps you want to have.
     */
    public void limitFPS(int fps)
    {
        try
        {
            out.writeObject("limitFPS");
            out.writeObject(fps);
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    /**
     * Berechnet ms seit letztem Aufruf der Funktion.
     *
     * @return ms seit letzten Aufruf der Funktion
     */
    public long getdelta()
    {
        try
        {
            out.writeObject("getdelta");
            out.writeObject(0);
            return (int) in.readObject();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        catch (ClassNotFoundException ex)
        {
            ex.printStackTrace();
        }
        return 0;
    }

    /**
     * Berechnet fps
     *
     * @return fps
     *
     */
    public double getfps()
    {
        try
        {
            out.writeObject("getfps");
            out.writeObject(0);
            return (double) in.readObject();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        catch (ClassNotFoundException ex)
        {
            ex.printStackTrace();
        }
        return 0;
    }

    /**
     * Abfrage, ob der Benutzer das Fenster schließen will.
     *
     * @return true, wenn der Benutzer das Fenster schließen will.
     */
    public boolean checkCloseRequest()
    {
        try
        {
            out.writeObject("checkCloseRequest");
            out.writeObject(0);
            return (boolean) in.readObject();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        catch (ClassNotFoundException ex)
        {
            ex.printStackTrace();
        }
        return true;
    }

    /**
     * Verändert das Koordinatensystem des Fensters.
     *
     * @param x neue x-Position.
     * @param y neue y-Position.
     */
    public void changePosition(int x, int y)
    {
        xy var = new xy();
        var.x = x;
        var.y = y;
        try
        {
            out.writeObject("changePosition");
            out.writeObject(var);
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    /**
     * Verändert das Koordinatensystem des Fensters relativ.
     *
     * @param x Veränderung der x-Position.
     * @param y Veränderung der y-Position.
     */
    public void relbewegen(int dx, int dy)
    {
        xy var = new xy();
        var.x = dx;
        var.y = dy;
        try
        {
            out.writeObject("relbewegen");
            out.writeObject(var);
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    /**
     * Setzt den Zoom der Leinwand
     *
     * @param zoom Wert des Zooms (normal: 1.0)
     */
    public void setZoom(double zoom)
    {
        try
        {
            out.writeObject("setZoom");
            out.writeObject(zoom);
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    /**
     * Verschiebt das Bild anhand der Eingaben der Pfeiltasten. Nützlich für
     * Übungen, Test, ...
     */
    public void processKeyboardforTranslation()
    {
        if (Keyboard.isKeyDown(Keyboard.KEY_UP))
        {
            this.data.transformy = this.data.transformy + 20;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_DOWN))
        {
            this.data.transformy = this.data.transformy - 20;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
        {
            this.data.transformx = this.data.transformx - 20;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_LEFT))
        {
            this.data.transformx = this.data.transformx + 20;
        }
        if (isMouseKeyDown(0))
        {
            double xg = getCorrectedMouseX();
            double yg = getCorrectedMouseY();
            xg = xg / getbreite();
            yg = yg / gethoehe();
            System.out.println("X  " + xg + "  Y  " + yg);
        }
        try
        {
            out.writeObject("LeinwandData");
            out.writeObject(data);
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    /**
     * Ist die Taste gedrückt
     *
     * @param key zu testende Taste
     * @return true, wenn Taste gedrückt
     */
    public boolean isKeyDown(int key)
    {
        try
        {
            out.writeObject("isKeyDown");
            out.writeObject(key);
            return (boolean) in.readObject();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        catch (ClassNotFoundException ex)
        {
            ex.printStackTrace();
        }
        return true;
    }

    /**
     * @return x-Position der Maus
     */
    public int getMouseX()
    {
        try
        {
            out.writeObject("getMouseX");
            out.writeObject(0);
            return (int) in.readObject();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        catch (ClassNotFoundException ex)
        {
            ex.printStackTrace();
        }
        return 0;
    }

    /**
     * @return y-Position der Maus
     */
    public int getMouseY()
    {
        try
        {
            out.writeObject("getMouseY");
            out.writeObject(0);
            return (int) in.readObject();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        catch (ClassNotFoundException ex)
        {
            ex.printStackTrace();
        }
        return 0;
    }

    /**
     * Veränderung der Mauslage.
     *
     * @return x-Veränderung der Lage der Maus seit letztem Aufruf
     */
    public int getMouseDX()
    {
        try
        {
            out.writeObject("getMouseDX");
            out.writeObject(0);
            return (int) in.readObject();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        catch (ClassNotFoundException ex)
        {
            ex.printStackTrace();
        }
        return 0;
    }

    /**
     * Veränderung der Mauslage.
     *
     * @return x-Veränderung der Lage der Maus seit letztem Aufruf
     */
    public int getMouseDY()
    {
        try
        {
            out.writeObject("getMouseDY");
            out.writeObject(0);
            return (int) in.readObject();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        catch (ClassNotFoundException ex)
        {
            ex.printStackTrace();
        }
        return 0;
    }

    /**
     * Mausraddrehung.
     *
     * @return Delta des Mausrads
     */
    public int getDMouseWheel()
    {
        try
        {
            out.writeObject("getDMouseWheel");
            out.writeObject(0);
            return (int) in.readObject();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        catch (ClassNotFoundException ex)
        {
            ex.printStackTrace();
        }
        return 0;
    }

    /**
     * Gibt die Koordinaten der Maus auf dem Koordinatensystem aus.
     *
     * @return x-Koordinate des Mauszeigers auf dem Koordinatensystem.
     */
    public int getCorrectedMouseX()
    {
        return this.getMouseX() - this.data.transformx;
    }

    /**
     * Gibt die Koordinaten der Maus auf dem Koordinatensystem aus.
     *
     * @return y-Koordinate des Mauszeigers auf dem Koordinatensystem.
     */
    public int getCorrectedMouseY()
    {
        return this.getMouseY() - this.data.transformy;
    }

    /**
     * Tastendruck Erkennung an der Maus
     *
     * @param index Tastenindex (normal 0 od. 1)
     * @return true, wenn die Taste gedrückt ist
     */
    public boolean isMouseKeyDown(int index)
    {
        return org.lwjgl.input.Mouse.isButtonDown(index);
    }

    /**
     * Gibt die möglichen Maustasten samt Index aus.
     */
    public void printAvailableMouseKeys()
    {
        for (int i = org.lwjgl.input.Mouse.getButtonCount(); i > 0; i--)
        {
            System.out.println("Key: Name: " + org.lwjgl.input.Mouse.getButtonName(i) + " index: " + i);
        }
    }

    /**
     * Sichtbarkeit des Mauszeigers.
     *
     * @param visible Beeinflusst die Sichtbarkeit des Mauszeigers
     */
    public void makeMouseVisible(boolean visible)
    {
        org.lwjgl.input.Mouse.setGrabbed(visible);
    }
    //private org.Leinwand.Leinwand.saveScreenshot savescreen;
    private long lastscreenshot = System.currentTimeMillis();

    public void takeScreenshot(String file)
    {
        if (lastscreenshot + 500 > System.currentTimeMillis())
        {
            return;
        }
        lastscreenshot = System.currentTimeMillis();
    }
    //Key Definitions
    public static final int EVENT_SIZE = 18;
    public static final int CHAR_NONE = 0;
    public static final int KEY_NONE = 0;
    public static final int KEY_ESCAPE = 1;
    public static final int KEY_1 = 2;
    public static final int KEY_2 = 3;
    public static final int KEY_3 = 4;
    public static final int KEY_4 = 5;
    public static final int KEY_5 = 6;
    public static final int KEY_6 = 7;
    public static final int KEY_7 = 8;
    public static final int KEY_8 = 9;
    public static final int KEY_9 = 10;
    public static final int KEY_0 = 11;
    public static final int KEY_MINUS = 12;
    public static final int KEY_EQUALS = 13;
    public static final int KEY_BACK = 14;
    public static final int KEY_TAB = 15;
    public static final int KEY_Q = 16;
    public static final int KEY_W = 17;
    public static final int KEY_E = 18;
    public static final int KEY_R = 19;
    public static final int KEY_T = 20;
    public static final int KEY_Y = 21;
    public static final int KEY_U = 22;
    public static final int KEY_I = 23;
    public static final int KEY_O = 24;
    public static final int KEY_P = 25;
    public static final int KEY_LBRACKET = 26;
    public static final int KEY_RBRACKET = 27;
    public static final int KEY_RETURN = 28;
    public static final int KEY_LCONTROL = 29;
    public static final int KEY_A = 30;
    public static final int KEY_S = 31;
    public static final int KEY_D = 32;
    public static final int KEY_F = 33;
    public static final int KEY_G = 34;
    public static final int KEY_H = 35;
    public static final int KEY_J = 36;
    public static final int KEY_K = 37;
    public static final int KEY_L = 38;
    public static final int KEY_SEMICOLON = 39;
    public static final int KEY_APOSTROPHE = 40;
    public static final int KEY_GRAVE = 41;
    public static final int KEY_LSHIFT = 42;
    public static final int KEY_BACKSLASH = 43;
    public static final int KEY_Z = 44;
    public static final int KEY_X = 45;
    public static final int KEY_C = 46;
    public static final int KEY_V = 47;
    public static final int KEY_B = 48;
    public static final int KEY_N = 49;
    public static final int KEY_M = 50;
    public static final int KEY_COMMA = 51;
    public static final int KEY_PERIOD = 52;
    public static final int KEY_SLASH = 53;
    public static final int KEY_RSHIFT = 54;
    public static final int KEY_MULTIPLY = 55;
    public static final int KEY_LMENU = 56;
    public static final int KEY_SPACE = 57;
    public static final int KEY_CAPITAL = 58;
    public static final int KEY_F1 = 59;
    public static final int KEY_F2 = 60;
    public static final int KEY_F3 = 61;
    public static final int KEY_F4 = 62;
    public static final int KEY_F5 = 63;
    public static final int KEY_F6 = 64;
    public static final int KEY_F7 = 65;
    public static final int KEY_F8 = 66;
    public static final int KEY_F9 = 67;
    public static final int KEY_F10 = 68;
    public static final int KEY_NUMLOCK = 69;
    public static final int KEY_SCROLL = 70;
    public static final int KEY_NUMPAD7 = 71;
    public static final int KEY_NUMPAD8 = 72;
    public static final int KEY_NUMPAD9 = 73;
    public static final int KEY_SUBTRACT = 74;
    public static final int KEY_NUMPAD4 = 75;
    public static final int KEY_NUMPAD5 = 76;
    public static final int KEY_NUMPAD6 = 77;
    public static final int KEY_ADD = 78;
    public static final int KEY_NUMPAD1 = 79;
    public static final int KEY_NUMPAD2 = 80;
    public static final int KEY_NUMPAD3 = 81;
    public static final int KEY_NUMPAD0 = 82;
    public static final int KEY_DECIMAL = 83;
    public static final int KEY_F11 = 87;
    public static final int KEY_F12 = 88;
    public static final int KEY_F13 = 100;
    public static final int KEY_F14 = 101;
    public static final int KEY_F15 = 102;
    public static final int KEY_F16 = 103;
    public static final int KEY_F17 = 104;
    public static final int KEY_F18 = 105;
    public static final int KEY_KANA = 112;
    public static final int KEY_F19 = 113;
    public static final int KEY_CONVERT = 121;
    public static final int KEY_NOCONVERT = 123;
    public static final int KEY_YEN = 125;
    public static final int KEY_NUMPADEQUALS = 141;
    public static final int KEY_CIRCUMFLEX = 144;
    public static final int KEY_AT = 145;
    public static final int KEY_COLON = 146;
    public static final int KEY_UNDERLINE = 147;
    public static final int KEY_KANJI = 148;
    public static final int KEY_STOP = 149;
    public static final int KEY_AX = 150;
    public static final int KEY_UNLABELED = 151;
    public static final int KEY_NUMPADENTER = 156;
    public static final int KEY_RCONTROL = 157;
    public static final int KEY_SECTION = 167;
    public static final int KEY_NUMPADCOMMA = 179;
    public static final int KEY_DIVIDE = 181;
    public static final int KEY_SYSRQ = 183;
    public static final int KEY_RMENU = 184;
    public static final int KEY_FUNCTION = 196;
    public static final int KEY_PAUSE = 197;
    public static final int KEY_HOME = 199;
    public static final int KEY_UP = 200;
    public static final int KEY_PRIOR = 201;
    public static final int KEY_LEFT = 203;
    public static final int KEY_RIGHT = 205;
    public static final int KEY_END = 207;
    public static final int KEY_DOWN = 208;
    public static final int KEY_NEXT = 209;
    public static final int KEY_INSERT = 210;
    public static final int KEY_DELETE = 211;
    public static final int KEY_CLEAR = 218;
    public static final int KEY_LMETA = 219;
    public static final int KEY_LWIN = 219;
    public static final int KEY_RMETA = 220;
    public static final int KEY_RWIN = 220;
    public static final int KEY_APPS = 221;
    public static final int KEY_POWER = 222;
    public static final int KEY_SLEEP = 223;
    public static final int MOUSE_KEY_LEFT = 0;
    public static final int MOUSE_KEY_RIGHT = 1;
}
