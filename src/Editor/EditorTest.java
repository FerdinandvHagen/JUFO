
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Editor;

import org.Leinwand.*;

/**
 *
 * @author Ferdinand von Hagen ferdinand.vonhagen@altmuehlnet.de
 */


public class EditorTest
{
    
    public static void main(String[] args)
    {
        new EditorTest();
    }

    public EditorTest()
    {
        Leinwand leinwand = Leinwand.gibLeinwand();
        //leinwand.setWindowSettings(480, 640, "", false);
        leinwand.setWindowSettings(450,720, "", false);
        //Diese Methode dauert später dann wahrscheinlich am längsten.
        Editor landscape = new Editor();
        long delta = 0;

        //DAS HIER IST NEU! TEXT IST JETZT EIN OBJEKT WIE ALLE ANDEREN AUCH
        /*Text text = new Text(4, 4, "");
        text.setzeSichtbarkeit(true);
        text.setFontColor(java.awt.Color.RED);
        */
        //landscape.draw(Landscape.Zimmer1);
        

        

        while (!leinwand.checkCloseRequest())
        {
            //text2.setText("You are in room: " + (landscape.currentroom() + 1) + leinwand.getCorrectedMouseX() + " ");
            //landscape.changeroom();
            double xm = leinwand.getCorrectedMouseX();
            double x2 = leinwand.getbreite();
            double ym = leinwand.getCorrectedMouseY();
            double y2 = leinwand.gethoehe();
            landscape.setobject(xm/ x2, ym/ y2);
            //leinwand.processKeyboardforTranslation();
            //leinwand.redraw();
            
            //text.setText("Maus: x " + leinwand.getCorrectedMouseX() + " / " + leinwand.getbreite() + "; testx " + xm/x2 + "; y " + leinwand.getCorrectedMouseY() + " / " + leinwand.gethoehe() + "; testy " + ym/y2);
            
            //leinwand.processKeyboardforTranslation();
            leinwand.redraw();
        }
        leinwand.close();
    }
}
