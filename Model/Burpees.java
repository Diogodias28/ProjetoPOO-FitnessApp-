package Model;
import java.time.LocalDate;
//import java.time.temporal.ChronoUnit;

public class Burpees extends Reps{

    public Burpees(){
        super("Burpees", "Burpees", null, 30, Dificuldade.DIFICIL,10);
    }

    public Burpees(String codigo, String descricao, LocalDate data, int duracao, Dificuldade dificuldade, int reps) {
        super(codigo, descricao, data, duracao, dificuldade, reps);
    }

    public Burpees(Burpees outro) {
        super(outro.getCodigo(), outro.getDescricao(), outro.getData(), outro.getDuracao(), outro.getDificuldade(), outro.getreps());
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