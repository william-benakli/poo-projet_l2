package modele;

import java.awt.geom.Point2D;

public class PlanComplex {


    private Point2D.Double debut, fin;

    public PlanComplex(Point2D.Double x1, Point2D.Double x2) {
        this.debut = x1;
        this.fin = x2;
    }

    public Point2D.Double getPos1() {
        return debut;
    }

    public Point2D.Double getPos2() {
        return fin;
    }

}
