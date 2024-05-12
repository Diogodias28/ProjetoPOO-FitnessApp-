package Model;

import java.time.LocalDate;

public class RepsPesos extends Atividade {
    private int reps;
    private int peso;

    public RepsPesos(){
        super("", "", null, 60, Dificuldade.FACIL);
        this.reps = 10;
        this.peso = 20;
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
        return calorias * getDificuldade().getFator() * (peso/10);
    }

    public double calcularCaloriasBase(Utilizador utilizador) {
        if (utilizador instanceof Utilizadorpro) {
            return getFrequenciaCardiacaMedia() * reps / utilizador.getPeso();
        } else if (utilizador instanceof Utilizadorpratoc) {
            return getFrequenciaCardiacaMedia() * reps * (utilizador.getPeso()/50);
        } else {
            return getFrequenciaCardiacaMedia() * reps;
        }
    }

    public Atividade clone() {
        return new RepsPesos(this);
    }
}