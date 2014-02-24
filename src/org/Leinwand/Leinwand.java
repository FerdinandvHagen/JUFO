package org.Leinwand;

import java.awt.image.BufferedImage;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import java.util.ArrayList;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.Properties;
import javax.imageio.ImageIO;

import static org.lwjgl.opengl.GL11.*;
import org.newdawn.slick.opengl.Texture;

import org.lwjgl.BufferUtils;
import org.lwjgl.openal.AL;
import org.lwjgl.opengl.GL11;

/**
 * Adaption der bekannten Java-Leinwand-Implementationen auf openGL. Sie
 * erreicht eine deutlich erhöhte Performance, ist leicht zu bedienen und ist
 * dennoch ein sehr mächtiges Werkzeug für den Unterricht
 *
 * @author Ferdinand von Hagen
 * @version 1.1
 */
public class Leinwand {

    LeinwandData data;

    public void setLeinwandData(LeinwandData data) {
        this.data = data;
    }
    //Singleton ist das erste Objekt der Klasse Leinwand
    //Hier können andere Objekte eine Instanz von Leinwand abrufen
    //und bekommen ein Handle
    public static Leinwand leinwandSingleton;

    private Leinwand() {
        String lwjgl = "", jinput = "", jinput2 = "", openAL = "";
        System.out.println("System detected: " + getOsName() + "; Using " + getDataModel2() + "-bit Version;");
        System.out.println("Loading natives");
        
        if (getOsName() == "windows") {
            if (getDataModel2().contains("64")) {
                lwjgl = createTemp("/lwjgl64.dll", "lwjgl64.dll");
                jinput = createTemp("/jinput-raw_64.dll", "jinput-raw_64.dll");
                jinput2 = createTemp("/jinput-dx8_64.dll", "jinput-dx8_64.dll");
                openAL = createTemp("/OpenAL64.dll", "OpenAL64.dll");
            } else {
                lwjgl = createTemp("/lwjgl.dll", "lwjgl.dll");
                jinput = createTemp("/jinput-raw.dll", "jinput-raw.dll");
                jinput2 = createTemp("/jinput-dx8.dll", "jinput-dx8.dll");
                openAL = createTemp("/OpenAL32.dll", "OpenAL32.dll");
            }

            System.load(lwjgl);
            System.load(jinput);
            System.load(jinput2);
            System.load(openAL);
        } else if (getOsName() == "mac") {
            lwjgl = createTemp("/liblwjgl.jnilib", "liblwjgl.jnilib");
            jinput = createTemp("/libjinput-osx.jnilib", "libjinput-osx.jnilib");
            openAL = createTemp("/openal.jnilib", "openal.jnilib");

            System.load(lwjgl);
            System.load(jinput);
            System.load(openAL);
        } else {
            lwjgl = createTemp("/liblwjgl" + getDataModel() + ".so", "liblwjgl" + getDataModel() + ".so");
            jinput = createTemp("/libjinput-linux" + getDataModel() + ".so", "libjinput-linux" + getDataModel() + ".so");
            openAL = createTemp("/libopenal" + getDataModel() + ".so", "libopenal" + getDataModel() + ".so");

            System.load(lwjgl);
            System.load(jinput);
            System.load(openAL);
        }

        data = new LeinwandData();
        create();
    }

    private String getDataModel() {
        if (getDataModel2() == "64") {
            return "64";
        }
        return "";
    }

    private String getDataModel2() {
        return System.getProperty("sun.arch.data.model");
    }

    private String getOsName() {
        String os = "";
        if (System.getProperty("os.name").toLowerCase().indexOf("windows") > -1) {
            os = "windows";
        } else if (System.getProperty("os.name").toLowerCase().indexOf("linux") > -1) {
            os = "linux";
        } else if (System.getProperty("os.name").toLowerCase().indexOf("mac") > -1) {
            os = "mac";
        }

        return os;
    }

    private String createTemp(String dll, String name) {
        try {
            InputStream in = Leinwand.class.getResourceAsStream(dll);
            File file = new File(name);
            file.deleteOnExit();
            OutputStream out = new FileOutputStream(file);

            if (in == null) {
                System.err.println("Resource not found");
            } else {
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();

                return file.getAbsolutePath();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return "";
    }

    /**
     * Erzeugt eine Leinwand oder gibt eine zurück, falls schon eine existiert.
     *
     * @return eine geöffnete Leinwand.
     */
    public static Leinwand gibLeinwand() {
        if (leinwandSingleton == null) {
            leinwandSingleton = new Leinwand();
            System.out.println("Leinwand created");
            return leinwandSingleton;
        } else {
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
    public void setzeFensterEinstellungen(int height, int width, String title, boolean fullscreen) {
        this.data.height = height;
        this.data.width = width;
        this.data.title = title;
        this.data.fullscreen = fullscreen;
        this.neuOeffnen();
    }

    /**
     * Gibt die aktuelle Breite des Fensters zurück
     *
     * @return Breite des Fensters
     */
    public int holeBreite() {
        return this.data.width;
    }

    /**
     * Gibt die aktuelle Höhe des Fensters zurück
     *
     * @return Höhe des Fensters
     */
    public int holeHoehe() {
        return this.data.height;
    }

    /**
     * Öffnet die Leinwand neu (z.B. um die Einstellungen neu zu laden
     */
    public void neuOeffnen() {
        Display.destroy();
        createDisplay();
    }

    private void create() {
        this.data.objects = new ArrayList<OBJECT_2D>();
        this.data.background_objects = new ArrayList<OBJECT_2D>();
        this.data.textList = new ArrayList<OBJECT_2D>();
        createDisplay();
    }

    private void createDisplay() {
        try {
            // Setze die Höhe udn Breite des Displays, den Titel und erstellt es
            if (this.data.fullscreen) {
                Display.setFullscreen(true);
            } else {
                Display.setFullscreen(false);
                Display.setDisplayMode(new DisplayMode(this.data.width, this.data.height));
            }
            Display.setVSyncEnabled(true);
            Display.setTitle(data.title);
            Display.create();
            this.data.height = Display.getHeight();
            this.data.width = Display.getWidth();
        } catch (LWJGLException e) {
            e.printStackTrace();
            Display.destroy();
            System.exit(1);
        }
        glMatrixMode(GL_PROJECTION);
        glOrtho(0, this.data.width, this.data.height, 0, 1, -1);
        glMatrixMode(GL_MODELVIEW);
        glClearColor(1f, 1f, 1f, 1f);

        glEnable(GL_POINT_SMOOTH);	// Antialiasing f�r Punkte einschalten
        glEnable(GL_LINE_SMOOTH);	// Antialiasing f�r Linien einschalten

        //Alpha enable
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
    }

    private void applyOrtho() {
        glLoadIdentity();
        glPushAttrib(GL_TRANSFORM_BIT);
        glMatrixMode(GL_MODELVIEW);
        glTranslatef(this.data.transformx, this.data.transformy, 0);
        glPopAttrib();
    }

    /**
     * Löscht alle gespeicherten Daten und schließt das Fenster.
     */
    public void schliessen() {
        //Delete all textures
        this.data.textureList.destroy();
        //Destroy the Display
        Display.destroy();
        AL.destroy();
    }

    /**
     * Fügt der Leinwand ein OBJECT_2D hinzu.
     *
     * @param add zeigt das Element an.
     */
    public void addObject(OBJECT_2D add) {
        if (add.istHintergrund()) {
            //Das Objekt ist für den Hintergrund e.g. für die Landschaft bestimmt
            this.data.background_objects.remove(add);
            this.data.background_objects.add(add);
        }
        if (add.istText()) {
            this.data.textList.remove(add);    //entferne die Figur, falls vorhanden
            this.data.textList.add(add);       //neu anfügen
        }
        if (!add.istHintergrund() && !add.istText()) {
            this.data.objects.remove(add);
            this.data.objects.add(add);
        }
    }

    /**
     * Löscht ein Objekt von der Leinwand.
     *
     * @param add Objekt zum löschen
     */
    public void removeObject(OBJECT_2D add) {
        this.data.objects.remove(add);
        this.data.background_objects.remove(add);
        this.data.textList.remove(add);
    }

    /**
     * Zeichnet das Bild neu.
     */
    public void neuZeichnen() {
        if (this.istTasteGedrückt(this.KEY_LCONTROL) && this.istTasteGedrückt(this.KEY_S)) {
            this.data.screenshotcounter++;
            this.schiesseBildschirmfoto("screenshot" + this.data.screenshotcounter + ".jpg");
        }
        if (Display.isCloseRequested()) {
            this.data.iscloserequested = true;
            System.out.println("Display Close Requested");
        } else {
            glClear(GL_COLOR_BUFFER_BIT);
            applyOrtho();

            //Erst den Hintergrund zeichnen
            for (OBJECT_2D obj : this.data.background_objects) {
                obj.zeichnen(this.data.factor);
            }
            //Dann den Rest
            for (OBJECT_2D obj : this.data.objects) {
                obj.zeichnen(this.data.factor);
            }
            //Und dann noch den Text
            for (OBJECT_2D text : this.data.textList) {
                text.zeichnen(this.data.factor);
            }
            glEnable(GL_TEXTURE_2D);
            glDisable(GL_TEXTURE_2D);
            Display.update();
            if (this.data.fpslimiter) {
                Display.sync(this.data.fpslimit);
            }
        }

        //FPS berechnen
        this.data.elapsed = System.nanoTime() - this.data.lastNanoTime;
        this.data.fps = 1000000000 / this.data.elapsed;
        this.data.lastNanoTime = System.nanoTime();
    }

    /**
     * Startet oder stoppt den FPSLimiter
     *
     * @param use true, um das FPSLimit zu aktivieren; false um es zu
     * deaktivieren
     */
    public void nutzeFPSLimit(boolean use) {
        this.data.fpslimiter = use;
    }

    /**
     * Gibt die aktuelle Einstellung des FPSLimiters zurück
     *
     * @return 0, wenn er abgeschatet ist; >0 das aktuelle Limit
     */
    public int holeFPSLimit() {
        if (this.data.fpslimiter == false) {
            return 0;
        }
        return this.data.fpslimit;
    }

    /**
     * Aktiviert den FPSLimiter
     *
     * @param fps Das gewünschte Framelimit
     */
    public void limitiereFPS(int fps) {
        this.data.fpslimiter = true;
        this.data.fpslimit = fps;
    }

    /**
     * Berechnet ms seit letztem Aufruf der Funktion.
     *
     * @return ms seit letzten Aufruf der Funktion
     */
    public long holeDelta() {
        return this.data.elapsed;

    }

    /**
     * Berechnet fps
     *
     * @return fps
     *
     */
    public double holeFPS() {
        return this.data.fps;
    }

    /**
     * Abfrage, ob der Benutzer das Fenster schließen will.
     *
     * @return true, wenn der Benutzer das Fenster schließen will.
     */
    public boolean ueberpruefeAnfrageSchliessen() {
        return this.data.iscloserequested;
    }

    /**
     * Verändert das Koordinatensystem des Fensters.
     *
     * @param x neue x-Position.
     * @param y neue y-Position.
     */
    public void bewegen(int x, int y) {
        this.data.transformx = x;
        this.data.transformy = y;
    }

    /**
     * Verändert das Koordinatensystem des Fensters relativ.
     *
     * @param x Veränderung der x-Position.
     * @param y Veränderung der y-Position.
     */
    public void relativBewegen(int dx, int dy) {
        this.data.transformx += dx;
        this.data.transformy += dy;
    }

    /**
     * Setzt den Zoom der Leinwand
     *
     * @param zoom Wert des Zooms (normal: 1.0)
     */
    public void setzeZoom(double zoom) {
        this.data.factor = zoom;
    }

    /**
     * Lädt eine neue Textur.
     *
     * @param texture String mit Pfad zur Textur.
     * @return Eine geladene Textur.
     */
    public Texture loadTexture(String texture) {
        return this.data.textureList.loadTexture(texture);
    }

    /**
     * Verschiebt das Bild anhand der Eingaben der Pfeiltasten. Nützlich für
     * Übungen, Test, ...
     */
    public void verarbeiteTastaturEingabe() {
        if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
            this.data.transformy = this.data.transformy + 20;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
            this.data.transformy = this.data.transformy - 20;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
            this.data.transformx = this.data.transformx - 20;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
            this.data.transformx = this.data.transformx + 20;
        }
        if (istMausTasteGedrueckt(0)) {
            double xg = holeKorrigierteMausX();
            double yg = this.holeKorrigierteMausY();
            xg = xg / holeBreite();
            yg = yg / holeHoehe();
            System.out.println("X  " + xg + "  Y  " + yg);
        }
    }

    /**
     * Ist die Taste gedrückt
     *
     * @param key zu testende Taste
     * @return true, wenn Taste gedrückt
     */
    public boolean istTasteGedrückt(int key) {
        return Keyboard.isKeyDown(key);
    }

    /**
     * @return x-Position der Maus
     */
    public int holeMausX() {
        return Mouse.getX();
    }

    /**
     * @return y-Position der Maus
     */
    public int holeMausY() {
        return this.data.height - Mouse.getY();
    }

    /**
     * Veränderung der Mauslage.
     *
     * @return x-Veränderung der Lage der Maus seit letztem Aufruf
     */
    public int holeMausAenderungX() {
        return Mouse.getDX();
    }

    /**
     * Veränderung der Mauslage.
     *
     * @return x-Veränderung der Lage der Maus seit letztem Aufruf
     */
    public int holeMausAenderungY() {
        return Mouse.getDY();
    }

    /**
     * Mausraddrehung.
     *
     * @return Delta des Mausrads
     */
    public int holeMausAenderungRad() {
        return Mouse.getDWheel();
    }

    /**
     * Gibt die Koordinaten der Maus auf dem Koordinatensystem aus.
     *
     * @return x-Koordinate des Mauszeigers auf dem Koordinatensystem.
     */
    public int holeKorrigierteMausX() {
        return this.holeMausX() - this.data.transformx;
    }

    /**
     * Gibt die Koordinaten der Maus auf dem Koordinatensystem aus.
     *
     * @return y-Koordinate des Mauszeigers auf dem Koordinatensystem.
     */
    public int holeKorrigierteMausY() {
        return this.holeMausY() - this.data.transformy;
    }

    /**
     * Tastendruck Erkennung an der Maus
     *
     * @param index Tastenindex (normal 0 od. 1)
     * @return true, wenn die Taste gedrückt ist
     */
    public boolean istMausTasteGedrueckt(int index) {
        return Mouse.isButtonDown(index);
    }

    /**
     * Gibt die möglichen Maustasten samt Index aus.
     */
    public void zeigeVerfuegbareMausTasten() {
        for (int i = Mouse.getButtonCount(); i > 0; i--) {
            System.out.println("Key: Name: " + Mouse.getButtonName(i) + " index: " + i);
        }
    }

    /**
     * Sichtbarkeit des Mauszeigers.
     *
     * @param visible Beeinflusst die Sichtbarkeit des Mauszeigers
     */
    public void macheMausSichtbar(boolean visible) {
        Mouse.setGrabbed(visible);
    }
    private saveScreenshot savescreen;
    private long lastscreenshot = System.currentTimeMillis();

    public void schiesseBildschirmfoto(String file) {
        if (lastscreenshot + 500 > System.currentTimeMillis()) {
            return;
        }
        lastscreenshot = System.currentTimeMillis();
        GL11.glReadBuffer(GL11.GL_FRONT);
        int width = Display.getDisplayMode().getWidth();
        int height = Display.getDisplayMode().getHeight();
        int bpp = 4; // Assuming a 32-bit display with a byte each for red, green, blue, and alpha.
        ByteBuffer buffer = BufferUtils.createByteBuffer(width * height * bpp);
        GL11.glReadPixels(0, 0, width, height, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer);

        savescreen = new saveScreenshot(file, buffer, height, width);
        Runnable rs = (Runnable) savescreen;
        Thread s = new Thread(rs, "saving Screenshot");
        s.start();
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

    private class saveScreenshot implements Runnable {

        private ByteBuffer buffer;
        private String path;
        private int height, width;

        public saveScreenshot(String path, ByteBuffer buffer, int height, int width) {
            this.buffer = buffer;
            this.path = path;
            this.height = height;
            this.width = width;
        }

        public void run() {
            File screenshot = new File(path); // The file to save to.
            String format = "JPG"; // Example: "PNG" or "JPG"
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            int bpp = 4;
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    int i = (x + (width * y)) * bpp;
                    int r = buffer.get(i) & 0xFF;
                    int g = buffer.get(i + 1) & 0xFF;
                    int b = buffer.get(i + 2) & 0xFF;
                    image.setRGB(x, height - (y + 1), (0xFF << 24) | (r << 16) | (g << 8) | b);
                }
            }

            try {
                ImageIO.write(image, format, screenshot);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
