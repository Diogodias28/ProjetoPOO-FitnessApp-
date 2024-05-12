package Model;
import java.time.LocalDate;

public class BTT extends DistanciaeAltimetria{

    public BTT(){
        super("BTT", "BTT", null, 30, Dificuldade.DIFICIL, 30, 120);
    }

    public BTT(String codigo, String descricao, LocalDate data, int duracao, Dificuldade dificuldade, int distancia, int altimetria) {
        super(codigo, descricao, data, duracao, dificuldade, distancia, altimetria);
    }

    public BTT(BTT outro) {
        super(outro.getCodigo(), outro.getDescricao(), outro.getData(), outro.getDuracao(), outro.getDificuldade(), outro.getdistancia(), outro.getaltimetria());
    }

    @Override
    public Atividade clone() {
        return new BTT(this);
    }

    @Override
    public String toString() {
        return "BTT{" +
                super.toString()+
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BTT other = (BTT) o;
        return super.equals(other);
    }

}