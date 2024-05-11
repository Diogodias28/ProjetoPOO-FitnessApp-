package Model;
import java.time.LocalDate;
//import java.time.temporal.ChronoUnit;

public class MountainClimber extends Reps{

    public MountainClimber(){
        super("MountainClimber", "Mountain Climber", LocalDate.EPOCH, 0, Dificuldade.DIFICIL,0);
    }

    public MountainClimber(String codigo, String descricao, LocalDate data, int duracao, Dificuldade dificuldade, int reps) {
        super(codigo, descricao, data, duracao, dificuldade, reps);
    }

    public MountainClimber(MountainClimber outro) {
        super(outro.getCodigo(), outro.getDescricao(), LocalDate.EPOCH, outro.getDuracao(), outro.getDificuldade(), outro.getreps());
    }

    @Override
    public Atividade clone() {
        return new MountainClimber(this);
    }

    @Override
    public String toString() {
        return "MountainClimber{" +
                super.toString()+
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return super.equals(o);
        /*return Double.compare(MountainClimber.getRep(), getRep()) == 0
                && this.getTipo().equals(MountainClimber.getTipo());*/
    }

}