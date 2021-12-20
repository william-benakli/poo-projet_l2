package vue;

import controleur.Controleur;
import modele.Complex;
import modele.PlanComplex;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class Vue extends JFrame {

    private Fractal fractal;
    private Complex type; //new Complex((-0.8) , 0.156)
    private BufferedImage image;
    private JPanel button;
    private Controleur c = new Controleur();


    public Vue() throws Exception {

        this.setTitle("Fractal  ");
        this.setSize(720, 720);
        this.setPreferredSize(new Dimension(720, 720));
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.button = new JPanel();
        setLayout(new BorderLayout());
        JButton a = new JButton("COMPLEXE JULIA 1");
        JButton b = new JButton("COMPLEXE JULIA 2");
        JButton d = new JButton("Zoom");

        a.addActionListener((e) -> {
            type = new Complex((0.285), 0.01);
            this.image = c.genererFractalByForkJoinPool(new PlanComplex(new Point2D.Double(-1, -1), new Point2D.Double(1, 1)));
            fractal.setBg(image);
            fractal.updateUI();
            fractal.revalidate();
        });


        b.addActionListener((e) -> {
            type = new Complex((-0.8), 0.156);
            this.image = c.genererFractalByForkJoinPool(new PlanComplex(new Point2D.Double(-1, -1), new Point2D.Double(1, 1)));
            fractal.setBg(image);
            fractal.updateUI();
            fractal.revalidate();
        });

        d.addActionListener((e) -> {
            type = new Complex((0.285), 0.01);
            this.image = c.genererFractalByForkJoinPool(new PlanComplex(new Point2D.Double(-1, -1), new Point2D.Double(1, 1)));
            fractal.setBg(image);
            fractal.updateUI();
            fractal.revalidate();
        });

        button.add(d);
        button.add(a);
        button.add(b);

        add(button, BorderLayout.PAGE_END);
        fractal = new Fractal(image);
        fractal.setBackground(Color.CYAN);
        fractal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        this.add(fractal);
        fractal.updateUI();

        fractal.revalidate();
    }


    //class jPanel2 extends JPanel {
    class Fractal extends JPanel implements MouseInputListener {
        BufferedImage image;

        double x1 = -1;
        double x2 = 1;
        double y1 = -1;
        double y2 = 1;


        Fractal(BufferedImage image) {
            this.image = image;
            this.addMouseListener(this);
            this.addMouseMotionListener(this);
        }

        public void setBg(BufferedImage image) {
            this.image = image;
        }

        public double[] getPos() {
            return new double[]{x1, x2, y1, y2};
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println("position " + e.getX());
            this.x1 += (e.getX() / 720);
            this.x2 -= (e.getX() / 720);
            this.y1 += (e.getY() / 720);
            this.y2 -= (e.getY() / 720);
            //    image = genererFractal(x1,x2,y1,y2);
            updateUI();
            revalidate();
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        @Override
        public void mouseDragged(MouseEvent e) {

        }

        @Override
        public void mouseMoved(MouseEvent e) {

        }
    }


    class Menu extends JPanel {

        private JButton button;

    }

    class Move extends JPanel {

        private JButton button;

    }
}
