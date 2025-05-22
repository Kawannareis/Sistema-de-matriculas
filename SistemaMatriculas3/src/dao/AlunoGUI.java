package dao;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class AlunoGUI extends JFrame {

    private final AlunoService alunoService = new AlunoService();

    private JTextField nomeField;
    private JTextField emailField;
    private JTextField matriculaField;
    private JTextArea listaArea;

    public AlunoGUI() {
        setTitle("Sistema de Matrículas - Alunos");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // centraliza a janela

        // Criando componentes
        nomeField = new JTextField(20);
        emailField = new JTextField(20);
        matriculaField = new JTextField(20);

        JButton btnAdicionar = new JButton("Adicionar Aluno");
        JButton btnListar = new JButton("Listar Alunos");

        listaArea = new JTextArea();
        listaArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(listaArea);

        // Painel de formulário
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        formPanel.add(new JLabel("Nome:"));
        formPanel.add(nomeField);
        formPanel.add(new JLabel("Email:"));
        formPanel.add(emailField);
        formPanel.add(new JLabel("Matrícula:"));
        formPanel.add(matriculaField);
        formPanel.add(btnAdicionar);
        formPanel.add(btnListar);

        // Layout principal
        setLayout(new BorderLayout(10, 10));
        add(formPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Ações dos botões
        btnAdicionar.addActionListener(e -> adicionarAluno());
        btnListar.addActionListener(e -> listarAlunos());
    }

    private void adicionarAluno() {
        String nome = nomeField.getText().trim();
        String email = emailField.getText().trim();
        String matricula = matriculaField.getText().trim();

        if (nome.isEmpty() || email.isEmpty() || matricula.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Aluno aluno = new Aluno(0, nome, email, matricula);
        alunoService.criar(aluno);

        JOptionPane.showMessageDialog(this, "Aluno adicionado com sucesso!");

        // Limpa os campos
        nomeField.setText("");
        emailField.setText("");
        matriculaField.setText("");
    }

    private void listarAlunos() {
        List<Aluno> alunos = alunoService.listar();
        StringBuilder sb = new StringBuilder();

        if (alunos.isEmpty()) {
            sb.append("Nenhum aluno cadastrado.");
        } else {
            for (Aluno a : alunos) {
                sb.append(a).append("\n");
            }
        }

        listaArea.setText(sb.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AlunoGUI().setVisible(true);
        });
    }
}

