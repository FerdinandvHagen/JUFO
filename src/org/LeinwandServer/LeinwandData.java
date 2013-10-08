/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.LeinwandServer;

import java.io.Serializable;
import java.util.List;
import javax.swing.JFrame;

/**
 *
 * @author Ferdinand von Hagen ferdinand.vonhagen@altmuehlnet.de
 */
public class LeinwandData implements Serializable
{

    public int transformx, transformy;
    public List<OBJECT_2D> objects;
    public List<OBJECT_2D> background_objects;
    public JFrame fenster;
    public boolean iscloserequested;
    //public TextureList textureList;
    public List<OBJECT_2D> textList;
    //Vairables for the Display
    public int height, width;
    public boolean fullscreen;
    public String title;
    //to Limit the FPS
    public boolean fpslimiter;
    public int fpslimit;
    public long lastNanoTime;
    public double fps;
    public long elapsed;
    //Faktor für das Vergrößern/Verkleinern von allem!!
    public double factor = 1.0;
    public int screenshotcounter = 0;
    
    public LeinwandData()
    {
        this.height = 480;
        this.width = 640;
        this.fullscreen = false;
        this.title = "openGL Test";
        this.transformx = 0;
        this.transformy = 0;
        this.iscloserequested = false;

        //The FPS Limiter is default on
        this.fpslimiter = true;
        this.fpslimit = 30;
        //textureList = new TextureList();
    }
}
