package Model;

public enum Dificuldade {
    FACIL(1.0),
    MEDIO(1.2),
    DIFICIL(1.5);

    private final double fator;

    Dificuldade(double fator) {
        this.fator = fator;
    }

    public double getFator() {
        return fator;
    }
}