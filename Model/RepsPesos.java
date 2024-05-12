package Model;

import java.time.LocalDate;

public class RepsPesos extends Atividade {
    private int reps;
    private int peso;

    public RepsPesos(){
        super("", "", null, 0, Dificuldade.FACIL);
        this.reps = 0;
    }

    public RepsPesos(String codigo, String descricao, LocalDate data, int duracao, Dificuldade dificuldade, int reps, int peso) {
        super(codigo, descricao, data, duracao, dificuldade);
        this.reps = reps;
        this.peso = peso;
    }

    public RepsPesos(RepsPesos outro) {
        super(outro.getCodigo(), outro.getDescricao(), outro.getData(), outro.getDuracao(), outro.getDificuldade());
        this.reps= outro.getreps();
        this.peso = outro.getpeso();
    }

    public int getreps() {
        return reps;
    }

    public void setreps(int reps) {
        this.reps = reps;
    }

    public int getpeso() {
        return peso;
    }

    public void setpeso(int peso) {
        this.peso = peso;
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
        return new RepsPesos(this);
    }
}