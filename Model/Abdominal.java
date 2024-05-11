package Model;
import java.time.LocalDate;
//import java.time.temporal.ChronoUnit;

public class Abdominal extends Reps{

    public Abdominal(){
        super("Abdominal", "Abdominal", LocalDate.EPOCH, 0, Dificuldade.FACIL,0);
    }

    public Abdominal(String codigo, String descricao, LocalDate data, int duracao, Dificuldade dificuldade, int reps) {
        super(codigo, descricao, data, duracao, dificuldade, reps);
    }

    public Abdominal(Abdominal outro) {
        super(outro.getCodigo(), outro.getDescricao(), LocalDate.EPOCH, outro.getDuracao(), outro.getDificuldade(), outro.getreps());
    }

    @Override
    public Atividade clone() {
        return new Abdominal(this);
    }

    @Override
    public String toString() {
        return "Abdominal{" +
                super.toString()+
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return super.equals(o);
        /*return Double.compare(Abdominal.getRep(), getRep()) == 0
                && this.getTipo().equals(Abdominal.getTipo());*/
    }

}