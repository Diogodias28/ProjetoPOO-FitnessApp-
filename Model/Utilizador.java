package Model;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.Map;

public abstract class Utilizador {
    private int codigoUtilizador;
    private String nome;
    private String morada;
    private String email;
    private String password;
    private Genero genero;
    private double altura;
    private double peso;
    private LocalDate data_nascimento;
    private String tipo_atleta;
    private String desporto_favorito;
    private Map<String, Atividade> atividades;
    private double frequenciaCardiacaMedia;

    public Utilizador(){
        this.codigoUtilizador = 0;
        this.morada = "";
        this.email = "";
        this.password = "";
        this.nome = "nome";
        this.genero = Genero.Outro;
        this.altura = 0;
        this.peso = 0;
        this.data_nascimento = LocalDate.ofEpochDay(0);
        this.desporto_favorito = "";
        this.tipo_atleta = "";
        this.atividades = new HashMap<>();
        this.frequenciaCardiacaMedia = 0;
    }

    public Utilizador(int codigoUtilizador, String morada, String email, String password, String nome, Genero genero, double altura, double peso, LocalDate data_nascimento, String desporto_favorito, String tipo_atleta, Map<String, Atividade> atividades, double frequenciaCardiacaMedia) {
        this.codigoUtilizador = codigoUtilizador;
        this.morada = morada;
        this.email = email;
        this.password = password;
        this.nome = nome;
        this.genero = genero;
        this.altura = altura;
        this.peso = peso;
        this.data_nascimento = data_nascimento;
        this.desporto_favorito = desporto_favorito;
        this.tipo_atleta = tipo_atleta;
        this.atividades = atividades.entrySet().stream().collect(Collectors.toMap(k->k.getKey(), v-> v.getValue().clone()));
        this.frequenciaCardiacaMedia = frequenciaCardiacaMedia;
    }

    public Utilizador(int codigoUtilizador, String morada, String email, String password, String nome, Genero genero, double altura, double peso, LocalDate data_nascimento, String desporto_favorito, String tipo_atleta, double frequenciaCardiacaMedia) {
        this.codigoUtilizador = codigoUtilizador;
        this.morada = morada;
        this.email = email;
        this.password = password;
        this.nome = nome;
        this.genero = genero;
        this.altura = altura;
        this.peso = peso;
        this.data_nascimento = data_nascimento;
        this.desporto_favorito = desporto_favorito;
        this.tipo_atleta = tipo_atleta;
        this.frequenciaCardiacaMedia = frequenciaCardiacaMedia;
        this.atividades = new HashMap<>();
    }

    public Utilizador(Utilizador outro){
        this.codigoUtilizador = outro.getCodigoUtilizador();
        this.morada = outro.getMorada();
        this.email = outro.getEmail();
        this.password = outro.getPassword();
        this.nome = outro.getNome();
        this.genero = outro.getGenero();
        this.altura = outro.getAltura();
        this.peso = outro.getAltura();
        this.data_nascimento = outro.getData_nascimento();
        this.desporto_favorito = outro.getDesporto_favorito();
        this.tipo_atleta = outro.getTipo_atleta();
        this.atividades = outro.getAtividades();
        this.frequenciaCardiacaMedia = getFrequenciaCardiacaMedia();
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

    public double getPeso(){
        return peso;
    }

    public double getFrequenciaCardiacaMedia(){
        return frequenciaCardiacaMedia;
    }

    public Map<String, Atividade> getAtividades() {
        return atividades.entrySet().stream().collect(Collectors.toMap(k->k.getKey(), v-> v.getValue().clone()));
    }

    public void setAtividades(Map<String, Atividade> atividades) {
        this.atividades =  atividades.entrySet().stream().collect(Collectors.toMap(k->k.getKey(), v-> v.getValue().clone()));
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public LocalDate getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(LocalDate data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public String getDesporto_favorito() {
        return desporto_favorito;
    }

    public void setDesporto_favorito(String desporto_favorito) {
        this.desporto_favorito = desporto_favorito;
    }

    public String getTipo_atleta() {
        return tipo_atleta;
    }

    public void setTipo_atleta(String tipo_atleta) {
        this.tipo_atleta = tipo_atleta;
    }

    public void addAtividade(Atividade a){
        this.atividades.put(a.getCodigo(), a.clone());
    }

    public Atividade getAtividade(String cod){
        if(!atividades.containsKey(cod)){
            return null;
        }
        return atividades.get(cod).clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Utilizador that = (Utilizador) o;
        return Double.compare(that.getAltura(), getAltura()) == 0
                && Double.compare(that.getPeso(), getPeso()) == 0
                && Objects.equals(getEmail(), that.getEmail())
                && Objects.equals(getPassword(), that.getPassword())
                && Objects.equals(getNome(), that.getNome())
                && getGenero() == that.getGenero()
                && Objects.equals(getData_nascimento(), that.getData_nascimento())
                && Objects.equals(getDesporto_favorito(), that.getDesporto_favorito())
                && Objects.equals(getTipo_atleta(), that.getTipo_atleta())
                && this.atividades.equals(that.getAtividades());
    }

    @Override
    public String toString() {
        return "Utilizador{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", nome='" + nome + '\'' +
                ", genero=" + genero +
                ", altura=" + altura +
                ", peso=" + peso +
                ", data_nascimento=" + data_nascimento +
                ", desporto_favorito=" + desporto_favorito +
                ", tipo_atleta=" + tipo_atleta +
                '}';
    }
    //public Utilizador clone(){
    //    return new Utilizador(this);
    //}

    public abstract double calcularFatorCalorias();
}