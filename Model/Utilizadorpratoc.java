package Model;

import java.time.LocalDate;

class Utilizadorpratoc extends Utilizador {
    public Utilizadorpratoc(String morada, String email, String password, String username, Genero genero, double altura, double peso, LocalDate data_nascimento, String desporto_favorito, String tipo_atleta) {
        super(morada, email, password, username, genero, altura, peso, data_nascimento,desporto_favorito, tipo_atleta);
    }

    @Override
    public double calcularFatorCalorias() {
        double fator = 0.0;
        for (Atividade atividade : getAtividades().values()) {
            fator += atividade.getDificuldade().getFator() * atividade.getDuracao();
        }
        return fator;
    }
}
