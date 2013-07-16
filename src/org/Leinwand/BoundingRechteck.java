/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.Leinwand;

/**
 *
 * @author Info3-S06
 */
public class BoundingRechteck extends BoundingBox{
    
    public double w;
    public double h;
    
    public BoundingRechteck(double x, double y, double w, double h){
        this.x=x;
        this.y=y;
        this.w=w;
        this.h=h;
    }
    
    public double h() {
        return this.h;
    }
    
    public void h(double h) {
        this.h = h;
    }
    
    public double w() {
        return this.w;
    }
    
    public void w(double w) {
        this.w = w;
    }
    
    public char getType(){
        return 'r';
    }
}