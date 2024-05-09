package Model;

import java.time.LocalDate;

public class Reps extends Atividade {
    private int reps;

    public Reps(){
        super("", "", LocalDate.EPOCH, 0, Dificuldade.FACIL);
        this.reps = 0;
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
        double fatorFreq = calcularFatorFreqCardiaca(utilizador);
        return calorias * getDificuldade().getFator() * fatorFreq;
    }

    private double calcularCaloriasBase(Utilizador utilizador) {
        
        double calorias = reps *getDuracao() * (utilizador.getPeso()/10);

        return calorias;
    }

    private double calcularFatorFreqCardiaca(Utilizador utilizador){
        double fator = utilizador.getFrequenciaCardiacaMedia();
        return fator;
    }

    public Atividade clone() {
        return new Reps(this);
    }
}