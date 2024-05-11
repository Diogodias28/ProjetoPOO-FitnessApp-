package Model;
import java.time.LocalDate;
//import java.time.temporal.ChronoUnit;

public class Prancha extends Reps{

    public Prancha(){
        super("Prancha", "Prancha", LocalDate.EPOCH, 0, Dificuldade.MEDIO,0);
    }

    public Prancha(String codigo, String descricao, LocalDate data, int duracao, Dificuldade dificuldade, int reps) {
        super(codigo, descricao, data, duracao, dificuldade, reps);
    }

    public Prancha(Prancha outro) {
        super(outro.getCodigo(), outro.getDescricao(), LocalDate.EPOCH, outro.getDuracao(), outro.getDificuldade(), outro.getreps());
    }

    @Override
    public Atividade clone() {
        return new Prancha(this);
    }

    @Override
    public String toString() {
        return "Prancha{" +
                super.toString()+
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return super.equals(o);
        /*return Double.compare(Prancha.getRep(), getRep()) == 0
                && this.getTipo().equals(Prancha.getTipo());*/
    }

}