package Model;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Canoagem extends Distancia{
    private double velvento;
    private double nvoltas;
    public Canoagem(){
        super("Canoagem","Canoagem", null, 30, Dificuldade.DIFICIL, 30);
        this.velvento = 20;
        this.nvoltas = 10;
    }

    public Canoagem(String codigo, String descricao, LocalDate data, int duracao, Dificuldade dificuldade, int distancia, double velvento, double nvoltas) {
        super(codigo, descricao, data, duracao, dificuldade, distancia);
        this.velvento = velvento;
        this.nvoltas = nvoltas;
    }

    public Canoagem(Canoagem outro) {
        super(outro.getCodigo(), outro.getDescricao(), outro.getData(), outro.getDuracao(), outro.getDificuldade(), outro.getdistancia());
        this.velvento = outro.getVelvento();
        this.nvoltas = outro.getNvoltas();
    }

    public double getVelvento() {
        return velvento;
    }

    public void setVelvento(double velvento) {
        this.velvento = velvento;
    }

    public double getNvoltas() {
        return nvoltas;
    }

    public void setNvoltas(double nvoltas) {
        this.nvoltas = nvoltas;
    }

    public double caloriasCanoagem(Utilizador utilizador) {
        long idade =  ChronoUnit.YEARS.between(LocalDate.now(),getUser().getData_nascimento());
        double calorias = calorias(utilizador);
        return calorias*(getVelvento()/100)*idade/4;
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
        if (!super.equals(o)) return false;
        Canoagem Canoagem = (Canoagem) o;
        return Double.compare(Canoagem.getVelvento(), getVelvento()) == 0
                && Double.compare(Canoagem.getNvoltas(), getNvoltas()) == 0;
    }

}