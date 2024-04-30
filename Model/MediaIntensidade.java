package Model;
import java.time.LocalDate;

public abstract class MediaIntensidade extends Atividade {
    protected double fatorIntensidade;

    public MediaIntensidade(String codigo, String descricao, LocalDate data, int duracao) {
        super(codigo, descricao, data, duracao);
        this.fatorIntensidade = 1.5;
    }
}