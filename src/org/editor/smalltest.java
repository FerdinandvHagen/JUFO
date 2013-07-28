/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.editor;

import java.util.ArrayList;
import java.util.List;
import org.Leinwand.Kreis;
import org.Leinwand.Leinwand;
import org.Leinwand.Mouse;

/**
 *
 * @author Ferdinand von Hagen
 * ferdinand.vonhagen@altmuehlnet.de
 */
public class smalltest {
    public static void main(String[] args)
    {
        new smalltest();
    }
    
    public smalltest()
    {
        Leinwand lein = Leinwand.gibLeinwand();
        
        Kreis k = null;
        List<Kreis> kreisList = new ArrayList<Kreis>();
        Mouse mouse = new Mouse();
        while(!lein.checkCloseRequest())
        {
            if(lein.isMouseKeyDown(Leinwand.MOUSE_KEY_LEFT)&&k==null)
            {
                k = new Kreis(lein.getCorrectedMouseX(), lein.getCorrectedMouseY(), 0);
                k.setzeFarbe("rot");
                k.setzeSichtbarkeit(true);
            }
            if(k != null)
            {
                double ab = Math.pow(lein.getCorrectedMouseX()-k.getXd(), 2) + Math.pow(lein.getCorrectedMouseY()-k.getYd(), 2);
                double radius = Math.sqrt(ab);
                k.r(radius);
            }
            if(lein.isMouseKeyDown(Leinwand.MOUSE_KEY_RIGHT) && k!=null)
            {
                k.setzeFarbe("schwarz");
                kreisList.add(k);
                k = null;
            }
            
            if(lein.isKeyDown(Leinwand.KEY_S) && k == null)
            {
                for(Kreis kk : kreisList)
                {
                    if(mouse.schneidet(kk))
                    {
                        k = kk;
                        k.setzeFarbe("rot");
                        break;
                    }
                }
            }

            lein.redraw();
        }
    }
}
