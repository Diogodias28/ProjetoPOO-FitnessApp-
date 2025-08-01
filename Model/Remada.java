package Model;
import java.time.LocalDate;
//import java.time.temporal.ChronoUnit;

public class Remada extends RepsPesos{

    public Remada(){
        super("Remada", "Remada", null, 60, Dificuldade.MEDIO,10, 20);
    }

    public Remada(String codigo, String descricao, LocalDate data, int duracao, Dificuldade dificuldade, int reps, int peso) {
        super(codigo, descricao, data, duracao, dificuldade, reps, peso);
    }

    public Remada(Remada outro) {
        super(outro.getCodigo(), outro.getDescricao(), outro.getData(), outro.getDuracao(), outro.getDificuldade(), outro.getreps(), outro.getpeso());
    }
    
    @Override
    public Atividade clone() {
        return new Remada(this);
    }

    @Override
    public String toString() {
        return "Remada{" +
                super.toString()+
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return super.equals(o);
        /*return Double.compare(Remada.getRep(), getRep()) == 0
                && this.getTipo().equals(Remada.getTipo());*/
    }

}