import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Utilizador {
    private String email;
    private String password;
    private String nome;
    private Genero genero;
    private double altura;
    private double peso;
    private LocalDate data_nascimento;
    private String desporto_favorito;
    private Map<String, Atividade> atividades;

    public Utilizador(){
        this.email = "";
        this.password = "";
        this.nome = "nome";
        this.genero = Genero.Outro;
        this.altura = 0;
        this.peso = 0;
        this.data_nascimento = LocalDate.EPOCH;
        this.desporto_favorito = "";
        this.atividades = new HashMap<>();
    }
    public Utilizador(String email, String password, String nome, Genero genero, double altura,
                      double peso, LocalDate data_nascimento, String desporto_favorito,
                      Map<String, Atividade> atividades) {
        this.email = email;
        this.password = password;
        this.nome = nome;
        this.genero = genero;
        this.altura = altura;
        this.peso = peso;
        this.data_nascimento = data_nascimento;
        this.desporto_favorito = desporto_favorito;
        this.atividades = atividades.entrySet().stream().collect(Collectors.toMap(k->k.getKey(), v-> v.getValue().clone()));
    }
    public void displayInfo() {
       System.out.println("Username: " + this.username);
       System.out.println("Weight: " + this.weight + " kg");
       System.out.println("Athlete Type: " + this.athleteType);
    }
 }