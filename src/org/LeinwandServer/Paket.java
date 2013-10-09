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
public class Paket implements Serializable
{
    public String id;
    public Serializable object;
    
    public Paket(String id, Serializable object)
    {
        this.id = id;
        this.object = object;
    }
    
    public Paket(String id)
    {
        this.id = id;
        this.object = 0;
    }
}
