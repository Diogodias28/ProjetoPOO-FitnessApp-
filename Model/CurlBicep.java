package Model;
import java.time.LocalDate;
//import java.time.temporal.ChronoUnit;

public class CurlBicep extends RepsPesos{

    public CurlBicep(){
        super("CurlBicep", "Curl Bicep", null, 30, Dificuldade.FACIL,10, 20);
    }

    public CurlBicep(String codigo, String descricao, LocalDate data, int duracao, Dificuldade dificuldade, int reps, int peso) {
        super(codigo, descricao, data, duracao, dificuldade, reps, peso);
    }

    public CurlBicep(CurlBicep outro) {
        super(outro.getCodigo(), outro.getDescricao(), outro.getData(), outro.getDuracao(), outro.getDificuldade(), outro.getreps(), outro.getpeso());
    }

    @Override
    public Atividade clone() {
        return new CurlBicep(this);
    }

    @Override
    public String toString() {
        return "CurlBicep{" +
                super.toString()+
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return super.equals(o);
        /*return Double.compare(CurlBicep.getRep(), getRep()) == 0
                && this.getTipo().equals(CurlBicep.getTipo());*/
    }

}