package Model;

public enum Estilo {
    COSTAS(1.0),
    BRUCOS(1.3),
    CRAWL(1.3),
    MARIPOSA(1.5);

    private final double fator;

    Estilo(double fator) {
        this.fator = fator;
    }

    public double getFator() {
        return fator;
    }
}