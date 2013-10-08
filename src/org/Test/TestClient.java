/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.Test;

import org.LeinwandServer.*;

/**
 *
 * @author Ferdinand von Hagen ferdinand.vonhagen@altmuehlnet.de
 */
public class TestClient
{

    public static void main(String[] args)
    {
        Leinwand leinwand = Leinwand.gibLeinwand();

        Rechteck recht = new Rechteck(10, 10, 100, 100);
        recht.setzeFarbe("rot");
        recht.setzeSichtbarkeit(true);
        
        Mouse maus = new Mouse();
        maus.setzeSichtbarkeit(true);
        
        
        //Text text = new Text(10,10, "FPS");
        //text.setzeSichtbarkeit(true);

        leinwand.useFPSLimiter(false);
        while (!leinwand.checkCloseRequest())
        {
            /*if (leinwand.isKeyDown(leinwand.KEY_UP))
            {
                recht.relbewegen(0, -10);
            }
            if (leinwand.isKeyDown(leinwand.KEY_DOWN))
            {
                recht.relbewegen(0, 10);
            }
            if (leinwand.isKeyDown(leinwand.KEY_RIGHT))
            {
                recht.relbewegen(10, 0);
            }
            if (leinwand.isKeyDown(leinwand.KEY_LEFT))
            {
                recht.relbewegen(-10, 0);
            }*/
            recht.bewegen(maus.bx(), maus.by());
            //text.setText("FPS:" +leinwand.getfps());
            leinwand.redraw();
        }

        leinwand.close();
    }
}
