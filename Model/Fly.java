package Model;
import java.time.LocalDate;
//import java.time.temporal.ChronoUnit;

public class Fly extends RepsPesos{

    public Fly(){
        super("Fly", "Fly", LocalDate.EPOCH, 0, Dificuldade.MEDIO,0, 0);
    }

    public Fly(String codigo, String descricao, LocalDate data, int duracao, Dificuldade dificuldade, int reps, int peso) {
        super(codigo, descricao, data, duracao, dificuldade, reps, peso);
    }

    public Fly(Fly outro) {
        super(outro.getCodigo(), outro.getDescricao(), LocalDate.EPOCH, outro.getDuracao(), outro.getDificuldade(), outro.getreps(), outro.getpeso());
    }

    public double calcularCaloriasFly (Utilizador utilizador){
        double calorias = calorias(utilizador);
        return calorias*3/5;
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