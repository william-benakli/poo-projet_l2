import controleur.Controleur;
import modele.PlanComplex;

import javax.imageio.ImageIO;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {


      /*  try {
            new Vue();
        } catch (Exception e) {
            e.printStackTrace();
        }


/*
        double x1 = -1 ;
        double x2 = 1;
        double y1 = -1;
        double y2 = 1;
        double deplacement = 0.001;

        var img = new BufferedImage((int) ((x2-x1)/deplacement), (int) ((y2-y1)/deplacement), BufferedImage.TYPE_INT_RGB);

        ArrayList<Generator> arrg= new ArrayList<>();

        int cores = Runtime.getRuntime().availableProcessors();//number threads
        for (int core=1; core<=cores; core++){
            Generator g = new Generator(
                    core,
                    new PlanComplex(new  Point2D.Double(x1,y1),new  Point2D.Double(x2,y2)),
                    deplacement,
                    img
            );
            arrg.add(g);
            g.start();
        }

        try {
            for (Generator generator : arrg) {
                generator.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        */
        double x1 = -1;
        double x2 = 1;
        double y1 = -1;
        double y2 = 1;
        Controleur c = new Controleur();
        BufferedImage img = c.genererFractalByForkJoinPool(new PlanComplex(new Point2D.Double(x1, y1), new Point2D.Double(x2, y2)));


        File f = new File("MyFile.png");
        try {
            ImageIO.write(img, "PNG", f);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
