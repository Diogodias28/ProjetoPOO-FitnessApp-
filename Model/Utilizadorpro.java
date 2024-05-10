package Model;

import java.time.LocalDate;

class Utilizadorpro extends Utilizador {
    public Utilizadorpro(String morada, String email, String password, String username, Genero genero, double altura, double peso, LocalDate data_nascimento, String desporto_favorito, String tipo_atleta) {
        super(morada, email, password, username, genero, altura, peso, data_nascimento,desporto_favorito, tipo_atleta);
    }

    @Override
    public double calcularFatorCalorias() {
        double fator = getFrequenciaCardiacaMedia2() * getDuracao() * getPeso();
        return fator;
    }
}
