package Model;
import java.time.LocalDate;
//import java.time.temporal.ChronoUnit;

public class Abdominal extends BaixaIntensidade implements Reps{
    private double rep;
    private String tipo;

    public Abdominal(){
        super("", "", LocalDate.EPOCH, 0);
        this.rep = 0;
        this.tipo = "";
    }

    public Abdominal(String codigo, String descricao, LocalDate data, int duracao, double rep, String tipo) {
        super(codigo, descricao, data, duracao);
        this.rep = rep;
        this.tipo = tipo;
    }

    public Abdominal(Abdominal outro) {
        super(outro.getCodigo(), outro.getDescricao(), LocalDate.EPOCH, outro.getDuracao());
        this.rep = outro.getRep();
        this.tipo = outro.getTipo();
    }

    public double getRep() {
        return rep;
    }

    public void setRep(Double rep) {
        this.rep = rep;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public double calorias() {
        double calorias = getRep()*getDuracao()*3/5;
        return calorias;
    }

    @Override
    public Atividade clone() {
        return new Abdominal(this);
    }

    @Override
    public String toString() {
        return "Abdominal{" +
                super.toString()+
                "rep=" + rep +
                ", tipo='" + tipo + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Abdominal Abdominal = (Abdominal) o;
        return Double.compare(Abdominal.getRep(), getRep()) == 0
                && this.getTipo().equals(Abdominal.getTipo());
    }

}