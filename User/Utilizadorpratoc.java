package User;

class Utilizadorpratoc extends Utilizador {
    public Utilizadorpratoc(int codigoUtilizador, String nome, String morada, String email, double frequenciaCardiacaMedia) {
        super(codigoUtilizador, nome, morada, email, frequenciaCardiacaMedia);
    }

    @Override
    public double calcularFatorCalorias() {
        double fator = getFrequenciaCardiacaMedia2() * getDuracao();
        return fator;
    }
}
