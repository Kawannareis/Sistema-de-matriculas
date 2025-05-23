package dao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class SistemaMatriculasGUI extends JFrame {
    private final AlunoService alunoService = new AlunoService();
    private final TurmaService turmaService = new TurmaService();
    private final ProfessorService professorService = new ProfessorService();

    // Aluno
    private JTextField alunoIdField, alunoNomeField, alunoEmailField, alunoMatriculaField;
    private JTextArea alunoListaArea;

    // Turma
    private JTextField turmaIdField, turmaNomeField, turmaProfessorField;
    private JTextArea turmaListaArea;

    // Professor
    private JTextField professorIdField, professorNomeField, professorEmailField;
    private JTextArea professorListaArea;

    public SistemaMatriculasGUI() {
        setTitle("Sistema de Matrículas");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Alunos", criarPainelAlunos());
        tabbedPane.addTab("Turmas", criarPainelTurmas());
        tabbedPane.addTab("Professores", criarPainelProfessores());

        add(tabbedPane);
    }

    private JPanel criarPainelAlunos() {
        alunoIdField = new JTextField(5);
        alunoIdField.setEditable(false);
        alunoNomeField = new JTextField(20);
        alunoEmailField = new JTextField(20);
        alunoMatriculaField = new JTextField(20);
        alunoListaArea = new JTextArea();
        alunoListaArea.setEditable(false);

        JButton btnAdicionar = new JButton("Adicionar");
        JButton btnListar = new JButton("Listar");
        JButton btnAtualizar = new JButton("Atualizar");
        JButton btnExcluir = new JButton("Excluir");

        btnAdicionar.addActionListener(e -> adicionarAluno());
        btnListar.addActionListener(e -> listarAlunos());
        btnAtualizar.addActionListener(e -> atualizarAluno());
        btnExcluir.addActionListener(e -> excluirAluno());

        JPanel form = new JPanel(new GridLayout(5, 2, 5, 5));
        form.add(new JLabel("ID:"));
        form.add(alunoIdField);
        form.add(new JLabel("Nome:"));
        form.add(alunoNomeField);
        form.add(new JLabel("Email:"));
        form.add(alunoEmailField);
        form.add(new JLabel("Matrícula:"));
        form.add(alunoMatriculaField);
        form.add(btnAdicionar);
        form.add(btnListar);

        JPanel botoes = new JPanel();
        botoes.add(btnAtualizar);
        botoes.add(btnExcluir);

        JPanel painel = new JPanel(new BorderLayout(10, 10));
        painel.add(form, BorderLayout.NORTH);
        painel.add(new JScrollPane(alunoListaArea), BorderLayout.CENTER);
        painel.add(botoes, BorderLayout.SOUTH);

        return painel;
    }

    private JPanel criarPainelTurmas() {
        turmaIdField = new JTextField(5);
        turmaIdField.setEditable(false);
        turmaNomeField = new JTextField(20);
        turmaProfessorField = new JTextField(20);
        turmaListaArea = new JTextArea();
        turmaListaArea.setEditable(false);

        JButton btnAdicionar = new JButton("Adicionar");
        JButton btnListar = new JButton("Listar");
        JButton btnAtualizar = new JButton("Atualizar");
        JButton btnExcluir = new JButton("Excluir");

        btnAdicionar.addActionListener(e -> adicionarTurma());
        btnListar.addActionListener(e -> listarTurmas());
        btnAtualizar.addActionListener(e -> atualizarTurma());
        btnExcluir.addActionListener(e -> excluirTurma());

        JPanel form = new JPanel(new GridLayout(4, 2, 5, 5));
        form.add(new JLabel("ID:"));
        form.add(turmaIdField);
        form.add(new JLabel("Nome:"));
        form.add(turmaNomeField);
        form.add(new JLabel("Professor:"));
        form.add(turmaProfessorField);
        form.add(btnAdicionar);
        form.add(btnListar);

        JPanel botoes = new JPanel();
        botoes.add(btnAtualizar);
        botoes.add(btnExcluir);

        JPanel painel = new JPanel(new BorderLayout(10, 10));
        painel.add(form, BorderLayout.NORTH);
        painel.add(new JScrollPane(turmaListaArea), BorderLayout.CENTER);
        painel.add(botoes, BorderLayout.SOUTH);

        return painel;
    }

    private JPanel criarPainelProfessores() {
        professorIdField = new JTextField(5);
        professorIdField.setEditable(false);
        professorNomeField = new JTextField(20);
        professorEmailField = new JTextField(20);
        professorListaArea = new JTextArea();
        professorListaArea.setEditable(false);

        JButton btnAdicionar = new JButton("Adicionar");
        JButton btnListar = new JButton("Listar");
        JButton btnAtualizar = new JButton("Atualizar");
        JButton btnExcluir = new JButton("Excluir");

        btnAdicionar.addActionListener(e -> adicionarProfessor());
        btnListar.addActionListener(e -> listarProfessores());
        btnAtualizar.addActionListener(e -> atualizarProfessor());
        btnExcluir.addActionListener(e -> excluirProfessor());

        JPanel form = new JPanel(new GridLayout(3, 2, 5, 5));
        form.add(new JLabel("ID:"));
        form.add(professorIdField);
        form.add(new JLabel("Nome:"));
        form.add(professorNomeField);
        form.add(new JLabel("Email:"));
        form.add(professorEmailField);

        JPanel botoes = new JPanel();
        botoes.add(btnAdicionar);
        botoes.add(btnListar);
        botoes.add(btnAtualizar);
        botoes.add(btnExcluir);

        JPanel painel = new JPanel(new BorderLayout(10, 10));
        painel.add(form, BorderLayout.NORTH);
        painel.add(new JScrollPane(professorListaArea), BorderLayout.CENTER);
        painel.add(botoes, BorderLayout.SOUTH);

        return painel;
    }

    // MÉTODOS ALUNO
    private void adicionarAluno() {
        Aluno aluno = new Aluno(0, alunoNomeField.getText(), alunoEmailField.getText(), alunoMatriculaField.getText());
        alunoService.criar(aluno);
        listarAlunos();
    }

    private void listarAlunos() {
        List<Aluno> alunos = alunoService.listar();
        alunoListaArea.setText("");
        for (Aluno a : alunos) {
            alunoListaArea.append(a.toString() + "\n");
        }
    }

    private void atualizarAluno() {
        int id = Integer.parseInt(alunoIdField.getText());
        Aluno aluno = new Aluno(id, alunoNomeField.getText(), alunoEmailField.getText(), alunoMatriculaField.getText());
        alunoService.atualizar(aluno);
        listarAlunos();
    }

    private void excluirAluno() {
        int id = Integer.parseInt(alunoIdField.getText());
        alunoService.excluir(id);
        listarAlunos();
    }

   
    private void adicionarTurma() {
        Turma turma = new Turma(turmaNomeField.getText(), turmaProfessorField.getText());
        turmaService.criar(turma);
        listarTurmas();
    }

    private void listarTurmas() {
        List<Turma> turmas = turmaService.listar();
        turmaListaArea.setText("");
        for (Turma t : turmas) {
            turmaListaArea.append(t.toString() + "\n");
        }
    }

    private void atualizarTurma() {
        int id = Integer.parseInt(turmaIdField.getText());
        Turma turma = new Turma(id, turmaNomeField.getText(), turmaProfessorField.getText());
        turmaService.atualizar(turma);
        listarTurmas();
    }

    private void excluirTurma() {
        int id = Integer.parseInt(turmaIdField.getText());
        turmaService.excluir(id);
        listarTurmas();
    }

    // MÉTODOS PROFESSOR
    private void adicionarProfessor() {
        Professor professor = new Professor(0, professorNomeField.getText(), professorEmailField.getText());
        professorService.criar(professor);
        listarProfessores();
    }

    private void listarProfessores() {
        List<Professor> professores = professorService.listar();
        professorListaArea.setText("");
        for (Professor p : professores) {
            professorListaArea.append(p.toString() + "\n");
        }
    }

    private void atualizarProfessor() {
        int id = Integer.parseInt(professorIdField.getText());
        Professor professor = new Professor(id, professorNomeField.getText(), professorEmailField.getText());
        professorService.atualizar(professor);
        listarProfessores();
    }

    private void excluirProfessor() {
        int id = Integer.parseInt(professorIdField.getText());
        professorService.excluir(id);
        listarProfessores();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SistemaMatriculasGUI().setVisible(true));
    }
}
