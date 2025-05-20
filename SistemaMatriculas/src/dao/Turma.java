package dao;

public class Turma extends EntidadeBase {
    private String nome;
    private String horario;
    private String professor;

    public Turma(int id, String nome, String horario, String professor) {
        super(id);
        this.nome = nome;
        this.horario = horario;
        this.professor = professor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    @Override
    public String toString() {
        return "Turma [ID: " + id + ", Nome: " + nome + ", Horário: " + horario + ", Professor: " + professor + "]";
    }

    @Override
    public String toJson() {
        return "{ "id": " + id + ", "nome": "" + nome + "", "horario": "" + horario + "", "professor": "" + professor + "" }";
    }
}