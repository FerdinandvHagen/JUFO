/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.LeinwandServer;

import java.io.Serializable;

/**
 *
 * @author Ferdinand von Hagen ferdinand.vonhagen@altmuehlnet.de
 */
public class KeyboardWrapper implements Serializable
{
    public boolean keyStates[];

    public KeyboardWrapper()
    {
        this.clear();
    }

    public void readKeyStates()
    {
        LeinwandServer lein = LeinwandServer.gibLeinwand();
        for (int i = 0; i < 224; i++)
        {
            keyStates[i] = lein.isKeyDown(i);
        }
    }

    public void clear()
    {
        keyStates = new boolean[255];
    }
}
