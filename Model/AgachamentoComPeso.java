package Model;
import java.time.LocalDate;
//import java.time.temporal.ChronoUnit;

public class AgachamentoComPeso extends RepsPesos{

    public AgachamentoComPeso(){
        super("AgachamentoComPeso", "Agachamento Com Peso", LocalDate.EPOCH, 0, Dificuldade.DIFICIL,0, 0);
    }

    public AgachamentoComPeso(String codigo, String descricao, LocalDate data, int duracao, Dificuldade dificuldade, int reps, int peso) {
        super(codigo, descricao, data, duracao, dificuldade, reps, peso);
    }

    public AgachamentoComPeso(AgachamentoComPeso outro) {
        super(outro.getCodigo(), outro.getDescricao(), LocalDate.EPOCH, outro.getDuracao(), outro.getDificuldade(), outro.getreps(), outro.getpeso());
    }

    @Override
    public Atividade clone() {
        return new AgachamentoComPeso(this);
    }

    @Override
    public String toString() {
        return "AgachamentoComPeso{" +
                super.toString()+
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return super.equals(o);
        /*return Double.compare(AgachamentoComPeso.getRep(), getRep()) == 0
                && this.getTipo().equals(AgachamentoComPeso.getTipo());*/
    }

}