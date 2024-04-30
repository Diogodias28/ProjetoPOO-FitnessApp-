package Model;
import java.time.LocalDate;

public class BaixaIntensidade extends Atividade {
    private static final double FATOR_INTENSIDADE = 1.0; // Exemplo de fator de intensidade para baixa intensidade

    public BaixaIntensidade(String codigo, String descricao, LocalDate data, int duracao) {
        super(codigo, descricao, data, duracao);
    }

    @Override
    public double calorias() {
        return FATOR_INTENSIDADE;
    }

    @Override
    public Atividade clone() {
        return new BaixaIntensidade(this.getCodigo(), this.getDescricao(), this.getData(), this.getDuracao());
    }
}