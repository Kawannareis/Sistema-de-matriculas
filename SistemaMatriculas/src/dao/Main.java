package dao;

public class Main {
    public static void main(String[] args) {
        AlunoService alunoService = new AlunoService();

        Aluno aluno = new Aluno(0, "Carlos Silva", "carlos@email.com", "MAT456");
        alunoService.criar(aluno);

        System.out.println("📋 Lista de alunos:");
        alunoService.listar().forEach(System.out::println);
    }
}