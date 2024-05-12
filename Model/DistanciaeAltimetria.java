package Model;

import java.time.LocalDate;

public class DistanciaeAltimetria extends Atividade {
    private double distancia;
    private int altimetria;

    public DistanciaeAltimetria(){
        super("", "", null, 30, Dificuldade.FACIL);
        this.distancia = 30;
        this.altimetria = 120;
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
        return calorias * getDificuldade().getFator() * distancia * (altimetria/80);
    }

    public double calcularCaloriasBase(Utilizador utilizador) {
        if (utilizador instanceof Utilizadorpro) {
            return (getFrequenciaCardiacaMedia()/80) * (getDuracao()/60) / utilizador.getPeso();
        } else if (utilizador instanceof Utilizadorpratoc) {
            return (getFrequenciaCardiacaMedia()/80) * (getDuracao()/60) * (utilizador.getPeso()/50);
        } else {
            return (getFrequenciaCardiacaMedia()/80) * (getDuracao()/60);
        }
    }
    
    public Atividade clone() {
        return new DistanciaeAltimetria(this);
    }
}