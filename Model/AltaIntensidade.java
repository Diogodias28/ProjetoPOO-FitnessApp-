package Model;
import java.time.LocalDate;

public class AltaIntensidade extends Atividade {
    private static final double FATOR_INTENSIDADE = 2.5;

    public AltaIntensidade(String codigo, String descricao, LocalDate data, int duracao) {
        super(codigo, descricao, data, duracao);
    }

    @Override
    public double calorias() {
        return FATOR_INTENSIDADE;
    }

    @Override
    public Atividade clone() {
        return new AltaIntensidade(this.getCodigo(), this.getDescricao(), this.getData(), this.getDuracao());
    }
}