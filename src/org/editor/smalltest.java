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

/**
 *
 * @author Ferdinand von Hagen
 * ferdinand.vonhagen@altmuehlnet.de
 */
public class smalltest {


    public static void main(String[] args)
    {
        new smalltest();
    }
    
    public smalltest(){
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
        boolean canttouchthis=false;
    
        Leinwand lein = Leinwand.gibLeinwand();
        
        Kreis k = null;
        Rechteck r=null;
        Dreieck d=null;
        List<Kreis> kreisList = new ArrayList<Kreis>();
        List<Rechteck> rechteckList=new ArrayList<Rechteck>();
        List<Dreieck> dreieckList=new ArrayList<Dreieck>();
        Mouse mouse = new Mouse();
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
                    d.setzeFarbe("schwarz");
                    dreieckList.add(d);
                    d = null;
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
}
