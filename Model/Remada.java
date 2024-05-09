package Model;
import java.time.LocalDate;
//import java.time.temporal.ChronoUnit;

public class Remada extends RepsPesos{

    public Remada(){
        super("Remada", "De pé, afastar os pés na mesma largura do quadril, flexionar levemente os joelhos e inclinar um pouco o tronco para frente. Em seguida, segurar a barra com as mãos a uma distância um pouco afastada da largura dos ombros. Flexione os cotovelos e traga a barra em direção ao abdômen.", LocalDate.EPOCH, 0, Dificuldade.MEDIO,0, 0);
    }

    public Remada(String codigo, String descricao, LocalDate data, int duracao, Dificuldade dificuldade, int reps, int peso) {
        super(codigo, descricao, data, duracao, dificuldade, reps, peso);
    }

    public Remada(Remada outro) {
        super(outro.getCodigo(), outro.getDescricao(), LocalDate.EPOCH, outro.getDuracao(), outro.getDificuldade(), outro.getreps(), outro.getpeso());
    }

    public double calcularCaloriasRemada (Utilizador utilizador){
        double calorias = calorias(utilizador);
        return calorias*3/5;
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