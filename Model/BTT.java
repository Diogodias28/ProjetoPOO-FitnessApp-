package Model;
import java.time.LocalDate;

public class BTT extends DistanciaeAltimetria{

    public BTT(){
        super("BTT", "BTT", LocalDate.EPOCH, 0, Dificuldade.DIFICIL, 0, 0);
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
        BTT other = (BTT) o;
        return super.equals(other) &&
        this.caloriasBTT(null) == other.caloriasBTT(null);
    }

}