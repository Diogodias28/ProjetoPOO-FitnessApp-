package Model;
import java.time.LocalDate;
//import java.time.temporal.ChronoUnit;

public class Fly extends RepsPesos{

    public Fly(){
        super("Fly", "Fly", null, 30, Dificuldade.MEDIO,10, 20);
    }

    public Fly(String codigo, String descricao, LocalDate data, int duracao, Dificuldade dificuldade, int reps, int peso) {
        super(codigo, descricao, data, duracao, dificuldade, reps, peso);
    }

    public Fly(Fly outro) {
        super(outro.getCodigo(), outro.getDescricao(), outro.getData(), outro.getDuracao(), outro.getDificuldade(), outro.getreps(), outro.getpeso());
    }

    @Override
    public Atividade clone() {
        return new Fly(this);
    }

    @Override
    public String toString() {
        return "Fly{" +
                super.toString()+
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return super.equals(o);
        /*return Double.compare(Fly.getRep(), getRep()) == 0
                && this.getTipo().equals(Fly.getTipo());*/
    }

}