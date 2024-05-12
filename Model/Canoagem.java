package Model;
import java.time.LocalDate;

public class Canoagem extends Distancia{
    private double velvento;
    private double nvoltas;
    public Canoagem(){
        super("Canoagem","Canoagem", null, 30, Dificuldade.DIFICIL, 30);
    }

    public Canoagem(String codigo, String descricao, LocalDate data, int duracao, Dificuldade dificuldade, int distancia) {
        super(codigo, descricao, data, duracao, dificuldade, distancia);
    }

    public Canoagem(Canoagem outro) {
        super(outro.getCodigo(), outro.getDescricao(), outro.getData(), outro.getDuracao(), outro.getDificuldade(), outro.getdistancia());
    }

    @Override
    public Atividade clone() {
        return new Canoagem(this);
    }

    @Override
    public String toString() {
        return "Canoagem{" +
                super.toString()+
                ", velvento=" + velvento +
                ", nvoltas=" + nvoltas +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return super.equals(o);
    }

}