package Model;

import java.time.LocalDate;
import java.util.Map;

class Utilizadorama extends Utilizador {
    public Utilizadorama(int codigoUtilizador, String morada, String email, String password, String nome, Genero genero, double altura, double peso, LocalDate data_nascimento, String desporto_favorito, String tipo_atleta, Map<String, Atividade> atividades, double frequenciaCardiacaMedia) {
        super(codigoUtilizador, morada, email, password, nome, genero, altura, peso, data_nascimento,desporto_favorito, tipo_atleta, frequenciaCardiacaMedia);
    }

    @Override
    public double calcularFatorCalorias() {
        double fator = getFrequenciaCardiacaMedia2() * getDuracao() / getPeso();
        return fator;
    }
}
