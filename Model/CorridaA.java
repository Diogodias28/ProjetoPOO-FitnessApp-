package Model;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
//import java.time.temporal.Temporal;

public class CorridaA extends AltaIntensidade implements DistanciaeAltimetria{
    private double distancia;
    private double altimetria;
    private String percurso;

    public CorridaA(){
        super("", "", LocalDate.EPOCH, 0);
        this.distancia = 0;
        this.altimetria = 0;
        this.percurso = "";
    }

    public CorridaA(String codigo, String descricao, LocalDate data, int duracao, double distancia, double altimetria, String percurso) {
        super(codigo, descricao, data, duracao);
        this.distancia = distancia;
        this.altimetria = altimetria;
        this.percurso = percurso;
    }

    public CorridaA(CorridaA outro) {
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
        double calorias =distancia*getUser().getPeso()*getDuracao()*idade/8;
        return calorias;
    }

    @Override
    public Atividade clone() {
        return new CorridaA(this);
    }

    @Override
    public String toString() {
        return "CorridaA{" +
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
        CorridaA CorridaA = (CorridaA) o;
        return Double.compare(CorridaA.getDistancia(), getDistancia()) == 0
                && Double.compare(CorridaA.getAltimetria(), getAltimetria()) == 0
                && this.getPercurso().equals(CorridaA.getPercurso());
    }

}


/*public interface AtividadeComDistanciaEAltimetria {
    double getDistancia();
    double getAltimetria();
    String getPercurso();
}

public class CorridaA extends AltaIntensidade implements AtividadeComDistanciaEAltimetria {
    private double distancia;
    private double altimetria;
    private String percurso;

    public CorridaA(String codigo, String descricao, LocalDate data, int duracao, double distancia, double altimetria, String percurso) {
        super(codigo, descricao, data, duracao);
        this.distancia = distancia;
        this.altimetria = altimetria;
        this.percurso = percurso;
    }

    // Implementação dos métodos da interface AtividadeComDistanciaEAltimetria

    @Override
    public double getDistancia() {
        return distancia;
    }

    @Override
    public double getAltimetria() {
        return altimetria;
    }

    @Override
    public String getPercurso() {
        return percurso;
    }

    // Implementação dos métodos abstratos ou adicionais de AltaIntensidade
}
 */