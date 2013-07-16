/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.TextureList;

import org.newdawn.slick.opengl.Texture;

/**
 *
 * @author Ferdinand von Hagen ferdinand.vonhagen@altmuehlnet.de
 */
public class TextureList
{

    TextureListElement element;

    public TextureList()
    {
        element = new TextureListAbschluss();
    }

    public Texture loadTexture(String path)
    {
        TextureListElement former = element;
        if (element.loadTexture(path) == null)
        {
            element = new TextureListPart(path, former);
        }
        return element.loadTexture(path);
    }
    
    public void destroy()
    {
        element.destroy();
        element = new TextureListAbschluss();
    }
}
