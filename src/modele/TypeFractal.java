package modele;

public enum TypeFractal {

    ENSEMBLE_JULIA_T1(new Complex(0.3, 0.5)),
    ENSEMBLE_JULIA_T2(new Complex(0.285, 0.01));

    private Complex type;

    TypeFractal(Complex c) {
        this.type = c;
    }

    public Complex getType() {
        return this.type;
    }
}
