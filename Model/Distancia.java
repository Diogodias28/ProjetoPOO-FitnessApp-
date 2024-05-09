package Model;

import java.time.LocalDate;

public class Distancia extends Atividade {
    private double distancia;

    public Distancia(){
        super("", "", LocalDate.EPOCH, 0, Dificuldade.FACIL);
        this.distancia = 0;
    }

    public Distancia(String codigo, String descricao, LocalDate data, int duracao, Dificuldade dificuldade, double distancia) {
        super(codigo, descricao, data, duracao, dificuldade);
        this.distancia = distancia;
    }

    public Distancia(Distancia outro) {
        super(outro.getCodigo(), outro.getDescricao(), outro.getData(), outro.getDuracao(), outro.getDificuldade());
        this.distancia = outro.getdistancia();
    }

    public double getdistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    @Override
    public double calorias(Utilizador utilizador) {
        double calorias = calcularCaloriasBase(utilizador);
        double fatorFreq = calcularFatorFreqCardiaca(utilizador);
        return calorias * getDificuldade().getFator() * fatorFreq;
    }

    private double calcularCaloriasBase(Utilizador utilizador) {
        
        double calorias = distancia *getDuracao() * (utilizador.getPeso()/10);

        return calorias;
    }

    private double calcularFatorFreqCardiaca(Utilizador utilizador){
        double fator = utilizador.getFrequenciaCardiacaMedia();
        return fator;
    }

    public Atividade clone() {
        return new Distancia(this);
    }
}