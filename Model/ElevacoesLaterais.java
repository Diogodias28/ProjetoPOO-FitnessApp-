package Model;
import java.time.LocalDate;
//import java.time.temporal.ChronoUnit;

public class ElevacoesLaterais extends RepsPesos{

    public ElevacoesLaterais(){
        super("ElevacoesLaterais", "Elevações Laterais", LocalDate.EPOCH, 0, Dificuldade.FACIL,0, 0);
    }

    public ElevacoesLaterais(String codigo, String descricao, LocalDate data, int duracao, Dificuldade dificuldade, int reps, int peso) {
        super(codigo, descricao, data, duracao, dificuldade, reps, peso);
    }

    public ElevacoesLaterais(ElevacoesLaterais outro) {
        super(outro.getCodigo(), outro.getDescricao(), LocalDate.EPOCH, outro.getDuracao(), outro.getDificuldade(), outro.getreps(), outro.getpeso());
    }

    @Override
    public Atividade clone() {
        return new ElevacoesLaterais(this);
    }

    @Override
    public String toString() {
        return "ElevacoesLaterais{" +
                super.toString()+
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return super.equals(o);
        /*return Double.compare(ElevacoesLaterais.getRep(), getRep()) == 0
                && this.getTipo().equals(ElevacoesLaterais.getTipo());*/
    }

}