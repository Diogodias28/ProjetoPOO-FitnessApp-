package Model;

import java.time.LocalDate;

public class Distancia extends Atividade {
    private double distancia;

    public Distancia(){
        super("", "", null, 60, Dificuldade.FACIL);
        this.distancia = 30;
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
        double calorias = calcularCaloriasBase(utilizador) / 10;
        return calorias * getDificuldade().getFator() * (distancia/100);
    }

    public double calcularCaloriasBase(Utilizador utilizador) {
        if (utilizador instanceof Utilizadorpro) {
            return (getFrequenciaCardiacaMedia()/80) * getDuracao() / utilizador.getPeso();
        } else if (utilizador instanceof Utilizadorpratoc) {
            return (getFrequenciaCardiacaMedia()/80) * getDuracao() * (utilizador.getPeso()/50);
        } else {
            return (getFrequenciaCardiacaMedia()/80) * getDuracao();
        }
    }

    public Atividade clone() {
        return new Distancia(this);
    }
}