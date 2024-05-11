package Model;
import java.time.LocalDate;
//import java.time.temporal.ChronoUnit;

public class Burpees extends Reps{

    public Burpees(){
        super("Burpees", "Burpees", LocalDate.EPOCH, 0, Dificuldade.DIFICIL,0);
    }

    public Burpees(String codigo, String descricao, LocalDate data, int duracao, Dificuldade dificuldade, int reps) {
        super(codigo, descricao, data, duracao, dificuldade, reps);
    }

    public Burpees(Burpees outro) {
        super(outro.getCodigo(), outro.getDescricao(), LocalDate.EPOCH, outro.getDuracao(), outro.getDificuldade(), outro.getreps());
    }

    @Override
    public Atividade clone() {
        return new Burpees(this);
    }

    @Override
    public String toString() {
        return "Burpees{" +
                super.toString()+
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return super.equals(o);
        /*return Double.compare(Burpees.getRep(), getRep()) == 0
                && this.getTipo().equals(Burpees.getTipo());*/
    }

}