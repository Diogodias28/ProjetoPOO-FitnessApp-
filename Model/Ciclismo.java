package Model;
import java.time.LocalDate;

public class Ciclismo extends DistanciaeAltimetria{

    public Ciclismo(){
        super("Ciclismo", "Andar de bicicleta em terreno não muito agressivo", LocalDate.EPOCH, 0, Dificuldade.MEDIO, 0, 0);
    }

    public Ciclismo(String codigo, String descricao, LocalDate data, int duracao, Dificuldade dificuldade, int distancia, int altimetria) {
        super(codigo, descricao, data, duracao, dificuldade, distancia, altimetria);
    }

    public Ciclismo(Ciclismo outro) {
        super(outro.getCodigo(), outro.getDescricao(), LocalDate.EPOCH, outro.getDuracao(), outro.getDificuldade(), outro.getdistancia(), outro.getaltimetria());
    }

    public double caloriasCiclismo(Utilizador utilizador) {
        double calorias = calorias(utilizador);
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
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return super.equals(0);
        /*if (!super.equals(o)) return false;
        Ciclismo Ciclismo = (Ciclismo) o;
        return Double.compare(Ciclismo.getRep(), getRep()) == 0
                && this.getTipo().equals(Ciclismo.getTipo());*/
    }

}