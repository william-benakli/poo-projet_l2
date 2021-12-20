package modele;

public class Complex {

    private double real;
    private double imaginary;

    public Complex(double real, double imaginary) {
        setReal(real);
        setImaginary(imaginary);
    }

    public static Complex createComplex(double real, double imaginary) {
        return new Complex(real, imaginary);
    }

    public double getReal() {
        return this.real;
    }

    public void setReal(double real) {
        this.real = real;
    }

    public double getImaginary() {
        return this.imaginary;
    }

    public void setImaginary(double imaginary) {
        this.imaginary = imaginary;
    }

    public Complex sum(Complex c) {
        return new Complex(this.real + c.getReal(), this.imaginary + c.getImaginary());
    }

    public Complex multiplication(Complex c) {
        return new Complex(this.real * c.getReal() - this.imaginary * c.getImaginary(), this.real * c.getImaginary() + this.real * c.getImaginary());
    }


    public String toString() {
        return " [Real : " + this.real + " Imag : i" + this.imaginary + "]";
    }

    public Complex fonction(Complex complex, Complex type) {
        return complex.multiplication(complex).sum(type);
    }

    public int divergenceIndex(Complex type) {
        int ite = 0;
        Complex zn = this;

        while (ite < 1000 - 1 && (Math.sqrt(Math.pow(zn.imaginary, 2) + Math.pow(zn.real, 2)) <= 2)) {
            zn = fonction(zn, type);
            ite++;
        }
        return ite;
    }

}
