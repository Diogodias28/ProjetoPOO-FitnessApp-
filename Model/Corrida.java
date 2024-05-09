package Model;
import java.time.LocalDate;

public class Corrida extends DistanciaeAltimetria{

    public Corrida(){
        super("", "", LocalDate.EPOCH, 0, Dificuldade.MEDIO, 0, 0);
    }

    public Corrida(String codigo, String descricao, LocalDate data, int duracao, Dificuldade dificuldade, int distancia, int altimetria) {
        super(codigo, descricao, data, duracao, dificuldade, distancia, altimetria);
    }

    public Corrida(Corrida outro) {
        super(outro.getCodigo(), outro.getDescricao(), LocalDate.EPOCH, outro.getDuracao(), outro.getDificuldade(), outro.getdistancia(), outro.getaltimetria());
    }

    public double caloriasCorrida(Utilizador utilizador) {
        double calorias = calorias(utilizador);
        return calorias * 6/5;
    }

    @Override
    public Atividade clone() {
        return new Corrida(this);
    }

    @Override
    public String toString() {
        return "Corrida{" +
                super.toString()+
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return super.equals(0);
        /*if (!super.equals(o)) return false;
        Corrida Corrida = (Corrida) o;
        return Double.compare(Corrida.getRep(), getRep()) == 0
                && this.getTipo().equals(Corrida.getTipo());*/
    }

}