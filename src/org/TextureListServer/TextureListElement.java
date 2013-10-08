/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.TextureListServer;

import org.TextureList.*;
import org.newdawn.slick.opengl.Texture;

/**
 *
 * @author Ferdinand von Hagen
 * ferdinand.vonhagen@altmuehlnet.de
 */
public abstract class TextureListElement {
    abstract Texture loadTexture(String path);
    abstract void destroy();
}
