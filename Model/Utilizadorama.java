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

    public Utilizador clone(){
        return new Utilizadorama(this);
    }
}