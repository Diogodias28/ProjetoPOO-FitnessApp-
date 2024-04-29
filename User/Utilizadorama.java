package User;

class Utilizadorama extends Utilizador {
    public Utilizadorama(int codigoUtilizador, String nome, String morada, String email, double frequenciaCardiacaMedia) {
        super(codigoUtilizador, nome, morada, email, frequenciaCardiacaMedia);
    }

    @Override
    public double calcularFatorCalorias() {
        double fator = getFrequenciaCardiacaMedia2() * getDuracao() / getPeso();
        return fator;
    }
}
