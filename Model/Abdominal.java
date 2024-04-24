package Model;
import java.time.LocalDate;
//import java.time.temporal.ChronoUnit;

public class Abdominal extends Atividade{
    private double rep;
    private String tipo;

    public Abdominal(){
        super();
        this.rep = 0;
        this.tipo = "";
    }

    public Abdominal(String codigo, String descricao, LocalDate data, int duracao, double rep, String tipo) {
        super(codigo, descricao, data, duracao);
        this.rep = rep;
        this.tipo = tipo;
    }

    public Abdominal(Flexao outro) {
        super(outro);
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
        return new Flexao(this);
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
        Flexao Abdominal = (Flexao) o;
        return Double.compare(Abdominal.getRep(), getRep()) == 0
                && this.getTipo().equals(Abdominal.getTipo());
    }

}