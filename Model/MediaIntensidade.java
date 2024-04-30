package Model;
import java.time.LocalDate;

public class MediaIntensidade extends Atividade{
    private static final double FATOR_INTENSIDADE = 1.5;

    public MediaIntensidade(String codigo, String descricao, LocalDate data, int duracao) {
        super(codigo, descricao, data, duracao);
    }

    @Override
    public double calorias() {
        return FATOR_INTENSIDADE;
    }

    @Override
    public Atividade clone() {
        return new MediaIntensidade(this.getCodigo(), this.getDescricao(), this.getData(), this.getDuracao());
    }
}
