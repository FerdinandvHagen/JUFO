/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.Test;

import org.Leinwand.*;

/**
 *
 * @author ferdinandvhagen
 */
public class Tests {

    public static void main(String[] args) {
        new Tests();
    }

    public Tests() {
        Leinwand leinwand = Leinwand.gibLeinwand();     //Fenster erzeugen

        Rechteck rechteck = new Rechteck(40, 40, 100, 100);     //neues Objekt erzeugen
        rechteck.setzeFarbe("rot");                             //das Objekt rot machen
        rechteck.setzeSichtbarkeit(true);                       //und anzeigen

        Text text = new Text(10, 10, "Hallo");                  //Das gleiche geht auch mit Texten, Kreise, Dreiecken
        text.verbinden(true);                                   //Text kann man auch am Fenster ausrichten
        text.setzeSichtbarkeit(true);                           //und natürlich anzeigen

        Rechteck rechteck2 = new Rechteck(200, 200, 200, 200);  //Bilder kann man auch anzeigen
        rechteck2.ladeTextur("C:\\Users\\ferdinand\\Documents\\NetBeansProjects\\PROJEKT11SCHULE\\src\\Images\\spinner.jpg");
        rechteck2.setzeSichtbarkeit(true);
        
        Mouse maus = new Mouse();                               //Die Maus gibts auch als OBJEKT
        maus.setzeSichtbarkeit(true);
        

        leinwand.limitiereFPS(60);                          //Man kann auch die Framerate einstellen

        double winkel = 0.0;

        while (!leinwand.ueberpruefeAnfrageSchliessen()) {      //Solange keiner das Fenster schließen will, zeigen wir es an
            
            if (leinwand.istTasteGedrückt(Leinwand.KEY_0)) {    //Rechteck soll verschwinden, wenn die 0 gedrückt wird
                rechteck.setzeSichtbarkeit(false);
            } else {
                rechteck.setzeSichtbarkeit(true);
            }
            
            if(rechteck2.schneidet(maus)){                      //Rechteck2 verschwindet bei Berührung mit der Maus
                rechteck2.setzeSichtbarkeit(false);
            }
            else {
                rechteck2.setzeSichtbarkeit(true);
            }

            //Turn the Icon!
            winkel += (double) 360.0 / leinwand.holeFPS() * 2;  //Berechnung des Winkels
            if (winkel > 360) {
                winkel = 0;
            }

            rechteck2.setzeRotation((int) winkel);              //Und Rechteck2 drehen

            text.setzeText("FPS: " + leinwand.holeFPS() + " Drücke Null -> Rechteck verschwindet!");

            leinwand.neuZeichnen();                             //Leinwand wird neu gezeichnet (Änderugnen angezeigt)
        }

        leinwand.schliessen();                                  //Und hier wird die Leinwand geschlossen
    }
}
