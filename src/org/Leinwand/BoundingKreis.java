/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.Leinwand;

/**
 *
 * @author Info3-S06
 */
public class BoundingKreis extends BoundingBox{
    
    protected double r;
    
    public BoundingKreis(double x, double y, double r){
        this.x=x;
        this.y=y;
        this.r=r;
    }
    
    public double r() {
        return this.r;
    }
    
    public void r(double r) {
        this.r = r;
    }
    
    public char getType(){
        return 'k';
    }
}