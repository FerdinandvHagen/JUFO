/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.editor;

import java.util.ArrayList;
import java.util.List;
import org.Leinwand.Kreis;
import org.Leinwand.Rechteck;
import org.Leinwand.Dreieck;
import org.Leinwand.Leinwand;
import org.Leinwand.Mouse;
import org.Leinwand.OBJECT_2D;

/**
 *
 * @author Ferdinand von Hagen
 * ferdinand.vonhagen@altmuehlnet.de
 */
public class smalltest {
    
        Rechteck blue=new Rechteck(165,15,15,15);
        Rechteck white=new Rechteck(150,15,15,15);
        Rechteck black=new Rechteck(150,30,15,15);
        Rechteck red=new Rechteck(165,30,15,15);
        Rechteck green=new Rechteck(180,15,15,15);
        Rechteck brown=new Rechteck(195,15,15,15);
        Rechteck orange=new Rechteck(195,30,15,15);
        Rechteck yellow=new Rechteck(180,30,15,15);
        Rechteck violett=new Rechteck(210,15,15,15);
        Rechteck gray=new Rechteck(210,30,15,15);
        Leinwand lein=Leinwand.gibLeinwand();
        Mouse mouse = new Mouse();boolean canttouchthis=false;
    
    public static void main(String[] args)
    {
        new smalltest();
    }
    
    public smalltest(){
        
        blue.setzeFarbe("blau");
        blue.setzeSichtbarkeit(true);
        white.setzeFarbe("weiss");
        white.setzeSichtbarkeit(true);
        black.setzeFarbe("schwarz");
        black.setzeSichtbarkeit(true);
        green.setzeFarbe("gruen");
        green.setzeSichtbarkeit(true);
        yellow.setzeFarbe("gelb");
        yellow.setzeSichtbarkeit(true);
        gray.setzeFarbe("grau");
        gray.setzeSichtbarkeit(true);
        orange.setzeFarbe("orange");
        orange.setzeSichtbarkeit(true);
        brown.setzeFarbe("braun");
        brown.setzeSichtbarkeit(true);
        violett.setzeFarbe("violett");
        violett.setzeSichtbarkeit(true);
        red.setzeFarbe("rot");
        red.setzeSichtbarkeit(true);
        
        
        Dreieck dtest=new Dreieck(15,15,30,30);
        Rechteck rtest=new Rechteck (60,15,30,30);
        Kreis ktest=new Kreis (120,30,15); 
        dtest.setzeFarbe("rot");
        rtest.setzeFarbe("rot");
        ktest.setzeFarbe("rot");
        ktest.setzeSichtbarkeit(true);
        rtest.setzeSichtbarkeit(true);
        dtest.setzeSichtbarkeit(true);
        char m='z';
        OBJECT_2D memo=null;
    
        
        Kreis k = null;
        Rechteck r=null;
        Dreieck d=null;
        List<Kreis> kreisList = new ArrayList<Kreis>();
        List<Rechteck> rechteckList=new ArrayList<Rechteck>();
        List<Dreieck> dreieckList=new ArrayList<Dreieck>();
        while(!lein.checkCloseRequest())
        {
            if(mouse.schneidet(dtest)&&lein.isMouseKeyDown(Leinwand.MOUSE_KEY_LEFT)){
                dtest.setzeFarbe("gruen");
                ktest.setzeFarbe("rot");
                rtest.setzeFarbe("rot");
                m='d';
            }
            if(mouse.schneidet(ktest)&&lein.isMouseKeyDown(Leinwand.MOUSE_KEY_LEFT)){
                ktest.setzeFarbe("gruen");
                dtest.setzeFarbe("rot");
                rtest.setzeFarbe("rot");
                m='k';
            }
            if(mouse.schneidet(rtest)&&lein.isMouseKeyDown(Leinwand.MOUSE_KEY_LEFT)){
                rtest.setzeFarbe("gruen");
                dtest.setzeFarbe("rot");
                ktest.setzeFarbe("rot");
                m='r';
            }
            if (mouse.schneidet(dtest)||mouse.schneidet(ktest)||mouse.schneidet(rtest)){
                canttouchthis=false;
            }
            else{
                canttouchthis=true;
            }
                //kreise:
                if(lein.isMouseKeyDown(Leinwand.MOUSE_KEY_LEFT)&&k==null&&m=='k'&&canttouchthis==true)
                {
                    k = new Kreis(lein.getCorrectedMouseX(), lein.getCorrectedMouseY(), 0);
                    k.setzeFarbe("rot");
                    k.setzeSichtbarkeit(true);
                }
                if(k != null)
                {
                    double ab = Math.pow(lein.getCorrectedMouseX()-k.getXd(), 2) + Math.pow(lein.getCorrectedMouseY()-k.getYd(), 2);
                    double radius = Math.sqrt(ab);
                    k.r(radius);
                }
                if(lein.isMouseKeyDown(Leinwand.MOUSE_KEY_RIGHT) && k!=null&&m=='k')
                {
                    k.setzeFarbe("schwarz");
                    kreisList.add(k);
                    k = null;
                }
            
                if(lein.isKeyDown(Leinwand.KEY_S) && k == null)
                {
                    for(Kreis kk : kreisList)
                    {
                        if(mouse.schneidet(kk))
                        {
                            k = kk;
                            k.setzeFarbe("rot");
                            ktest.setzeFarbe("gruen");
                            m='k';
                            break;
                        }
                    }
                }
                if(lein.isKeyDown(Leinwand.KEY_D) && k == null)
                {
                    for(Kreis kk : kreisList)
                    {
                        if(mouse.schneidet(kk))
                        {
                            kk.setzeSichtbarkeit(false);
                            kreisList.remove(kk);
                            ktest.setzeFarbe("gruen");
                            dtest.setzeFarbe("rot");
                            rtest.setzeFarbe("rot");
                            m='k';
                            break;
                        }
                    }
                }
                //rechtecke:
                if(lein.isMouseKeyDown(Leinwand.MOUSE_KEY_LEFT)&&r==null&&m=='r'&&canttouchthis==true)
                {
                    r = new Rechteck(lein.getCorrectedMouseX(), lein.getCorrectedMouseY(),0,0);
                    r.setzeFarbe("rot");
                    r.setzeSichtbarkeit(true);
                }
                if(r != null)
                {
                    r.setzeW(lein.getCorrectedMouseX()-r.getXd());
                    r.setzeH(lein.getCorrectedMouseY()-r.getYd());
                }
                if(lein.isMouseKeyDown(Leinwand.MOUSE_KEY_RIGHT) && r!=null&&m=='r')
                {
                    r.setzeFarbe("schwarz");
                    rechteckList.add(r);
                    r = null;
                }
            
                if(lein.isKeyDown(Leinwand.KEY_S) && r == null)
                {
                    for(Rechteck rr : rechteckList)
                    {
                        if(mouse.schneidet(rr))
                        {
                            r = rr;
                            r.setzeFarbe("rot");
                            rtest.setzeFarbe("gruen");
                            m='r';
                            break;
                        }
                    }
                }
                if(lein.isKeyDown(Leinwand.KEY_D) && r == null)
                {
                    for(Rechteck rr : rechteckList)
                    {
                        if(mouse.schneidet(rr))
                        {
                            rr.setzeSichtbarkeit(false);
                            rechteckList.remove(rr);
                            rtest.setzeFarbe("gruen");
                            ktest.setzeFarbe("rot");
                            dtest.setzeFarbe("rot");
                            m='r';
                            break;
                        }
                    }
                }
                //dreiecke:
                if(lein.isMouseKeyDown(Leinwand.MOUSE_KEY_LEFT)&&d==null&&m=='d'&&canttouchthis==true)
                {
                    d = new Dreieck(lein.getCorrectedMouseX(), lein.getCorrectedMouseY(),0,0);
                    d.setzeFarbe("rot");
                    d.setzeSichtbarkeit(true);
                }
                if(d != null)
                {
                    d.setzeW(lein.getCorrectedMouseX()-d.getXd());
                    d.setzeH(lein.getCorrectedMouseY()-d.getYd());
                }
                if(lein.isMouseKeyDown(Leinwand.MOUSE_KEY_RIGHT) && d!=null&&m=='d')
                {
                    canttouchthis=false;
                    d.setzeFarbe("schwarz");
                    dreieckList.add(d);
                    //farbauswahl(d);
                    memo=d;
                    d = null;
                    
                    System.out.println("asd");
                }
                if(m=='d'&&memo!=null&&mouse.schneidet(blue)&&lein.isMouseKeyDown(Leinwand.MOUSE_KEY_RIGHT)){
                    memo.setzeFarbe("blau");
                }
                if(m=='d'&&memo!=null&&mouse.schneidet(red)&&lein.isMouseKeyDown(Leinwand.MOUSE_KEY_RIGHT)){
                    memo.setzeFarbe("rot");
                }
                if(m=='d'&&memo!=null&&mouse.schneidet(white)&&lein.isMouseKeyDown(Leinwand.MOUSE_KEY_RIGHT)){
                    memo.setzeFarbe("weiss");
                }
                if(m=='d'&&memo!=null&&mouse.schneidet(black)&&lein.isMouseKeyDown(Leinwand.MOUSE_KEY_RIGHT)){
                    memo.setzeFarbe("schwarz");
                }
                if(m=='d'&&memo!=null&&mouse.schneidet(green)&&lein.isMouseKeyDown(Leinwand.MOUSE_KEY_RIGHT)){
                    memo.setzeFarbe("gruen");
                }
                if(m=='d'&&memo!=null&&mouse.schneidet(gray)&&lein.isMouseKeyDown(Leinwand.MOUSE_KEY_RIGHT)){
                    memo.setzeFarbe("grau");
                }
                if(m=='d'&&memo!=null&&mouse.schneidet(orange)&&lein.isMouseKeyDown(Leinwand.MOUSE_KEY_RIGHT)){
                    memo.setzeFarbe("orange");
                }
                if(m=='d'&&memo!=null&&mouse.schneidet(yellow)&&lein.isMouseKeyDown(Leinwand.MOUSE_KEY_RIGHT)){
                    memo.setzeFarbe("gelb");
                }
                if(m=='d'&&memo!=null&&mouse.schneidet(violett)&&lein.isMouseKeyDown(Leinwand.MOUSE_KEY_RIGHT)){
                    memo.setzeFarbe("violett");
                }
                if(m=='d'&&memo!=null&&mouse.schneidet(brown)&&lein.isMouseKeyDown(Leinwand.MOUSE_KEY_RIGHT)){
                    memo.setzeFarbe("braun");
                }
                if(lein.isKeyDown(Leinwand.KEY_S) && d == null)
                {
                    for(Dreieck dd : dreieckList)
                    {
                        if(mouse.schneidet(dd))
                        {
                            d = dd;
                            d.setzeFarbe("rot");
                            dtest.setzeFarbe("gruen");
                            m='d';
                            break;
                        }
                    }
                }
                if(lein.isKeyDown(Leinwand.KEY_D) && d == null)
                {
                    for(Dreieck dd : dreieckList)
                    {
                        if(mouse.schneidet(dd))
                        {
                            dd.setzeSichtbarkeit(false);
                            dreieckList.remove(dd);
                            dtest.setzeFarbe("gruen");
                            ktest.setzeFarbe("rot");
                            rtest.setzeFarbe("rot");
                            m='d';
                            break;
                        }
                    }
                }
                
                
                
                
                
            lein.redraw();
        }
    }

    public void farbauswahl(OBJECT_2D obj){
        while(!lein.isKeyDown(Leinwand.KEY_P)&&mouse.schneidet(obj)){
            if(lein.isMouseKeyDown(Leinwand.MOUSE_KEY_LEFT)&&mouse.schneidet(blue)){
                obj.setzeFarbe(blue.getFarbe());
            }
            if(lein.isMouseKeyDown(Leinwand.MOUSE_KEY_LEFT)&&mouse.schneidet(red)){
                obj.setzeFarbe(red.getFarbe());
            }
            if(lein.isMouseKeyDown(Leinwand.MOUSE_KEY_LEFT)&&mouse.schneidet(black)){
                obj.setzeFarbe(black.getFarbe());
            }
            if(lein.isMouseKeyDown(Leinwand.MOUSE_KEY_LEFT)&&mouse.schneidet(white)){
                obj.setzeFarbe(white.getFarbe());
            }
            if(lein.isMouseKeyDown(Leinwand.MOUSE_KEY_LEFT)&&mouse.schneidet(green)){
                obj.setzeFarbe(green.getFarbe());
            }
            if(lein.isMouseKeyDown(Leinwand.MOUSE_KEY_LEFT)&&mouse.schneidet(orange)){
                obj.setzeFarbe(orange.getFarbe());
            }
            if(lein.isMouseKeyDown(Leinwand.MOUSE_KEY_LEFT)&&mouse.schneidet(brown)){
                obj.setzeFarbe(brown.getFarbe());
            }
            if(lein.isMouseKeyDown(Leinwand.MOUSE_KEY_LEFT)&&mouse.schneidet(violett)){
                obj.setzeFarbe(violett.getFarbe());
            }
            if(lein.isMouseKeyDown(Leinwand.MOUSE_KEY_LEFT)&&mouse.schneidet(gray)){
                obj.setzeFarbe(gray.getFarbe());
            }
            if(lein.isMouseKeyDown(Leinwand.MOUSE_KEY_LEFT)&&mouse.schneidet(yellow)){
                obj.setzeFarbe(yellow.getFarbe());
            }
            lein.redraw();
        }
        
        System.out.println("bla");
    }
}
