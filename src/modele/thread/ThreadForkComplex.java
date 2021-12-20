package modele.thread;

import modele.Complex;
import modele.PlanComplex;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.RecursiveAction;

public class ThreadForkComplex extends RecursiveAction {

    static int cmp = 0;
    private double j;
    private PlanComplex planComplex;
    private double deplacement;
    private BufferedImage img;
    private Complex type;
    private double from, to;

    public ThreadForkComplex(PlanComplex planComplex, double deplacement, double from, double to, BufferedImage img, Complex type) {
        //    this.i=planComplex.getPos1().getX();
        this.j = planComplex.getPos1().getY();
        this.type = type;
        this.deplacement = deplacement;
        this.planComplex = planComplex;
        this.img = img;
        this.from = from;
        this.to = to;
        System.out.println("From initial" + from + " To initial " + to);
    }

    public int getCmp() {
        return cmp;
    }

    @Override
    protected void compute() {
        //       System.out.println(to - from + " Valeur");

        //TODO: Pixel mauvaise convertion
/*
        System.out.println( "distance " + Math.abs(Math.abs(from/deplacement) - Math.abs(to/deplacement))+ " " +  Math.abs(from/deplacement)  + " " + Math.abs(to/deplacement));
        if( Math.abs(Math.abs(from/deplacement) - Math.abs(to/deplacement)) < 500){
            System.out.println(" Thread coupé ");
            computeDirectly();
            return;
        }
        */
        if (to - from <= 0.5) {
            computeDirectly();
            System.out.println("je sors et je divise");
            return;
        }
        //System.out.println("je sors et je divise");
        double middle = ((from + to) / 2.0);
        //System.out.println("le millieu ou je divise " + middle);
        invokeAll(new ThreadForkComplex(planComplex, deplacement, from, middle, img, type),
                new ThreadForkComplex(planComplex, deplacement, middle, to, img, type));
    }

    //On parcours en colonne pas en Ligne
    public void computeDirectly() {
        for (double i = this.from + 1; i <= to + 1; i += deplacement) {

            for (double j = this.j; j <= planComplex.getPos2().getY(); j += deplacement) {
                Complex complex = Complex.createComplex(i - 1, j);
                int indiceDivergent = complex.divergenceIndex(type);
                Color col = new Color(indiceDivergent % 255, ((indiceDivergent | 1) % 255), ((indiceDivergent & 150) % 255));
                img.setRGB((int) ((i - 1 - planComplex.getPos1().getX()) / deplacement), (int) ((j - planComplex.getPos1().getY()) / deplacement), col.getRGB());

            }

        }
    }
}
