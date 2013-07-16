/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.Leinwand;

/**
 *
 * @author Info3-S15
 */
public abstract class BoundingBox {

    protected double x;
    protected double y;

    public void x(double x) {
        this.x = x;
    }

    public void y(double y) {
        this.y = y;
    }
    
    public void move(double x, double y)
    {
        this.x += x;
        this.y += y;
    }

    public double y() {
        return this.y;
    }

    public double x() {
        return this.x;
    }
    
    public abstract char getType();
}
