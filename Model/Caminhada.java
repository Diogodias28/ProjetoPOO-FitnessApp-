package Model;
import java.time.LocalDate;

public class Caminhada extends DistanciaeAltimetria{

    public Caminhada(){
        super("Caminhada", "Caminhada", null, 60, Dificuldade.FACIL, 30, 120);
    }

    public Caminhada(String codigo, String descricao, LocalDate data, int duracao, Dificuldade dificuldade, int distancia, int altimetria) {
        super(codigo, descricao, data, duracao, dificuldade, distancia, altimetria);
    }

    public Caminhada(Caminhada outro) {
        super(outro.getCodigo(), outro.getDescricao(), outro.getData(), outro.getDuracao(), outro.getDificuldade(), outro.getdistancia(), outro.getaltimetria());
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