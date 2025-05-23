package dao;

import dao.*;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SistemaMatriculasGUI extends JFrame {

    
    private JTextField nomeAlunoField, emailAlunoField, matriculaField;
    private JTextField nomeTurmaField, professorTurmaField, horarioField;
    private JTextField nomeProfField, emailProfField, disciplinaField;

    private JTextArea listaAlunoArea, listaTurmaArea, listaProfessorArea;

    
    private final AlunoService alunoService = new AlunoService();
    private final TurmaService turmaService = new TurmaService();
    private final ProfessorService professorService = new ProfessorService();

    public SistemaMatriculasGUI() {
        setTitle("Sistema de Matrículas");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();

        
        tabbedPane.add("Alunos", criarPainelAluno());
        tabbedPane.add("Turmas", criarPainelTurma());
        tabbedPane.add("Professores", criarPainelProfessor());

        add(tabbedPane);
    }

   
    private JPanel criarPainelAluno() {
        nomeAlunoField = new JTextField(20);
        emailAlunoField = new JTextField(20);
        matriculaField = new JTextField(20);
        listaAlunoArea = new JTextArea(10, 40);
        listaAlunoArea.setEditable(false);

        JButton btnAdd = new JButton("Adicionar Aluno");
        JButton btnListar = new JButton("Listar Alunos");

        btnAdd.addActionListener(e -> {
            String nome = nomeAlunoField.getText().trim();
            String email = emailAlunoField.getText().trim();
            String matricula = matriculaField.getText().trim();

            if (nome.isEmpty() || email.isEmpty() || matricula.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
                return;
            }

            alunoService.criar(new Aluno(0, nome, email, matricula));
            limparCampos(nomeAlunoField, emailAlunoField, matriculaField);
        });

        btnListar.addActionListener(e -> {
            List<Aluno> lista = alunoService.listar();
            mostrarLista(listaAlunoArea, lista);
        });

        return montarFormulario(new JComponent[]{
            new JLabel("Nome:"), nomeAlunoField,
            new JLabel("Email:"), emailAlunoField,
            new JLabel("Matrícula:"), matriculaField,
            btnAdd, btnListar
        }, listaAlunoArea);
    }

    
    private JPanel criarPainelTurma() {
        nomeTurmaField = new JTextField(20);
        professorTurmaField = new JTextField(20);
        horarioField = new JTextField(20);
        listaTurmaArea = new JTextArea(10, 40);
        listaTurmaArea.setEditable(false);

        JButton btnAdd = new JButton("Adicionar Turma");
        JButton btnListar = new JButton("Listar Turmas");

        btnAdd.addActionListener(e -> {
            String nome = nomeTurmaField.getText().trim();
            String professor = professorTurmaField.getText().trim();
            String horario = horarioField.getText().trim();

            if (nome.isEmpty() || professor.isEmpty() || horario.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
                return;
            }

            turmaService.criar(new Turma(0, nome, professor, horario));
            limparCampos(nomeTurmaField, professorTurmaField, horarioField);
        });

        btnListar.addActionListener(e -> {
            List<Turma> lista = turmaService.listar();
            mostrarLista(listaTurmaArea, lista);
        });

        return montarFormulario(new JComponent[]{
            new JLabel("Nome da Turma:"), nomeTurmaField,
            new JLabel("Professor:"), professorTurmaField,
            new JLabel("Horário:"), horarioField,
            btnAdd, btnListar
        }, listaTurmaArea);
    }

    
    private JPanel criarPainelProfessor() {
        nomeProfField = new JTextField(20);
        emailProfField = new JTextField(20);
        disciplinaField = new JTextField(20);
        listaProfessorArea = new JTextArea(10, 40);
        listaProfessorArea.setEditable(false);

        JButton btnAdd = new JButton("Adicionar Professor");
        JButton btnListar = new JButton("Listar Professores");

        btnAdd.addActionListener(e -> {
            String nome = nomeProfField.getText().trim();
            String email = emailProfField.getText().trim();
            String disciplina = disciplinaField.getText().trim();

            if (nome.isEmpty() || email.isEmpty() || disciplina.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
                return;
            }

            professorService.criar(new Professor(0, nome, email, disciplina));
            limparCampos(nomeProfField, emailProfField, disciplinaField);
        });

        btnListar.addActionListener(e -> {
            List<Professor> lista = professorService.listar();
            mostrarLista(listaProfessorArea, lista);
        });

        return montarFormulario(new JComponent[]{
            new JLabel("Nome:"), nomeProfField,
            new JLabel("Email:"), emailProfField,
            new JLabel("Disciplina:"), disciplinaField,
            btnAdd, btnListar
        }, listaProfessorArea);
    }

    
    private JPanel montarFormulario(JComponent[] componentes, JTextArea areaLista) {
        JPanel painel = new JPanel(new BorderLayout(10, 10));

        JPanel form = new JPanel(new GridLayout(componentes.length / 2, 2, 5, 5));
        for (JComponent c : componentes) form.add(c);

        painel.add(form, BorderLayout.NORTH);
        painel.add(new JScrollPane(areaLista), BorderLayout.CENTER);

        return painel;
    }

    private void limparCampos(JTextField... campos) {
        for (JTextField campo : campos) campo.setText("");
    }

    private <T> void mostrarLista(JTextArea area, List<T> lista) {
        StringBuilder sb = new StringBuilder();
        if (lista.isEmpty()) {
            sb.append("Nenhum registro encontrado.");
        } else {
            for (T item : lista) sb.append(item.toString()).append("\n");
        }
        area.setText(sb.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SistemaMatriculasGUI().setVisible(true));
    }
}
