package dao;

public class Turma {
    private int id;
    private String nome;
    private String professor;
    private String horario;

    public Turma(int id, String nome, String professor, String horario) {
        this.id = id;
        this.nome = nome;
        this.professor = professor;
        this.horario = horario;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getProfessor() { return professor; }
    public String getHorario() { return horario; }

    public void setId(int id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setProfessor(String professor) { this.professor = professor; }
    public void setHorario(String horario) { this.horario = horario; }

    @Override
    public String toString() {
        return "ID: " + id + ", Nome: " + nome + ", Professor: " + professor + ", Horário: " + horario;
    }
}
