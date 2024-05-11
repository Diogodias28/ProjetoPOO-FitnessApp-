package Model;
import java.time.LocalDate;

public class Trail extends DistanciaeAltimetria{

    public Trail(){
        super("Trail", "Trail", LocalDate.EPOCH, 0, Dificuldade.DIFICIL, 0, 0);
    }

    public Trail(String codigo, String descricao, LocalDate data, int duracao, Dificuldade dificuldade, int distancia, int altimetria) {
        super(codigo, descricao, data, duracao, dificuldade, distancia, altimetria);
    }

    public Trail(Trail outro) {
        super(outro.getCodigo(), outro.getDescricao(), LocalDate.EPOCH, outro.getDuracao(), outro.getDificuldade(), outro.getdistancia(), outro.getaltimetria());
    }

    @Override
    public Atividade clone() {
        return new Trail(this);
    }

    @Override
    public String toString() {
        return "Trail{" +
                super.toString()+
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return super.equals(0);
        /*if (!super.equals(o)) return false;
        Trail Trail = (Trail) o;
        return Double.compare(Trail.getRep(), getRep()) == 0
                && this.getTipo().equals(Trail.getTipo());*/
    }

}