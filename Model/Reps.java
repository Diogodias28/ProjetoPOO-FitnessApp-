package Model;

import java.time.LocalDate;

public class Reps extends Atividade {
    private int reps;

    public Reps(){
        super("", "", null, 30, Dificuldade.FACIL);
        this.reps = 10;
    }

    public Reps(String codigo, String descricao, LocalDate data, int duracao, Dificuldade dificuldade, int reps) {
        super(codigo, descricao, data, duracao, dificuldade);
        this.reps = reps;
    }

    public Reps(Reps outro) {
        super(outro.getCodigo(), outro.getDescricao(), outro.getData(), outro.getDuracao(), outro.getDificuldade());
        this.reps = outro.getreps();
    }

    public int getreps() {
        return reps;
    }

    public void setreps(int reps) {
        this.reps = reps;
    }

    @Override
    public double calorias(Utilizador utilizador) {
        double calorias = calcularCaloriasBase(utilizador);
        return calorias * getDificuldade().getFator();
    }

    private double calcularCaloriasBase(Utilizador utilizador) {
        if (utilizador instanceof Utilizadorpro) {
            return getFrequenciaCardiacaMedia() * reps / utilizador.getPeso();
        } else if (utilizador instanceof Utilizadorpratoc) {
            return getFrequenciaCardiacaMedia() * reps;
        } else {
            return getFrequenciaCardiacaMedia() * reps * utilizador.getPeso();
        }
    }

    public Atividade clone() {
        return new Reps(this);
    }
}