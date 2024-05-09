package Model;
import java.time.LocalDate;
//import java.time.temporal.ChronoUnit;

public class PesoLivre extends RepsPesos{

    public PesoLivre(){
        super("", "", LocalDate.EPOCH, 0, Dificuldade.DIFICIL,0, 0);
    }

    public PesoLivre(String codigo, String descricao, LocalDate data, int duracao, Dificuldade dificuldade, int reps, int peso) {
        super(codigo, descricao, data, duracao, dificuldade, reps, peso);
    }

    public PesoLivre(PesoLivre outro) {
        super(outro.getCodigo(), outro.getDescricao(), LocalDate.EPOCH, outro.getDuracao(), outro.getDificuldade(), outro.getreps(), outro.getpeso());
    }

    public double calcularCaloriasPesoLivre (Utilizador utilizador){
        double calorias = calorias(utilizador);
        return calorias;
    }

    @Override
    public Atividade clone() {
        return new PesoLivre(this);
    }

    @Override
    public String toString() {
        return "PesoLivre{" +
                super.toString()+
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return super.equals(o);
        /*return Double.compare(PesoLivre.getRep(), getRep()) == 0
                && this.getTipo().equals(PesoLivre.getTipo());*/
    }

}