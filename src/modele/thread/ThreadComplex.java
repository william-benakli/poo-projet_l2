package modele.thread;

import modele.Complex;
import modele.PlanComplex;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ThreadComplex extends Thread {

    private double i, j;
    private int core;
    private PlanComplex planComplex;
    private double deplacement;
    private BufferedImage img;
    private Complex type;
    private int coreMAX = Runtime.getRuntime().availableProcessors();

    public ThreadComplex(int core, PlanComplex planComplex, double deplacement, BufferedImage img, Complex type) {
        this.i = planComplex.getPos1().getX();
        this.j = planComplex.getPos1().getY() + (deplacement * core);
        this.type = type;
        this.core = core;
        this.deplacement = deplacement;
        this.planComplex = planComplex;
        this.img = img;

    }

    @Override
    public void run() {
        while (i < planComplex.getPos2().getX() - deplacement) {
            double j = this.j;
            while (j < planComplex.getPos2().getY() - deplacement) {

                Complex complex = Complex.createComplex(i, j);
                int indiceDivergent = complex.divergenceIndex(type);
                Color col = new Color(indiceDivergent % 255, ((indiceDivergent | 1) % 255), ((indiceDivergent & 150) % 255));
                img.setRGB((int) ((i - planComplex.getPos1().getX()) / deplacement), (int) ((j - planComplex.getPos1().getY()) / deplacement), col.getRGB());
                j += coreMAX * deplacement;
            }
            i += deplacement;
        }
    }
}
