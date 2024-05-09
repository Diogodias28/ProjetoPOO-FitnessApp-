package Model;

import java.time.LocalDate;

public class RepsPesos extends Atividade {
    private int reps;
    private int peso;

    public RepsPesos(){
        super("", "", LocalDate.EPOCH, 0, Dificuldade.FACIL);
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
        double fatorFreq = calcularFatorFreqCardiaca(utilizador);
        return calorias * getDificuldade().getFator() * fatorFreq;
    }

    private double calcularCaloriasBase(Utilizador utilizador) {
        
        double calorias = reps * peso *getDuracao() * (utilizador.getPeso()/10);

        return calorias;
    }

    private double calcularFatorFreqCardiaca(Utilizador utilizador){
        double fator = utilizador.getFrequenciaCardiacaMedia();
        return fator;
    }

    public Atividade clone() {
        return new RepsPesos(this);
    }
}