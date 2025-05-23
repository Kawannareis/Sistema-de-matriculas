package dao;

public class Professor {
    private int id;
    private String nome;
    private String email;
    private String disciplina;

    public Professor(int id, String nome, String email, String disciplina) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.disciplina = disciplina;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getDisciplina() { return disciplina; }

    @Override
    public String toString() {
        return "Professor: " + nome + " | Email: " + email + " | Disciplina: " + disciplina;
    }
}
