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
public class PhysicEngine {
    public static boolean checkviolation(BoundingRechteck b1, BoundingRechteck b2) {
        double w = 0;
        if (b1.x() < b2.x()) {
            w = b1.w();
        } else {
            w = b2.w();
        }

        if (Math.max(b1.x(), b2.x()) <= (Math.min(b1.x(), b2.x()) + w)) {
            return checky(b1, b2);
        } else {
            return false;
        }
    }

    private static boolean checky(BoundingRechteck b1, BoundingRechteck b2) {
        double h = 0;
        if (b1.y() < b2.y()) {
            h = b1.h();
        } else {
            h = b2.h();
        }
        if (Math.max(b1.y(), b2.y()) <= (Math.min(b1.y(), b2.y()) + h)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkviolation(BoundingKreis b1, BoundingKreis b2) {
        double distance_a = Math.pow((double) (b2.x() - b1.x()), 2);
        double distance_b = Math.pow((double) (b2.y() - b1.y()), 2);
        if (Math.sqrt(distance_a + distance_b) <= (b1.r() + b2.r())) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkviolation(BoundingRechteck b1, BoundingKreis b2) {
        return checkviolation(b2, b1);
    }

    public static boolean checkviolation(BoundingKreis kreis, BoundingRechteck rechteck) {
        if (kreis.x() >= rechteck.x()) {
            //rechts vom linken vertikalem Strich
            if (kreis.x() <= (rechteck.x() + rechteck.w())) {
                //links vom rechten vertikalem Strich
                if ((kreis.y() + kreis.r()) <= rechteck.y() || kreis.y() - kreis.r() >= rechteck.y() + rechteck.h()) {
                    //Über der waagrechten Parallele über dem Rechteck oder unter der waagrechten Parallele unter dem Rechteck
                    return false;
                } else {
                    //zwischen diesen Parallelen
                    return true;
                }
            } else {
                //rechts vom rechten vertikalem Strich
                if (kreis.y() >= rechteck.y()) {
                    //unter dem oberen horizontalem Strich
                    if (kreis.y() <= rechteck.y() + rechteck.h()) {
                        //über dem unteren horizontalem Strich
                        if (rechteck.x() + rechteck.w() > kreis.x() - kreis.r()) {
                            //links von der vertikalen Parallele rechts vom Rechteck
                            return true;
                        } else {
                            //rechts von dieser Parallele
                            return false;
                        }
                    } else {
                        //unter dem unteren horizontalem Strich
                        BoundingKreis b3 = new BoundingKreis(rechteck.x() + rechteck.w(), rechteck.y() + rechteck.h(), 0);
                        return checkviolation(kreis, b3);
                    }
                } else {
                    //über dem oberen horizontalem Strich
                    BoundingKreis b3 = new BoundingKreis(rechteck.x() + rechteck.w(), rechteck.y(), 0);
                    return checkviolation(kreis, b3);
                }
            }
        } else {
            //links vom linken vertikalem Strich
            if (kreis.y() >= rechteck.y()) {
                //unter dem oberem horizontalem Strich
                if (kreis.y() <= rechteck.y() + rechteck.h()) {
                    //über dem unterem horizontalem Strich
                    if (rechteck.x() <= kreis.x() + kreis.r()) {
                        //rechts von der vertikalen Parallele links vom Rechteck
                        return true;
                    } else {
                        //links von dieser Parallele
                        return false;
                    }
                } else {
                    //unter dem unterem horizontalem Strich
                    BoundingKreis b3 = new BoundingKreis(rechteck.x(), rechteck.y() + rechteck.h(), 0);
                    return checkviolation(kreis, b3);
                }
            } else {
                //über dem oberem horizontalem Strich
                BoundingKreis b3 = new BoundingKreis(rechteck.x(), rechteck.y(), 0);
                return checkviolation(kreis, b3);
            }
        }
    }

    public static boolean checkviolation(BoundingDreieck b1, BoundingKreis b2) {
        return checkviolation(b2, b1);
    }

    public static boolean checkviolation(BoundingKreis b1, BoundingDreieck b2) {
        BoundingRechteck b3 = new BoundingRechteck(b2.x(), b2.y(), b2.w(), b2.h());
        if (checkviolation(b1, b3) == false) {
            return false;
        } else {
            if (b2.type() == 'a') {
                if (b1.y() + (b2.w() / Math.sqrt((b2.h()) * (b2.h()) + (b2.w()) * (b2.w()))) * b1.r() <= b2.y() + (b1.x() - (b2.h() / Math.sqrt((b2.h()) * (b2.h()) + (b2.w()) * (b2.w()))) * b1.r() - b2.x()) * (b2.h() / b2.w())) {
                    return false;
                } else {
                    return true;
                }
            } else if (b2.type() == 'b') {
                if (b1.y() + (b2.w() / Math.sqrt((b2.w()) * (b2.w()) + (b2.h()) * (b2.h()))) * b1.r() <= b2.y() + b2.h() - (b1.x() + (b2.h() / Math.sqrt((b2.h()) * (b2.h()) + (b2.w()) * (b2.w()))) * b1.r() - b2.x()) * (b2.h() / b2.w())) {
                    return false;
                } else {
                    return true;
                }
            } else if (b2.type() == 'c') {
                if (b1.y() - (b2.w() / Math.sqrt((b2.w()) * (b2.w()) + (b2.h()) * (b2.h()))) * b1.r() >= b2.y() + (b1.x() + (b2.h() / Math.sqrt((b2.h()) * (b2.h()) + (b2.w()) * (b2.w()))) * b1.r() - b2.x()) * (b2.h() / b2.w())) {
                    return false;
                } else {
                    return true;
                }
            } else if (b2.type() == 'd') {
                if (b1.y() - (b2.w() / Math.sqrt((b2.w()) * (b2.w()) + (b2.h()) * (b2.h()))) * b1.r() >= b2.y() + b2.h() - (b1.x() - (b2.h() / Math.sqrt((b2.h()) * (b2.h()) + (b2.w()) * (b2.w()))) * b1.r() - b2.x()) * (b2.h() / b2.w())) {
                    return false;
                } else {
                    return true;
                }
            } else {
                return false;
            }
        }
    }

    public static boolean checkviolation(BoundingDreieck b1, BoundingRechteck b2) {
        return checkviolation(b2, b1);
    }

    public static boolean checkviolation(BoundingRechteck b1, BoundingDreieck b2) {
        BoundingRechteck b3 = new BoundingRechteck(b2.x(), b2.y(), b2.w(), b2.h());
        if (checkviolation(b1, b3) == false) {
            return false;
        } else {
            if (b2.type() == 'a') {
                if (b1.y() + b1.h() <= b2.y() + (b1.x() - b2.x()) * (b2.h() / b2.w())) {
                    return false;
                } else {
                    return true;
                }
            } else if (b2.type() == 'b') {
                if (b1.y() + b1.h() <= b2.y() + b2.h() - (b1.x() + b1.w() - b2.x()) * (b2.h() / b2.w())) {
                    return false;
                } else {
                    return true;
                }
            } else if (b2.type() == 'c') {
                if (b1.y() >= b2.y() + (b1.x() + b1.w() - b2.x()) * (b2.h() / b2.w())) {
                    return false;
                } else {
                    return true;
                }
            } else if (b2.type() == 'd') {
                if (b1.y() >= b2.y() + b2.h() - (b1.x() - b2.x()) * (b2.h() / b2.w())) {
                    return false;
                } else {
                    return true;
                }
            } else {
                return false;
            }
        }
    }

    public static boolean checkviolation(BoundingDreieck b1, BoundingDreieck b2) {
        BoundingRechteck b3 = new BoundingRechteck(b1.x(), b1.y(), b1.w(), b1.h());
        BoundingRechteck b4 = new BoundingRechteck(b2.x(), b2.y(), b2.w(), b2.h());
        if (checkviolation(b3, b4) == true) {
            if (b1.type() == 'a' && b2.type() == 'a') {
                if (b1.y() <= b2.y() + b2.h()) {
                    return checkviolation(b3, b2);
                } else {
                    return checkviolation(b4, b1);
                }
            } else if (b1.type() == 'b' && b2.type() == 'b') {
                if (b1.y() <= b2.y() + b2.h()) {
                    return checkviolation(b3, b2);
                } else {
                    return checkviolation(b4, b1);
                }
            } else if (b1.type() == 'c' && b2.type() == 'c') {
                if (b1.y() + b1.h() >= b2.y()) {
                    return checkviolation(b3, b2);
                } else {
                    return checkviolation(b4, b1);
                }
            } else if (b1.type() == 'd' && b2.type() == 'd') {
                if (b1.y() + b1.h() >= b2.y()) {
                    return checkviolation(b3, b2);
                } else {
                    return checkviolation(b4, b1);
                }
            } else if (b1.type() == 'a' && b2.type() == 'b' || b1.type() == 'b' && b2.type() == 'a') {
                if (b1.y() <= b2.y() + b2.h()) {
                    return checkviolation(b3, b2);
                } else {
                    return checkviolation(b1, b4);
                }
            } else if (b1.type() == 'a' && b2.type() == 'd' || b1.type() == 'd' && b2.type() == 'a') {
                if (b1.x() < b2.x() + b2.w()) {
                    return checkviolation(b3, b2);
                } else {
                    return checkviolation(b1, b4);
                }
            } else if (b1.type() == 'b' && b2.type() == 'c' || b1.type() == 'c' && b2.type() == 'b') {
                if (b1.x() < b2.x() + b2.w()) {
                    return checkviolation(b3, b2);
                } else {
                    return checkviolation(b1, b4);
                }
            } else if (b1.type() == 'c' && b2.type() == 'd' || b1.type() == 'd' && b2.type() == 'c') {
                if (b1.y() > b2.y()) {
                    return checkviolation(b3, b2);
                } else {
                    return checkviolation(b1, b4);
                }
            } else if (b1.type() == 'a' && b2.type() == 'c') {
                if ((b1.y() <= b2.y() + (b1.x() - b2.x()) * (b2.h() / b2.w())
                        || b1.y() + b1.h() <= b2.y() + (b1.x() + b1.w() - b2.x()) * (b2.h() / b2.w()))
                        && (b2.y() >= b1.y() + (b2.x() - b1.x()) * (b1.h() / b1.w())
                        || b2.y() + b2.h() >= b1.y() + (b2.x() + b2.w() - b1.x()) * (b1.h() / b1.w()))) {
                    return true;
                } else {
                    return false;
                }
            } else if (b1.type() == 'c' && b2.type() == 'a') {
                if ((b1.y() >= b2.y() + (b1.x() - b2.x()) * (b2.h() / b2.w())
                        || b1.y() + b1.h() >= b2.y() + (b1.x() + b1.w() - b2.x()) * (b2.h() / b2.w()))
                        && (b2.y() <= b1.y() + (b2.x() - b1.x()) * (b1.h() / b1.w())
                        || b2.y() + b2.h() <= b1.y() + (b2.x() + b2.w() - b1.x()) * (b1.h() / b1.w()))) {
                    return true;
                } else {
                    return false;
                }
            } else if (b1.type() == 'b' && b2.type() == 'd') {
                if ((b1.y() + b1.h() <= b2.y() + b2.h() - (b1.x() - b2.x()) * (b2.h() / b2.w())
                        || b1.y() <= b2.y() - (b1.x() + b1.w() - b2.x()) * (b2.h() / b2.w()))
                        && (b2.y() + b2.h() >= b1.y() + b1.h() - (b2.x() - b1.x()) * (b1.h() / b1.w())
                        || b2.y() >= b1.y() - (b2.x() + b2.w() - b1.x()) * (b1.h() / b1.w()))) {
                    return true;
                } else {
                    return false;
                }
            } else if (b1.type() == 'd' && b2.type() == 'b') {
                if ((b1.y() + b1.h() >= b2.y() + b2.h() - (b1.x() - b2.x()) * (b2.h() / b2.w())
                        || b1.y() >= b2.y() - (b1.x() + b1.w() - b2.x()) * (b2.h() / b2.w()))
                        && (b2.y() + b2.h() <= b1.y() + b1.h() - (b2.x() - b1.x()) * (b1.h() / b1.w())
                        || b2.y() <= b1.y() - (b2.x() + b2.w() - b1.x()) * (b1.h() / b1.w()))) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public static boolean checkviolation(BoundingBox b1, BoundingBox b2) {
        if (b1 instanceof BoundingKreis) {
            BoundingKreis k1 = (BoundingKreis) b1;
            if (b2 instanceof BoundingKreis) {
                BoundingKreis k2 = (BoundingKreis) b2;
                return PhysicEngine.checkviolation(k1, k2);
            }
            if (b2 instanceof BoundingRechteck) {
                BoundingRechteck k2 = (BoundingRechteck) b2;
                return PhysicEngine.checkviolation(k1, k2);
            }
            if (b2 instanceof BoundingDreieck) {
                BoundingDreieck k2 = (BoundingDreieck) b2;
                return PhysicEngine.checkviolation(k1, k2);
            }
            if (b2 instanceof BoundingMouse) {
                BoundingMouse k2 = (BoundingMouse) b2;
                return PhysicEngine.checkviolation(k1, k2);
            }
        }
        if (b1 instanceof BoundingRechteck) {
            BoundingRechteck k1 = (BoundingRechteck) b1;
            if (b2 instanceof BoundingKreis) {
                BoundingKreis k2 = (BoundingKreis) b2;
                return PhysicEngine.checkviolation(k1, k2);
            }
            if (b2 instanceof BoundingRechteck) {
                BoundingRechteck k2 = (BoundingRechteck) b2;
                return PhysicEngine.checkviolation(k1, k2);
            }
            if (b2 instanceof BoundingDreieck) {
                BoundingDreieck k2 = (BoundingDreieck) b2;
                return PhysicEngine.checkviolation(k1, k2);
            }
            if (b2 instanceof BoundingMouse) {
                BoundingMouse k2 = (BoundingMouse) b2;
                return PhysicEngine.checkviolation(k1, k2);
            }
        }
        if (b1 instanceof BoundingDreieck) {
            BoundingDreieck k1 = (BoundingDreieck) b1;
            if (b2 instanceof BoundingKreis) {
                BoundingKreis k2 = (BoundingKreis) b2;
                return PhysicEngine.checkviolation(k1, k2);
            }
            if (b2 instanceof BoundingRechteck) {
                BoundingRechteck k2 = (BoundingRechteck) b2;
                return PhysicEngine.checkviolation(k1, k2);
            }
            if (b2 instanceof BoundingDreieck) {
                BoundingDreieck k2 = (BoundingDreieck) b2;
                return PhysicEngine.checkviolation(k1, k2);
            }
            if (b2 instanceof BoundingMouse) {
                BoundingMouse k2 = (BoundingMouse) b2;
                return PhysicEngine.checkviolation(k1, k2);
            }
        }
        if (b1 instanceof BoundingMouse) {
            BoundingMouse k1 = (BoundingMouse) b1;
            if (b2 instanceof BoundingKreis) {
                BoundingKreis k2 = (BoundingKreis) b2;
                return PhysicEngine.checkviolation(k1, k2);
            }
            if (b2 instanceof BoundingRechteck) {
                BoundingRechteck k2 = (BoundingRechteck) b2;
                return PhysicEngine.checkviolation(k1, k2);
            }
            if (b2 instanceof BoundingDreieck) {
                BoundingDreieck k2 = (BoundingDreieck) b2;
                return PhysicEngine.checkviolation(k1, k2);
            }
            if (b2 instanceof BoundingMouse) {
                BoundingMouse k2 = (BoundingMouse) b2;
                return PhysicEngine.checkviolation(k1, k2);
            }
        }
        return false;
    }

    public static boolean checkviolation(List<BoundingBox> b1, BoundingBox b2) {
        for (BoundingBox bb : b1) {
            if (PhysicEngine.checkviolation(bb, b2)) {
                return true;
            }
        }
        return false;
    }
}