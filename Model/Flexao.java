package Model;
import java.time.LocalDate;
//import java.time.temporal.ChronoUnit;

public class Flexao extends Reps{

    public Flexao(){
        super("Flexao", "Flexão", LocalDate.EPOCH, 0, Dificuldade.MEDIO,0);
    }

    public Flexao(String codigo, String descricao, LocalDate data, int duracao, Dificuldade dificuldade, int reps) {
        super(codigo, descricao, data, duracao, dificuldade, reps);
    }

    public Flexao(Flexao outro) {
        super(outro.getCodigo(), outro.getDescricao(), LocalDate.EPOCH, outro.getDuracao(), outro.getDificuldade(), outro.getreps());
    }

    @Override
    public Atividade clone() {
        return new Flexao(this);
    }

    @Override
    public String toString() {
        return "Flexao{" +
                super.toString()+
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return super.equals(o);
        /*return Double.compare(Flexao.getRep(), getRep()) == 0
                && this.getTipo().equals(Flexao.getTipo());*/
    }

}