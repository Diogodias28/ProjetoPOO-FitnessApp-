package Model;

import java.time.LocalDate;

class Utilizadorama extends Utilizador {
    public Utilizadorama(String morada, String email, String password, String username, Genero genero, double altura, double peso, LocalDate data_nascimento, String desporto_favorito, String tipo_atleta) {
        super(morada, email, password, username, genero, altura, peso, data_nascimento,desporto_favorito, tipo_atleta);
    }

    public Utilizadorama() {
        super("", "", "", "", Genero.Outro, 0, 0, LocalDate.EPOCH,"", "");
    }

    public Utilizadorama(Utilizadorama outro) {
        super(outro.getMorada(), outro.getEmail(), outro.getPassword(), outro.getusername(), outro.getGenero(), outro.getAltura(), outro.getPeso(), outro.getData_nascimento(), outro.getDesporto_favorito(), outro.getTipo_atleta());
    }

    @Override
    public double calcularFatorCalorias() {
        double fator = 0.0;
        for (Atividade atividade : getAtividades().values()) {
            fator += atividade.getDificuldade().getFator() * atividade.getDuracao() / getPeso();
        }
        return fator;
    }

    public Utilizador clone(){
        return new Utilizadorama(this);
    }
}