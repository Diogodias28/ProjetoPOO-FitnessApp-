package Model;
import java.time.LocalDate;

public class Natacao extends Distancia{
    private Estilo estilo;

    public Natacao(){
        super("Natacao","Natação", null, 60, Dificuldade.FACIL, 30);
        this.estilo = Estilo.COSTAS;
    }

    public Natacao(String codigo, String descricao, LocalDate data, int duracao, Dificuldade dificuldade, int distancia, Estilo estilo) {
        super(codigo, descricao, data, duracao, dificuldade, distancia);
        this.estilo = estilo;
    }

    public Natacao(Natacao outro) {
        super(outro.getCodigo(), outro.getDescricao(), outro.getData(), outro.getDuracao(), outro.getDificuldade(), outro.getdistancia());
        this.estilo = outro.getestilo();
    }

    public Estilo getestilo() {
        return estilo;
    }

    public void setestilo(Estilo estilo) {
        this.estilo = estilo;
    }

    public double caloriasNatacao(Utilizador utilizador) {
        double calorias = calorias(utilizador);
        return calorias*getestilo().getFator();
    }

    @Override
    public Atividade clone() {
        return new Natacao(this);
    }

    @Override
    public String toString() {
        return "Natacao{" +
                super.toString()+
                ", estilo='" + estilo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Natacao Natacao = (Natacao) o;
        return this.getestilo().equals(Natacao.getestilo());
    }

}