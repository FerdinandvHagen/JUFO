/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.Leinwand;

import java.util.*;

/**
 *
 * @author Info3-S20
 */
public class PhysicEngine
{

    public static boolean checkviolation(Rechteck b1, Rechteck b2)
    {
        double w = 0;
        if (b1.bx() < b2.bx())
        {
            w = b1.bw();
        }
        else
        {
            w = b2.bw();
        }

        if (Math.max(b1.bx(), b2.bx()) <= (Math.min(b1.bx(), b2.bx()) + w))
        {
            return checky(b1, b2);
        }
        else
        {
            return false;
        }
    }

    private static boolean checky(Rechteck b1, Rechteck b2)
    {
        double h = 0;
        if (b1.by() < b2.by())
        {
            h = b1.bh();
        }
        else
        {
            h = b2.bh();
        }
        if (Math.max(b1.by(), b2.by()) <= (Math.min(b1.by(), b2.by()) + h))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static boolean checkviolation(Kreis b1, Kreis b2)
    {
        double distance_a = Math.pow((double) (b2.bx() - b1.bx()), 2);
        double distance_b = Math.pow((double) (b2.by() - b1.by()), 2);
        if (Math.sqrt(distance_a + distance_b) <= (b1.br() + b2.br()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public static boolean checkviolation(Rechteck b1, Kreis b2)
    {
        return checkviolation(b2, b1);
    }

    public static boolean checkviolation(Kreis kreis, Rechteck rechteck)
    {
        if (kreis.bx() >= rechteck.bx())
        {
            //rechts vom linken vertikalem Strich
            if (kreis.bx() <= (rechteck.bx() + rechteck.bw()))
            {
                //links vom rechten vertikalem Strich
                if ((kreis.by() + kreis.br()) <= rechteck.by() || kreis.by() - kreis.br() >= rechteck.by() + rechteck.bh())
                {
                    //Über der waagrechten Parallele über dem Rechteck oder unter der waagrechten Parallele unter dem Rechteck
                    return false;
                }
                else
                {
                    //zwischen diesen Parallelen
                    return true;
                }
            }
            else
            {
                //rechts vom rechten vertikalem Strich
                if (kreis.by() >= rechteck.by())
                {
                    //unter dem oberen horizontalem Strich
                    if (kreis.by() <= rechteck.by() + rechteck.bh())
                    {
                        //über dem unteren horizontalem Strich
                        if (rechteck.bx() + rechteck.bw() > kreis.bx() - kreis.br())
                        {
                            //links von der vertikalen Parallele rechts vom Rechteck
                            return true;
                        }
                        else
                        {
                            //rechts von dieser Parallele
                            return false;
                        }
                    }
                    else
                    {
                        //unter dem unteren horizontalem Strich
                        Kreis b3 = new Kreis(rechteck.bx() + rechteck.bw(), rechteck.by() + rechteck.bh(), 0);
                        return checkviolation(kreis, b3);
                    }
                }
                else
                {
                    //über dem oberen horizontalem Strich
                    Kreis b3 = new Kreis(rechteck.bx() + rechteck.bw(), rechteck.by(), 0);
                    return checkviolation(kreis, b3);
                }
            }
        }
        else
        {
            //links vom linken vertikalem Strich
            if (kreis.by() >= rechteck.by())
            {
                //unter dem oberem horizontalem Strich
                if (kreis.by() <= rechteck.by() + rechteck.bh())
                {
                    //über dem unterem horizontalem Strich
                    if (rechteck.bx() <= kreis.bx() + kreis.br())
                    {
                        //rechts von der vertikalen Parallele links vom Rechteck
                        return true;
                    }
                    else
                    {
                        //links von dieser Parallele
                        return false;
                    }
                }
                else
                {
                    //unter dem unterem horizontalem Strich
                    Kreis b3 = new Kreis(rechteck.bx(), rechteck.by() + rechteck.bh(), 0);
                    return checkviolation(kreis, b3);
                }
            }
            else
            {
                //über dem oberem horizontalem Strich
                Kreis b3 = new Kreis(rechteck.bx(), rechteck.by(), 0);
                return checkviolation(kreis, b3);
            }
        }
    }

    public static boolean checkviolation(Dreieck b1, Kreis b2)
    {
        return checkviolation(b2, b1);
    }

    public static boolean checkviolation(Kreis b1, Dreieck b2)
    {
        Rechteck b3 = new Rechteck(b2.bx(), b2.by(), b2.bw(), b2.bh());
        if (checkviolation(b1, b3) == false)
        {
            return false;
        }
        else
        {
            if (b2.btype() == 'a')
            {
                if (b1.by() + (b2.bw() / Math.sqrt((b2.bh()) * (b2.bh()) + (b2.bw()) * (b2.bw()))) * b1.br() <= b2.by() + (b1.bx() - (b2.bh() / Math.sqrt((b2.bh()) * (b2.bh()) + (b2.bw()) * (b2.bw()))) * b1.br() - b2.bx()) * (b2.bh() / b2.bw()))
                {
                    return false;
                }
                else
                {
                    return true;
                }
            }
            else if (b2.btype() == 'b')
            {
                if (b1.by() + (b2.bw() / Math.sqrt((b2.bw()) * (b2.bw()) + (b2.bh()) * (b2.bh()))) * b1.br() <= b2.by() + b2.bh() - (b1.bx() + (b2.bh() / Math.sqrt((b2.bh()) * (b2.bh()) + (b2.bw()) * (b2.bw()))) * b1.br() - b2.bx()) * (b2.bh() / b2.bw()))
                {
                    return false;
                }
                else
                {
                    return true;
                }
            }
            else if (b2.btype() == 'c')
            {
                if (b1.by() - (b2.bw() / Math.sqrt((b2.bw()) * (b2.bw()) + (b2.bh()) * (b2.bh()))) * b1.br() >= b2.by() + (b1.bx() + (b2.bh() / Math.sqrt((b2.bh()) * (b2.bh()) + (b2.bw()) * (b2.bw()))) * b1.br() - b2.bx()) * (b2.bh() / b2.bw()))
                {
                    return false;
                }
                else
                {
                    return true;
                }
            }
            else if (b2.btype() == 'd')
            {
                if (b1.by() - (b2.bw() / Math.sqrt((b2.bw()) * (b2.bw()) + (b2.bh()) * (b2.bh()))) * b1.br() >= b2.by() + b2.bh() - (b1.bx() - (b2.bh() / Math.sqrt((b2.bh()) * (b2.bh()) + (b2.bw()) * (b2.bw()))) * b1.br() - b2.bx()) * (b2.bh() / b2.bw()))
                {
                    return false;
                }
                else
                {
                    return true;
                }
            }
            else
            {
                return false;
            }
        }
    }

    public static boolean checkviolation(Dreieck b1, Rechteck b2)
    {
        return checkviolation(b2, b1);
    }

    public static boolean checkviolation(Rechteck b1, Dreieck b2)
    {
        Rechteck b3 = new Rechteck(b2.bx(), b2.by(), b2.bw(), b2.bh());
        if (checkviolation(b1, b3) == false)
        {
            return false;
        }
        else
        {
            if (b2.btype() == 'a')
            {
                if (b1.by() + b1.bh() <= b2.by() + (b1.bx() - b2.bx()) * (b2.bh() / b2.bw()))
                {
                    return false;
                }
                else
                {
                    return true;
                }
            }
            else if (b2.btype() == 'b')
            {
                if (b1.by() + b1.bh() <= b2.by() + b2.bh() - (b1.bx() + b1.bw() - b2.bx()) * (b2.bh() / b2.bw()))
                {
                    return false;
                }
                else
                {
                    return true;
                }
            }
            else if (b2.btype() == 'c')
            {
                if (b1.by() >= b2.by() + (b1.bx() + b1.bw() - b2.bx()) * (b2.bh() / b2.bw()))
                {
                    return false;
                }
                else
                {
                    return true;
                }
            }
            else if (b2.btype() == 'd')
            {
                if (b1.by() >= b2.by() + b2.bh() - (b1.bx() - b2.bx()) * (b2.bh() / b2.bw()))
                {
                    return false;
                }
                else
                {
                    return true;
                }
            }
            else
            {
                return false;
            }
        }
    }

    public static boolean checkviolation(Dreieck b1, Dreieck b2)
    {
        Rechteck b3 = new Rechteck(b1.bx(), b1.by(), b1.bw(), b1.bh());
        Rechteck b4 = new Rechteck(b2.bx(), b2.by(), b2.bw(), b2.bh());
        if (checkviolation(b3, b4) == true)
        {
            if (b1.btype() == 'a' && b2.btype() == 'a')
            {
                if (b1.by() <= b2.by() + b2.bh())
                {
                    return checkviolation(b3, b2);
                }
                else
                {
                    return checkviolation(b4, b1);
                }
            }
            else if (b1.btype() == 'b' && b2.btype() == 'b')
            {
                if (b1.by() <= b2.by() + b2.bh())
                {
                    return checkviolation(b3, b2);
                }
                else
                {
                    return checkviolation(b4, b1);
                }
            }
            else if (b1.btype() == 'c' && b2.btype() == 'c')
            {
                if (b1.by() + b1.bh() >= b2.by())
                {
                    return checkviolation(b3, b2);
                }
                else
                {
                    return checkviolation(b4, b1);
                }
            }
            else if (b1.btype() == 'd' && b2.btype() == 'd')
            {
                if (b1.by() + b1.bh() >= b2.by())
                {
                    return checkviolation(b3, b2);
                }
                else
                {
                    return checkviolation(b4, b1);
                }
            }
            else if (b1.btype() == 'a' && b2.btype() == 'b' || b1.btype() == 'b' && b2.btype() == 'a')
            {
                if (b1.by() <= b2.by() + b2.bh())
                {
                    return checkviolation(b3, b2);
                }
                else
                {
                    return checkviolation(b1, b4);
                }
            }
            else if (b1.btype() == 'a' && b2.btype() == 'd' || b1.btype() == 'd' && b2.btype() == 'a')
            {
                if (b1.bx() < b2.bx() + b2.bw())
                {
                    return checkviolation(b3, b2);
                }
                else
                {
                    return checkviolation(b1, b4);
                }
            }
            else if (b1.btype() == 'b' && b2.btype() == 'c' || b1.btype() == 'c' && b2.btype() == 'b')
            {
                if (b1.bx() < b2.bx() + b2.bw())
                {
                    return checkviolation(b3, b2);
                }
                else
                {
                    return checkviolation(b1, b4);
                }
            }
            else if (b1.btype() == 'c' && b2.btype() == 'd' || b1.btype() == 'd' && b2.btype() == 'c')
            {
                if (b1.by() > b2.by())
                {
                    return checkviolation(b3, b2);
                }
                else
                {
                    return checkviolation(b1, b4);
                }
            }
            else if (b1.btype() == 'a' && b2.btype() == 'c')
            {
                if ((b1.by() <= b2.by() + (b1.bx() - b2.bx()) * (b2.bh() / b2.bw())
                        || b1.by() + b1.bh() <= b2.by() + (b1.bx() + b1.bw() - b2.bx()) * (b2.bh() / b2.bw()))
                        && (b2.by() >= b1.by() + (b2.bx() - b1.bx()) * (b1.bh() / b1.bw())
                        || b2.by() + b2.bh() >= b1.by() + (b2.bx() + b2.bw() - b1.bx()) * (b1.bh() / b1.bw())))
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
            else if (b1.btype() == 'c' && b2.btype() == 'a')
            {
                if ((b1.by() >= b2.by() + (b1.bx() - b2.bx()) * (b2.bh() / b2.bw())
                        || b1.by() + b1.bh() >= b2.by() + (b1.bx() + b1.bw() - b2.bx()) * (b2.bh() / b2.bw()))
                        && (b2.by() <= b1.by() + (b2.bx() - b1.bx()) * (b1.bh() / b1.bw())
                        || b2.by() + b2.bh() <= b1.by() + (b2.bx() + b2.bw() - b1.bx()) * (b1.bh() / b1.bw())))
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
            else if (b1.btype() == 'b' && b2.btype() == 'd')
            {
                if ((b1.by() + b1.bh() <= b2.by() + b2.bh() - (b1.bx() - b2.bx()) * (b2.bh() / b2.bw())
                        || b1.by() <= b2.by() - (b1.bx() + b1.bw() - b2.bx()) * (b2.bh() / b2.bw()))
                        && (b2.by() + b2.bh() >= b1.by() + b1.bh() - (b2.bx() - b1.bx()) * (b1.bh() / b1.bw())
                        || b2.by() >= b1.by() - (b2.bx() + b2.bw() - b1.bx()) * (b1.bh() / b1.bw())))
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
            else if (b1.btype() == 'd' && b2.btype() == 'b')
            {
                if ((b1.by() + b1.bh() >= b2.by() + b2.bh() - (b1.bx() - b2.bx()) * (b2.bh() / b2.bw())
                        || b1.by() >= b2.by() - (b1.bx() + b1.bw() - b2.bx()) * (b2.bh() / b2.bw()))
                        && (b2.by() + b2.bh() <= b1.by() + b1.bh() - (b2.bx() - b1.bx()) * (b1.bh() / b1.bw())
                        || b2.by() <= b1.by() - (b2.bx() + b2.bw() - b1.bx()) * (b1.bh() / b1.bw())))
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
            else
            {
                return true;
            }
        }
        else
        {
            return false;
        }
    }

    public static boolean checkviolation(OBJECT_2D b1, OBJECT_2D b2)
    {
        if (b1 instanceof Kreis)
        {
            Kreis k1 = (Kreis) b1;
            if (b2 instanceof Kreis)
            {
                Kreis k2 = (Kreis) b2;
                return PhysicEngine.checkviolation(k1, k2);
            }
            if (b2 instanceof Rechteck)
            {
                Rechteck k2 = (Rechteck) b2;
                return PhysicEngine.checkviolation(k1, k2);
            }
            if (b2 instanceof Dreieck)
            {
                Dreieck k2 = (Dreieck) b2;
                return PhysicEngine.checkviolation(k1, k2);
            }
            if (b2 instanceof Mouse)
            {
                Mouse k2 = (Mouse) b2;
                return PhysicEngine.checkviolation(k1, k2);
            }
        }
        if (b1 instanceof Rechteck)
        {
            Rechteck k1 = (Rechteck) b1;
            if (b2 instanceof Kreis)
            {
                Kreis k2 = (Kreis) b2;
                return PhysicEngine.checkviolation(k1, k2);
            }
            if (b2 instanceof Rechteck)
            {
                Rechteck k2 = (Rechteck) b2;
                return PhysicEngine.checkviolation(k1, k2);
            }
            if (b2 instanceof Dreieck)
            {
                Dreieck k2 = (Dreieck) b2;
                return PhysicEngine.checkviolation(k1, k2);
            }
            if (b2 instanceof Mouse)
            {
                Mouse k2 = (Mouse) b2;
                return PhysicEngine.checkviolation(k1, k2);
            }
        }
        if (b1 instanceof Dreieck)
        {
            Dreieck k1 = (Dreieck) b1;
            if (b2 instanceof Kreis)
            {
                Kreis k2 = (Kreis) b2;
                return PhysicEngine.checkviolation(k1, k2);
            }
            if (b2 instanceof Rechteck)
            {
                Rechteck k2 = (Rechteck) b2;
                return PhysicEngine.checkviolation(k1, k2);
            }
            if (b2 instanceof Dreieck)
            {
                Dreieck k2 = (Dreieck) b2;
                return PhysicEngine.checkviolation(k1, k2);
            }
            if (b2 instanceof Mouse)
            {
                Mouse k2 = (Mouse) b2;
                return PhysicEngine.checkviolation(k1, k2);
            }
        }
        if (b1 instanceof Mouse)
        {
            Mouse k1 = (Mouse) b1;
            if (b2 instanceof Kreis)
            {
                Kreis k2 = (Kreis) b2;
                return PhysicEngine.checkviolation(k1, k2);
            }
            if (b2 instanceof Rechteck)
            {
                Rechteck k2 = (Rechteck) b2;
                return PhysicEngine.checkviolation(k1, k2);
            }
            if (b2 instanceof Dreieck)
            {
                Dreieck k2 = (Dreieck) b2;
                return PhysicEngine.checkviolation(k1, k2);
            }
            if (b2 instanceof Mouse)
            {
                Mouse k2 = (Mouse) b2;
                return PhysicEngine.checkviolation(k1, k2);
            }
        }
        return false;
    }

    public static boolean checkviolation(List<OBJECT_2D> b1, OBJECT_2D b2)
    {
        for (OBJECT_2D bb : b1)
        {
            if (PhysicEngine.checkviolation(bb, b2))
            {
                return true;
            }
        }
        return false;
    }
}