/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.LeinwandServer;

import org.Leinwand.*;

/**
 *
 * @author Ferdinand von Hagen
 * ferdinand.vonhagen@altmuehlnet.de
 */
public class Mouse extends Kreis{
    
    public Mouse()
    {
        super(10,10,1);
    }
    
    public int bx()
    {
        return Leinwand.gibLeinwand().getCorrectedMouseX();
    }
    
    public int by()
    {
        return Leinwand.gibLeinwand().getCorrectedMouseY();
    }
    
    public double x()
    {
        return Leinwand.gibLeinwand().getCorrectedMouseX();
    }
    
    public double y()
    {
        return Leinwand.gibLeinwand().getCorrectedMouseY();
    }

    @Override
    public void zeichnen(double factor)
    {
    }

    @Override
    public void bewegen(int x, int y)
    {
    }
    
    public void showMouse(boolean show)
    {
        Leinwand.gibLeinwand().makeMouseVisible(show);
        update();
    }

    @Override
    public boolean schneidet(OBJECT_2D obj)
    {
        return PhysicEngine.checkviolation(obj, this);
    }
}
