package Model;

public class AltaIntensidade implements Intensidade {
    private double fatorIntensidade;

    public AltaIntensidade() {
        this.fatorIntensidade = 2;
    }

    @Override
    public double getFatorIntensidade() {
        return fatorIntensidade;
    }
}
