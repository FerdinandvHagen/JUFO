/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.Test;

import org.Leinwand.Leinwand;
import org.Leinwand.Rechteck;
import org.Leinwand.Text;

/**
 *
 * @author ferdinandvhagen
 */
public class Tests {

    public static void main(String[] args) {
        new Tests();
    }

    public Tests() {
        Leinwand leinwand = Leinwand.gibLeinwand();

        Rechteck rechteck = new Rechteck(40, 40, 100, 100);
        rechteck.setzeFarbe("rot");
        rechteck.setzeSichtbarkeit(true);

        Text text = new Text(10, 10, "Hallo");
        text.verbinden(true);
        text.setzeSichtbarkeit(true);
        
        Rechteck rechteck2 = new Rechteck(100, 100, 200,200);
        rechteck2.ladeTextur("C:\\Users\\ferdinand\\Documents\\NetBeansProjects\\PROJEKT11SCHULE\\src\\Images\\spinner.jpg");
        rechteck2.setzeSichtbarkeit(true);
        
        leinwand.nutzeFPSLimit(false);
        
        double winkel = 0.0;

        while (!leinwand.ueberpruefeAnfrageSchliessen()) {
            if (leinwand.istTasteGedrückt(Leinwand.KEY_0)) {
                rechteck.setzeSichtbarkeit(false);
            } else {
                rechteck.setzeSichtbarkeit(true);
            }
            
            //Turn the Icon!
            winkel += (double)360.0/leinwand.holeFPS()*2;
            if(winkel>360){
                winkel = 0;
            }
            
            System.out.println(winkel);
            rechteck2.setzeRotation((int)winkel);
            
            text.setzeText("FPS: "+leinwand.holeFPS()+" Drücke Null -> Rechteck verschwindet!");

            leinwand.neuZeichnen();
        }
        
        leinwand.schliessen();
    }
}
