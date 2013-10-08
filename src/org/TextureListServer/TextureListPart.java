/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.TextureListServer;

import org.TextureList.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

/**
 *
 * @author Ferdinand von Hagen
 * ferdinand.vonhagen@altmuehlnet.de
 */
public class TextureListPart extends TextureListElement {
    private String path;
    private Texture texture;
    private TextureListElement next;
    public TextureListPart(String path, TextureListElement next)
    {
        this.next = next;
        this.path = path;
        try
        {
            // Load the wood texture
            this.texture = TextureLoader.getTexture(path.split("\\.")[1].toUpperCase(), new FileInputStream(new File(path)));
        }
        catch (IOException e)
        {
            System.err.println("Could not find " + path);
            this.texture = null;
        }
    }
    
    public Texture loadTexture(String path)
    {
        if(this.path.equals(path))
        {
            return this.texture;
        }
        else
        {
            return next.loadTexture(path);
        }
    }
    
    public void destroy()
    {
        next.destroy();
        texture.release();
        next = null;
    }
}