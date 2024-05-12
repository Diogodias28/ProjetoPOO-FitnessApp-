package Model;
import java.time.LocalDate;

public class Corrida extends DistanciaeAltimetria{

    public Corrida(){
        super("Corrida", "Corrida", null, 30, Dificuldade.MEDIO, 30, 120);
    }

    public Corrida(String codigo, String descricao, LocalDate data, int duracao, Dificuldade dificuldade, int distancia, int altimetria) {
        super(codigo, descricao, data, duracao, dificuldade, distancia, altimetria);
    }

    public Corrida(Corrida outro) {
        super(outro.getCodigo(), outro.getDescricao(), outro.getData(), outro.getDuracao(), outro.getDificuldade(), outro.getdistancia(), outro.getaltimetria());
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