package Model;
import java.time.LocalDate;

public class BTT extends DistanciaeAltimetria{

    public BTT(){
        super("", "", LocalDate.EPOCH, 0, Dificuldade.DIFICIL, 0, 0);
    }

    public BTT(String codigo, String descricao, LocalDate data, int duracao, Dificuldade dificuldade, int distancia, int altimetria) {
        super(codigo, descricao, data, duracao, dificuldade, distancia, altimetria);
    }

    public BTT(BTT outro) {
        super(outro.getCodigo(), outro.getDescricao(), LocalDate.EPOCH, outro.getDuracao(), outro.getDificuldade(), outro.getdistancia(), outro.getaltimetria());
    }

    public double caloriasBTT(Utilizador utilizador) {
        double calorias = calorias(utilizador);
        return calorias * 8/5;
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
        return super.equals(0);
        /*if (!super.equals(o)) return false;
        BTT BTT = (BTT) o;
        return Double.compare(BTT.getRep(), getRep()) == 0
                && this.getTipo().equals(BTT.getTipo());*/
    }

}