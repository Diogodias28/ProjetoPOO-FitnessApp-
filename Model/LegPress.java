package Model;
import java.time.LocalDate;
//import java.time.temporal.ChronoUnit;

public class LegPress extends RepsPesos{

    public LegPress(){
        super("LegPress", "Leg Press", null, 60, Dificuldade.MEDIO,10, 20);
    }

    public LegPress(String codigo, String descricao, LocalDate data, int duracao, Dificuldade dificuldade, int reps, int peso) {
        super(codigo, descricao, data, duracao, dificuldade, reps, peso);
    }

    public LegPress(LegPress outro) {
        super(outro.getCodigo(), outro.getDescricao(), outro.getData(), outro.getDuracao(), outro.getDificuldade(), outro.getreps(), outro.getpeso());
    }

    @Override
    public Atividade clone() {
        return new LegPress(this);
    }

    @Override
    public String toString() {
        return "LegPress{" +
                super.toString()+
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return super.equals(o);
        /*return Double.compare(LegPress.getRep(), getRep()) == 0
                && this.getTipo().equals(LegPress.getTipo());*/
    }

}