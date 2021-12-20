package controleur;

import modele.PlanComplex;
import modele.TypeFractal;
import modele.thread.ThreadComplex;
import modele.thread.ThreadForkComplex;

import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool;

public class Controleur {

    private PlanComplex plan;

    public BufferedImage genererFractalByThreadComplex(PlanComplex plan) {
        double x1 = plan.getPos1().getX();
        double x2 = plan.getPos2().getX();
        double y1 = plan.getPos1().getY();
        double y2 = plan.getPos2().getY();

        double deplacement = 0.001;

        var img = new BufferedImage((int) ((x2 - x1) / deplacement), (int) ((y2 - y1) / deplacement), BufferedImage.TYPE_INT_RGB);

        ArrayList<ThreadComplex> arrg = new ArrayList<>();

        int cores = Runtime.getRuntime().availableProcessors();//number threads
        for (int core = 1; core <= cores; core++) {
            ThreadComplex g = new ThreadComplex(core, new PlanComplex(new Point2D.Double(x1, y1), new Point2D.Double(x2, y2)), deplacement, img, TypeFractal.ENSEMBLE_JULIA_T1.getType());
            arrg.add(g);
            g.start();
        }

        try {
            for (ThreadComplex threadComplex : arrg) threadComplex.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return img;
    }

    public BufferedImage genererFractalByNoThread(PlanComplex plan) {
        double x1 = plan.getPos1().getX();
        double x2 = plan.getPos2().getX();
        double y1 = plan.getPos1().getY();
        double y2 = plan.getPos2().getY();

        double deplacement = 0.001;

        var img = new BufferedImage((int) ((x2 - x1) / deplacement), (int) ((y2 - y1) / deplacement), BufferedImage.TYPE_INT_RGB);

        ArrayList<ThreadComplex> arrg = new ArrayList<>();

        int cores = Runtime.getRuntime().availableProcessors();//number threads
        for (int core = 1; core <= cores; core++) {
            ThreadComplex g = new ThreadComplex(core, new PlanComplex(new Point2D.Double(x1, y1), new Point2D.Double(x2, y2)), deplacement, img, TypeFractal.ENSEMBLE_JULIA_T1.getType());
            arrg.add(g);
            g.start();
        }

        try {
            for (ThreadComplex threadComplex : arrg) threadComplex.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return img;
    }

    public BufferedImage genererFractalByForkJoinPool(PlanComplex plan) {
        double x1 = plan.getPos1().getX();
        double x2 = plan.getPos2().getX();
        double y1 = plan.getPos1().getY();
        double y2 = plan.getPos2().getY();

        double deplacement = 0.001;

        var img = new BufferedImage((int) ((x2 - x1) / deplacement), (int) ((y2 - y1) / deplacement), BufferedImage.TYPE_INT_RGB);
        
        ThreadForkComplex work = new ThreadForkComplex(plan, deplacement, plan.getPos1().getX(), plan.getPos2().getX(), img, TypeFractal.ENSEMBLE_JULIA_T2.getType());
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(work);
        System.out.println(work.getCmp() + " nb pixel");
        return img;
    }
}
