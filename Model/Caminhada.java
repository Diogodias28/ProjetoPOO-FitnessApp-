package Model;
import java.time.LocalDate;

public class Caminhada extends DistanciaeAltimetria{

    public Caminhada(){
        super("", "", LocalDate.EPOCH, 0, Dificuldade.FACIL, 0, 0);
    }

    public Caminhada(String codigo, String descricao, LocalDate data, int duracao, Dificuldade dificuldade, int distancia, int altimetria) {
        super(codigo, descricao, data, duracao, dificuldade, distancia, altimetria);
    }

    public Caminhada(Caminhada outro) {
        super(outro.getCodigo(), outro.getDescricao(), LocalDate.EPOCH, outro.getDuracao(), outro.getDificuldade(), outro.getdistancia(), outro.getaltimetria());
    }

    public double caloriasCaminhada(Utilizador utilizador) {
        double calorias = calorias(utilizador);
        return calorias * 3/5;
    }

    @Override
    public Atividade clone() {
        return new Caminhada(this);
    }

    @Override
    public String toString() {
        return "Caminhada{" +
                super.toString()+
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return super.equals(0);
        /*if (!super.equals(o)) return false;
        Caminhada Caminhada = (Caminhada) o;
        return Double.compare(Caminhada.getRep(), getRep()) == 0
                && this.getTipo().equals(Caminhada.getTipo());*/
    }

}