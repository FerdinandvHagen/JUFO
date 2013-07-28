
package Editor;

import org.Leinwand.*;
import static Editor.Editor.einfacheingabe;
import static Editor.Editor.mehrfacheingabe;
import java.util.List;
import java.util.ArrayList;
import java.lang.Math;
import javax.swing.JOptionPane;//für einzeleingabe
import javax.swing.JTextField;//für mehrfacheingabe




public class Editor{
    private Leinwand leinwand;
    private ArrayList<OBJECT_2D> List;
    private double mx1;
    private double my1;
    private double x;
    private double y;
    private OBJECT_2D memo;
    private Dreieck d1;
    private double memox=0;
    private double memoy=0;
    private boolean istGleich=false;
    private boolean once=true;
    private static String farbe;
    private Text text=new Text(5,5,"");
    
    public Editor(){
        this.leinwand = Leinwand.gibLeinwand();
        x=leinwand.getbreite();
        y=leinwand.gethoehe();
        List=new ArrayList<OBJECT_2D>();
    }
    
    
    
    public void setobject(double mx, double my) {
        Leinwand lein = Leinwand.gibLeinwand();
        if(once==true){
            text.setText("Setze den Startpunkt an der Spitze der Maus mit der S-Taste.");
            text.setzeSichtbarkeit(true);
            text.setFontColor(java.awt.Color.RED);
        once=false;
        System.out.println("asd");
        }
        if (memox== mx && memoy== my &&memo.istSichtbar()==true){
            istGleich=true;
        }
        else{
            istGleich=false;
        }
        if (lein.isKeyDown(Leinwand.KEY_S)) {
            mx1 = mx;
            my1 = my;
            text.setText("Um einen Kreis zu malen, bestimme mit der Maus den Radius und drücke die K-Taste.\nUm ein Rechteck zu malen, bestimme mit der Maus die untere rechte Ecke und drücke die R-Taste.\nUm ein Dreieck zu malen, bestimme den zweiten Punkt der Hypothenose und drücke Die D-Taste.");
            //text.setFontColor(java.awt.Color.RED);
            text.setzeSichtbarkeit(true);
        }
        if (lein.isKeyDown(Leinwand.KEY_K)&&istGleich==false) {
            double x1=mx-mx1;
            double y1=my-my1;
            memox=mx;
            memoy=my;
            double q=Math.sqrt((double)(Math.pow (x1*x,2)+ (Math.pow(y1*y,2 ) )) );
            Kreis k=new Kreis (mx1*x,my1*y,q);
            k.setzeFarbe("blau");
            k.setzeSichtbarkeit(true);
            leinwand.redraw();
            memo=k;
            text.setText("Um den Kreis zu löschen, drücken sie die B-Taste.\nUm ihn zu speichern, drücken sie die A-Taste.");
        }
        if (lein.isKeyDown(Leinwand.KEY_R) && istGleich==false) {
            double w = mx - mx1;
            double h = my - my1;
            memox=mx;
            memoy=my;
            Rechteck r = new Rechteck(mx1*x,my1*y,w*x,h*y);
            r.setzeFarbe("gruen");
            r.setzeSichtbarkeit(true);
            leinwand.redraw();
            memo=r;
        }
        if (lein.isKeyDown(Leinwand.KEY_D) && istGleich==false) {
            double w = mx - mx1;
            double h = my - my1;
            memox=mx;
            memoy=my;
            Dreieck d=new Dreieck (mx1*x,my1*y,w*x,h*y,'a');
            d.setzeFarbe("rot");
            d.setzeSichtbarkeit(true);
            leinwand.redraw();
            memo=d;
        }
        if(lein.isKeyDown(Leinwand.KEY_A)){
            if (memo==null){
            }
            else{ 
                List.add(memo);
                einfacheingabe("bla");
                memo.setzeFarbe(farbe);
                leinwand.redraw();
                memo=null;
            }
        }
        if(lein.isKeyDown(Leinwand.KEY_B)&&!(memo==null)){
            memo.setzeSichtbarkeit(false);
            leinwand.redraw();
            memox=0;
            memoy=0;
        }
    }
    
    public static void einfacheingabe(String bla){//um eine eingabe zu bekommen
 
                // Aufruf der statischen Methode showMessageDialog()
                farbe = JOptionPane.showInputDialog(null,"Geben Sie die gewünschte Farbe ein.","Eine Eingabeaufforderung",JOptionPane.PLAIN_MESSAGE);
 
 
        }
    public static void mehrfacheingabe(){//für viele eingaben
 
                // Erstellung Array vom Datentyp Object, Hinzufügen der Komponenten            
                JTextField farb = new JTextField();
                JTextField ran = new JTextField();
                JTextField ran2 = new JTextField();
                Object[] message = {"farbe", farb, "random", ran, "asd", ran2};
                JOptionPane pane = new JOptionPane( message, JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
                pane.createDialog(null, "mehrfacheingabe").setVisible(true);

        }
    
}

    
