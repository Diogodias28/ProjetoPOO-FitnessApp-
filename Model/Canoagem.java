package Model;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Canoagem extends AltaIntensidade{
    private double direcao;
    private double velvento;
    private double distancia;
    private double nvoltas;
    private String embarcacao;

    public Canoagem(){
        super("","", LocalDate.EPOCH, 0);
        this.direcao = 0;
        this.velvento = 0;
        this.distancia = 0;
        this.nvoltas = 0;
        this.embarcacao = "";
    }

    public Canoagem(String codigo, String descricao, LocalDate data, int duracao, double direcao, double velvento, double distancia, double nvoltas, String embarcacao) {
        super(codigo, descricao, data, duracao);
        this.direcao = direcao;
        this.velvento = velvento;
        this.distancia = distancia;
        this.nvoltas = nvoltas;
        this.embarcacao = embarcacao;
    }

    public Canoagem(Canoagem outro) {
        super(outro.getCodigo(), outro.getDescricao(), LocalDate.EPOCH, outro.getDuracao());
        this.direcao = outro.getDirecao();
        this.velvento = outro.getVelvento();
        this.distancia = outro.getDistancia();
        this.nvoltas = outro.getNvoltas();
        this.embarcacao = outro.getEmbarcacao();
    }

    public double getDirecao() {
        return direcao;
    }

    public void setDirecao(Double direcao) {
        this.direcao = direcao;
    }

    public double getVelvento() {
        return velvento;
    }

    public void setVelvento(double velvento) {
        this.velvento = velvento;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public double getNvoltas() {
        return nvoltas;
    }

    public void setNvoltas(double nvoltas) {
        this.nvoltas = nvoltas;
    }

    public String getEmbarcacao() {
        return embarcacao;
    }

    public void setEmbarcacao(String embarcacao) {
        this.embarcacao = embarcacao;
    }

    @Override
    public double calorias() {
        long idade =  ChronoUnit.YEARS.between(LocalDate.now(),
                getUser().getData_nascimento());
        double calorias =distancia*getDirecao()*getDuracao()*idade/4;
        return calorias;
    }

    @Override
    public Atividade clone() {
        return new Canoagem(this);
    }

    @Override
    public String toString() {
        return "Canoagem{" +
                super.toString()+
                "direcao=" + direcao +
                ", velvento=" + velvento +
                ", distancia=" + distancia +
                ", nvoltas=" + nvoltas +
                ", embarcacao='" + embarcacao + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Canoagem Canoagem = (Canoagem) o;
        return Double.compare(Canoagem.getDirecao(), getDirecao()) == 0
                && Double.compare(Canoagem.getVelvento(), getVelvento()) == 0
                && Double.compare(Canoagem.getDistancia(), getDistancia()) == 0
                && Double.compare(Canoagem.getNvoltas(), getNvoltas()) == 0
                && this.getEmbarcacao().equals(Canoagem.getEmbarcacao());
    }

}