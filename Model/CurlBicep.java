package Model;
import java.time.LocalDate;
//import java.time.temporal.ChronoUnit;

public class CurlBicep extends RepsPesos{

    public CurlBicep(){
        super("CurlBicep", "Selecione um peso e deixe os braços relaxarem ao lado do corpo, com as palmas voltadas para a frente. Dobre os cotovelos e levante os pesos", LocalDate.EPOCH, 0, Dificuldade.FACIL,0, 0);
    }

    public CurlBicep(String codigo, String descricao, LocalDate data, int duracao, Dificuldade dificuldade, int reps, int peso) {
        super(codigo, descricao, data, duracao, dificuldade, reps, peso);
    }

    public CurlBicep(CurlBicep outro) {
        super(outro.getCodigo(), outro.getDescricao(), LocalDate.EPOCH, outro.getDuracao(), outro.getDificuldade(), outro.getreps(), outro.getpeso());
    }

    public double calcularCaloriasCurlBicep (Utilizador utilizador){
        double calorias = calorias(utilizador);
        return calorias*3/5;
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