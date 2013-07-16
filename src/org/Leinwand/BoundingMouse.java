/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.Leinwand;

/**
 *
 * @author Ferdinand von Hagen ferdinand.vonhagen@altmuehlnet.de
 */
public class BoundingMouse extends BoundingKreis {

    public BoundingMouse() {
        super(10, 10, 1);
    }

    public double x() {
        return (double) Leinwand.gibLeinwand().getCorrectedMouseX();
    }

    public double y() {
        return (double) Leinwand.gibLeinwand().getCorrectedMouseY();
    }
}