package Model;
import java.time.LocalDate;
//import java.time.temporal.ChronoUnit;

public class Agachamentos extends Reps{

    public Agachamentos(){
        super("", "Agachamentos", LocalDate.EPOCH, 0, Dificuldade.FACIL,0);
    }

    public Agachamentos(String codigo, String descricao, LocalDate data, int duracao, Dificuldade dificuldade, int reps) {
        super(codigo, descricao, data, duracao, dificuldade, reps);
    }

    public Agachamentos(Agachamentos outro) {
        super(outro.getCodigo(), outro.getDescricao(), LocalDate.EPOCH, outro.getDuracao(), outro.getDificuldade(), outro.getreps());
    }

    public double calcularCaloriasAgachamentos (Utilizador utilizador){
        double calorias = calorias(utilizador);
        return calorias*3/5;
    }

    @Override
    public Atividade clone() {
        return new Agachamentos(this);
    }

    @Override
    public String toString() {
        return "Agachamentos{" +
                super.toString()+
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return super.equals(o);
        /*return Double.compare(Agachamentos.getRep(), getRep()) == 0
                && this.getTipo().equals(Agachamentos.getTipo());*/
    }

}