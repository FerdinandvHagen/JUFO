/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.Leinwand;

/**
 *
 * @author Info3-S06
 */
public class BoundingDreieck extends BoundingBox {

    private double w;
    private double h;
    private char type;

    public BoundingDreieck(double x, double y, double w, double h, char type) {
        this.x =  x;
        this.y =  y;
        this.w =  w;
        this.h =  h;
        this.type = type;
    }

    public double w() {
        return this.w;
    }

    public void w(double w) {
        this.w = w;
    }

    public double h() {
        return this.h;
    }

    public void h(double h) {
        this.h = h;
    }

    public char type() {
        return this.type;
    }

    public void type(char type) {
        this.type = type;
    }
    
    public char getType(){
        return 'd';
    }
}