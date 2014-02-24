/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.Leinwand;

/**
 *
 * @author Ferdinand von Hagen ferdinand.vonhagen@altmuehlnet.de
 */
public class Mouse extends Kreis {

    /**
     * Erstellt eine neues Objekt Maus
     */
    public Mouse() {
        super(10, 10, 1);
    }

    public int bx() {
        return Leinwand.gibLeinwand().holeKorrigierteMausX();
    }

    public int by() {
        return Leinwand.gibLeinwand().holeKorrigierteMausY();
    }

    /**
     * Ermittelt die korrekte x-Koordinate der Maus, gemessen am
     * Koordinatensystem der Leinwand
     *
     * @return korrigierte x-Koordinate der Maus
     */
    public double x() {
        return Leinwand.gibLeinwand().holeKorrigierteMausX();
    }

    /**
     * Ermittelt die korrekte y-Koordinate der Maus, gemessen am
     * Koordinatensystem der Leinwand
     *
     * @return korrigierte y-Koordinate der Maus
     */
    public double y() {
        return Leinwand.gibLeinwand().holeKorrigierteMausY();
    }

    @Override
    public void zeichnen(double factor) {
    }

    @Override
    public void bewegen(int x, int y) {
    }

    /**
     * Legt fest, ob der Mauszeiger angezeigt wird oder nicht
     *
     * @param show true, wenn der Mauszeiger angezeigt werden soll; false wenn
     * nicht
     */
    public void zeigeMausZeiger(boolean show) {
        Leinwand.gibLeinwand().macheMausSichtbar(show);
    }
}
