/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.editor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import org.Leinwand.Kreis;
import org.Leinwand.Rechteck;
import org.Leinwand.Dreieck;
import org.Leinwand.Leinwand;
import org.Leinwand.Mouse;
import org.Leinwand.OBJECT_2D;

import javax.swing.JFileChooser;
import java.io.File;
import javax.swing.JColorChooser;
import javax.swing.JFrame;

/**
 *
 * @author Ferdinand von Hagen
 * ferdinand.vonhagen@altmuehlnet.de
 */
public class smalltest {
    

        Leinwand lein=Leinwand.gibLeinwand();
        Mouse mouse = new Mouse();boolean canttouchthis=false;
    
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
                    canttouchthis=false;
                    k.setzeFarbe("schwarz");
                    kreisList.add(k);
                    memo=k;
                    getColor(k);
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
                    canttouchthis=false;
                    r.setzeFarbe("schwarz");
                    rechteckList.add(r);
                    memo=r;
                    getColor(r);
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
                    memo=d;
                    getColor(d);
                    d = null;
                }
                if(lein.isKeyDown(Leinwand.KEY_T)&&r==null){
                    for(Rechteck rr:rechteckList){
                        if(mouse.schneidet(rr)){
                            String a=getTexturePath();
                            System.out.println(a);
                            rr.ladeTextur(a);
                        }
                    }
                }if(lein.isKeyDown(Leinwand.KEY_T)&&d==null){
                    for(Dreieck dd:dreieckList){
                        if(mouse.schneidet(dd)){
                            String a=getTexturePath();
                            System.out.println(a);
                            dd.ladeTextur(a);
                        }
                    }
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

    public String getTexturePath(){
        JFileChooser chooser = new JFileChooser();
        int rueckgabeWert = chooser.showOpenDialog(null);
        
        if(rueckgabeWert == JFileChooser.APPROVE_OPTION){
            File a=chooser.getSelectedFile();
            System.out.println(a.getAbsolutePath());
            return a.getAbsolutePath();
            
        }
        else{
            return null;
        }
    }
    public void getColor(OBJECT_2D obj){
        JFrame guiFrame = new JFrame();
            Color selectedColor = JColorChooser.showDialog(guiFrame, "Pick a Color", Color.GREEN);
            obj.setzeFarbe(selectedColor.getRed(),selectedColor.getGreen(),selectedColor.getBlue(),selectedColor.getAlpha());
            System.out.println(selectedColor.getRed()+"  "+selectedColor.getGreen()+"  "+selectedColor.getBlue());
            guiFrame.dispose();
    }
}
