package modele;

public abstract sealed class Fractal permits Julia, Mandelbrot {

    private Complex zn;
    private int ite;

    abstract protected Complex fonction(Complex zn, Complex type);

    public int divergenceIndex(Complex type) {
        while (ite < 1000 - 1 && (Math.sqrt(Math.pow(zn.getImaginary(), 2) + Math.pow(zn.getReal(), 2)) <= 2)) {
            zn = fonction(zn, type);
            ite++;
        }
        return ite;
    }

}
