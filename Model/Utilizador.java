package Model;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class Utilizador implements Serializable{
    private String username;
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
    private ArrayList<PlanoTreino> planosTreino;

    public Utilizador(){
        this.morada = "";
        this.email = "";
        this.password = "";
        this.username = "";
        this.genero = Genero.Outro;
        this.altura = 0;
        this.peso = 0;
        this.data_nascimento = LocalDate.ofEpochDay(0);
        this.desporto_favorito = "";
        this.tipo_atleta = "";
        this.atividades = new HashMap<>();
        this.planosTreino = new ArrayList<>();
    }

    public Utilizador(String morada, String email, String password, String username, Genero genero, double altura, double peso, LocalDate data_nascimento, String desporto_favorito, String tipo_atleta, Map<String, Atividade> atividades, ArrayList <PlanoTreino> planosTreino) {
        this.morada = morada;
        this.email = email;
        this.password = password;
        this.username = username;
        this.genero = genero;
        this.altura = altura;
        this.peso = peso;
        this.data_nascimento = data_nascimento;
        this.desporto_favorito = desporto_favorito;
        this.tipo_atleta = tipo_atleta;
        this.atividades = atividades.entrySet().stream().collect(Collectors.toMap(k->k.getKey(), v-> v.getValue().clone()));
        this.planosTreino = new ArrayList<>(planosTreino.size());
        for (PlanoTreino plano : planosTreino) {
            this.planosTreino.add(plano.clone());
        }
    }

    public Utilizador(String morada, String email, String password, String username, Genero genero, double altura, double peso, LocalDate data_nascimento, String desporto_favorito, String tipo_atleta) {
        this.morada = morada;
        this.email = email;
        this.password = password;
        this.username = username;
        this.genero = genero;
        this.altura = altura;
        this.peso = peso;
        this.data_nascimento = data_nascimento;
        this.desporto_favorito = desporto_favorito;
        this.tipo_atleta = tipo_atleta;
        this.atividades = new HashMap<>();
        this.planosTreino = new ArrayList<>();
    }

    public Utilizador(Utilizador outro){
        this.morada = outro.getMorada();
        this.email = outro.getEmail();
        this.password = outro.getPassword();
        this.username = outro.getusername();
        this.genero = outro.getGenero();
        this.altura = outro.getAltura();
        this.peso = outro.getPeso();
        this.data_nascimento = outro.getData_nascimento();
        this.desporto_favorito = outro.getDesporto_favorito();
        this.tipo_atleta = outro.getTipo_atleta();
        this.atividades = outro.getAtividades();
        this.planosTreino = outro.getPlanosTreino();
    }

    public String getusername(){
        return username;
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

    public Map<String, Atividade> getAtividades() {
        return atividades.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().clone()));
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

    public void setusername(String username) {
        this.username = username;
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

    public ArrayList<PlanoTreino> getPlanosTreino() {
        return planosTreino;
    }

    public void adicionarPlanoTreino(PlanoTreino planoTreino) {
        planosTreino.add(planoTreino);
    }

    public double calcularTotalCalorias(LocalDate inicioPeriodo, LocalDate fimPeriodo) {
        double totalCalorias = 0.0;
        for (Atividade atividade : atividades.values()) {
            LocalDate dataAtividade = atividade.getData();
            if (!dataAtividade.isBefore(inicioPeriodo) && !dataAtividade.isAfter(fimPeriodo)) {
                totalCalorias += atividade.calorias(this);
            }
        }
        return totalCalorias;
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
                && Objects.equals(getusername(), that.getusername())
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
                ", username='" + username + '\'' +
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
    public abstract Utilizador clone();
}