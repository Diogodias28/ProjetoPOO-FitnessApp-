package Model;
import java.time.LocalDate;
//import java.time.temporal.ChronoUnit;

public class LegPress extends RepsPesos{

    public LegPress(){
        super("LegPress", "Regule o peso, sente-se na máquina, coloque o pé na base e ajuste o banco de modo que os joelhos formem um ângulo de 90 graus. Faça força para empurrar o peso com as pernas e pare antes de esticá-las, repetindo o movimento.", LocalDate.EPOCH, 0, Dificuldade.MEDIO,0, 0);
    }

    public LegPress(String codigo, String descricao, LocalDate data, int duracao, Dificuldade dificuldade, int reps, int peso) {
        super(codigo, descricao, data, duracao, dificuldade, reps, peso);
    }

    public LegPress(LegPress outro) {
        super(outro.getCodigo(), outro.getDescricao(), LocalDate.EPOCH, outro.getDuracao(), outro.getDificuldade(), outro.getreps(), outro.getpeso());
    }

    public double calcularCaloriasLegPress (Utilizador utilizador){
        double calorias = calorias(utilizador);
        return calorias;
    }

    @Override
    public Atividade clone() {
        return new LegPress(this);
    }

    @Override
    public String toString() {
        return "LegPress{" +
                super.toString()+
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return super.equals(o);
        /*return Double.compare(LegPress.getRep(), getRep()) == 0
                && this.getTipo().equals(LegPress.getTipo());*/
    }

}