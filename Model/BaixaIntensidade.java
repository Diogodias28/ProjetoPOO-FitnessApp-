package Model;
import java.time.LocalDate;

public abstract class BaixaIntensidade extends Atividade {
    protected double fatorIntensidade;

    public BaixaIntensidade(String codigo, String descricao, LocalDate data, int duracao) {
        super(codigo, descricao, data, duracao);
        this.fatorIntensidade = 1;
    }
}