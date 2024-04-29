package User;

abstract class Utilizador {
    private int codigoUtilizador;
    private String nome;
    private String morada;
    private String email;
    private int peso;
    private double frequenciaCardiacaMedia;

    public Utilizador(int codigoUtilizador, String nome, String morada, String email, double frequenciaCardiacaMedia) {
        this.codigoUtilizador = codigoUtilizador;
        this.nome = nome;
        this.morada = morada;
        this.email = email;
        this.frequenciaCardiacaMedia = frequenciaCardiacaMedia;
    }

    public int getCodigoUtilizador(){
        return codigoUtilizador;
    }

    public String getNome(){
        return nome;
    }

    public String getMorada(){
        return morada;
    }

    public String getEmail(){
        return email;
    }

    public int getPeso(){
        return peso;
    }

    public double getFrequenciaCardiacaMedia(){
        return frequenciaCardiacaMedia;
    }
    
    public abstract double calcularFatorCalorias();
}