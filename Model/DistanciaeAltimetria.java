package Model;

import java.time.LocalDate;

public class DistanciaeAltimetria extends Atividade {
    private double distancia;
    private int altimetria;

    public DistanciaeAltimetria(){
        super("", "", LocalDate.EPOCH, 0, Dificuldade.FACIL);
        this.distancia = 0;
        this.altimetria = 0;
    }

    public DistanciaeAltimetria(String codigo, String descricao, LocalDate data, int duracao, Dificuldade dificuldade, double distancia, int altimetria) {
        super(codigo, descricao, data, duracao, dificuldade);
        this.distancia = distancia;
        this.altimetria = altimetria;
    }

    public DistanciaeAltimetria(DistanciaeAltimetria outro) {
        super(outro.getCodigo(), outro.getDescricao(), outro.getData(), outro.getDuracao(), outro.getDificuldade());
        this.distancia = outro.getdistancia();
        this.altimetria = outro.getaltimetria();
    }

    public double getdistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public int getaltimetria() {
        return altimetria;
    }

    public void setAltimetria(int altimetria) {
        this.altimetria = altimetria;
    }

    @Override
    public double calorias(Utilizador utilizador) {
        double calorias = calcularCaloriasBase(utilizador);
        double fatorFreq = calcularFatorFreqCardiaca(utilizador);
        return calorias * getDificuldade().getFator() * fatorFreq;
    }

    private double calcularCaloriasBase(Utilizador utilizador) {
        
        double calorias = distancia * altimetria *getDuracao() * (utilizador.getPeso()/10);

        return calorias;
    }

    private double calcularFatorFreqCardiaca(Utilizador utilizador){
        double fator = utilizador.getFrequenciaCardiacaMedia();
        return fator;
    }

    public Atividade clone() {
        return new DistanciaeAltimetria(this);
    }
}