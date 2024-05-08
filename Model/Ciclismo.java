package Model;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Ciclismo extends DistanciaeAltimetria{
    private double distancia;
    private double altimetria;
    private String percurso;

    public Ciclismo(){
        super("", "", LocalDate.EPOCH, 0);
        this.distancia = 0;
        this.altimetria = 0;
        this.percurso = "";
    }

    public Ciclismo(String codigo, String descricao, LocalDate data, int duracao, double distancia, double altimetria, String percurso) {
        super(codigo, descricao, data, duracao);
        this.distancia = distancia;
        this.altimetria = altimetria;
        this.percurso = percurso;
    }

    public Ciclismo(Ciclismo outro) {
        super(outro.getCodigo(), outro.getDescricao(), LocalDate.EPOCH, outro.getDuracao());
        this.distancia = outro.getDistancia();
        this.altimetria = outro.getAltimetria();
        this.percurso = outro.getPercurso();
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public double getAltimetria() {
        return altimetria;
    }

    public void setAltimetria(double altimetria) {
        this.altimetria = altimetria;
    }

    public String getPercurso() {
        return percurso;
    }

    public void setPercurso(String percurso) {
        this.percurso = percurso;
    }

    @Override
    public double calorias() {
        long idade =  ChronoUnit.YEARS.between(LocalDate.now(),
                getUser().getData_nascimento());
        double calorias =distancia*getUser().getPeso()*getDuracao()*idade/4;
        return calorias;
    }

    @Override
    public Atividade clone() {
        return new Ciclismo(this);
    }

    @Override
    public String toString() {
        return "Ciclismo{" +
                super.toString()+
                "distancia=" + distancia +
                ", altimetria=" + altimetria +
                ", percurso='" + percurso + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Ciclismo Ciclismo = (Ciclismo) o;
        return Double.compare(Ciclismo.getDistancia(), getDistancia()) == 0
                && Double.compare(Ciclismo.getAltimetria(), getAltimetria()) == 0
                && this.getPercurso().equals(Ciclismo.getPercurso());
    }    
}
