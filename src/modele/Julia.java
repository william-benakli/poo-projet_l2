package modele;

public final class Julia extends Fractal { //final qui ne peut pas etre etendu


    Julia() {

    }

    public Complex fonction(Complex complex, Complex type) {
        return complex.multiplication(complex).sum(type);
    }
}
