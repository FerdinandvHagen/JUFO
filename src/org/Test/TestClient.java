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
        recht.ladeTextur("C:/Users/ADMIN/Documents/NetBeansProjects/F3DGL/0EAE5E44.jpg");
        
        Rechteck recht2 = new Rechteck(110, 10, 100, 100);
        recht2.setzeFarbe("rot");
        recht2.setzeSichtbarkeit(true);
        recht2.ladeTextur("C:/Users/ADMIN/Documents/NetBeansProjects/F3DGL/0EAE5E44.jpg");
        Rechteck recht3 = new Rechteck(110, 110, 100, 100);
        recht3.setzeFarbe("rot");
        recht3.setzeSichtbarkeit(true);
        recht3.ladeTextur("C:/Users/ADMIN/Documents/NetBeansProjects/F3DGL/0EAE5E44.jpg");
        Rechteck recht4 = new Rechteck(10, 110, 100, 100);
        recht4.setzeFarbe("rot");
        recht4.setzeSichtbarkeit(true);
        recht4.ladeTextur("C:/Users/ADMIN/Documents/NetBeansProjects/F3DGL/0EAE5E44.jpg");

        Mouse maus = new Mouse();
        maus.setzeSichtbarkeit(true);


        Text text = new Text(10, 10, "FPS");
        text.setzeSichtbarkeit(true);

        leinwand.useFPSLimiter(false);
        while (!leinwand.checkCloseRequest())
        {
            if (leinwand.isKeyDown(leinwand.KEY_UP))
            {
                recht.relbewegen(0, -10);
                recht2.relbewegen(0, -10);
                recht3.relbewegen(0, -10);
                recht4.relbewegen(0, -10);
            }
            if (leinwand.isKeyDown(leinwand.KEY_DOWN))
            {
                recht.relbewegen(0, 10);
                recht2.relbewegen(0, 10);
                recht3.relbewegen(0, 10);
                recht4.relbewegen(0, 10);
            }
            if (leinwand.isKeyDown(leinwand.KEY_RIGHT))
            {
                recht.relbewegen(10, 0);
                recht2.relbewegen(10, 0);
                recht3.relbewegen(10, 0);
                recht4.relbewegen(10, 0);
            }
            if (leinwand.isKeyDown(leinwand.KEY_LEFT))
            {
                recht.relbewegen(-10, 0);
                recht2.relbewegen(-10, 0);
                recht3.relbewegen(-10, 0);
                recht4.relbewegen(-10, 0);
            }
            //recht.bewegen(maus.bx(), maus.by());
            text.setText("FPS:" + leinwand.getfps());
            leinwand.redraw();
        }

        leinwand.close();
    }
}
