package dao;

public class Aluno extends Pessoa {
    private String matricula;

    public Aluno(int id, String nome, String email, String matricula) {
        super(id, nome, email);
        this.matricula = matricula;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    @Override
    public String toString() {
        return super.toString() + ", Matrícula: " + matricula;
    }

  @Override
public String toJson() {
    return "{ \"id\": " + id + ", \"nome\": \"" + nome + "\", \"email\": \"" + email + "\", \"matricula\": \"" + matricula + "\" }";
    }
}