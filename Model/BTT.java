package Model;
import java.time.LocalDate;

public class BTT extends DistanciaeAltimetria{ //temos de substituir cenas
    private double rep;
    private String tipo;

    public BTT(){
        super("", "", LocalDate.EPOCH, 0);
        this.rep = 0;
        this.tipo = "";
    }

    public BTT(String codigo, String descricao, LocalDate data, int duracao, double rep, String tipo) {
        super(codigo, descricao, data, duracao);
        this.rep = rep;
        this.tipo = tipo;
    }

    public BTT(BTT outro) {
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
        return new BTT(this);
    }

    @Override
    public String toString() {
        return "BTT{" +
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
        BTT BTT = (BTT) o;
        return Double.compare(BTT.getRep(), getRep()) == 0
                && this.getTipo().equals(BTT.getTipo());
    }

}