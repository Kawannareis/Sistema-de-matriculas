package dao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class TurmaUI extends JFrame {
    private final TurmaService turmaService = new TurmaService();

    private JTextField idField, nomeField, horarioField, professorField;
    private JTextArea displayArea;

    public TurmaUI() {
        setTitle("📘 Cadastro de Turmas");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 450);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Painel de formulário
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Informações da Turma"));

        idField = new JTextField();
        nomeField = new JTextField();
        horarioField = new JTextField();
        professorField = new JTextField();

        formPanel.add(new JLabel("ID:"));
        formPanel.add(idField);
        formPanel.add(new JLabel("Nome:"));
        formPanel.add(nomeField);
        formPanel.add(new JLabel("Horário:"));
        formPanel.add(horarioField);
        formPanel.add(new JLabel("Professor:"));
        formPanel.add(professorField);

        // Painel de botões
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        buttonPanel.setBorder(BorderFactory.createTitledBorder("Ações"));

        JButton salvarButton = new JButton("Salvar");
        salvarButton.addActionListener(this::salvarTurma);
        buttonPanel.add(salvarButton);

        JButton listarButton = new JButton("Listar");
        listarButton.addActionListener(this::listarTurmas);
        buttonPanel.add(listarButton);

        JButton atualizarButton = new JButton("Atualizar");
        atualizarButton.addActionListener(this::atualizarTurma);
        buttonPanel.add(atualizarButton);

        JButton deletarButton = new JButton("Deletar");
        deletarButton.addActionListener(this::deletarTurma);
        buttonPanel.add(deletarButton);

        // Área de exibição
        displayArea = new JTextArea(10, 50);
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Lista de Turmas"));

        // Adiciona os painéis à janela
        JPanel topPanel = new JPanel(new BorderLayout(10, 10));
        topPanel.add(formPanel, BorderLayout.CENTER);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    private void salvarTurma(ActionEvent e) {
        String idTexto = idField.getText();
        String nome = nomeField.getText();
        String horario = horarioField.getText();
        String professor = professorField.getText();

        if (idTexto.isEmpty() || nome.isEmpty() || professor.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!");
            return;
        }

        try {
            int id = Integer.parseInt(idTexto);
            Turma turma = new Turma(id, nome, professor, horario);
            turmaService.criar(turma);
            JOptionPane.showMessageDialog(this, "Turma salva com sucesso!");
            limparCampos();
            listarTurmas(null);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID deve ser um número inteiro.");
        }
    }

    private void listarTurmas(ActionEvent e) {
        List<Turma> turmas = turmaService.listar();
        displayArea.setText("");

        if (turmas.isEmpty()) {
            displayArea.setText("Nenhuma turma cadastrada.");
        } else {
            for (Turma t : turmas) {
                displayArea.append(t.toString() + "\n");
            }
        }
    }

    private void atualizarTurma(ActionEvent e) {
        String idTexto = idField.getText();
        String nome = nomeField.getText();
        String horario = horarioField.getText();
        String professor = professorField.getText();

        if (idTexto.isEmpty() || nome.isEmpty() || professor.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos para atualizar.");
            return;
        }

        try {
            int id = Integer.parseInt(idTexto);
            Turma turma = new Turma(id, nome, professor, horario);
            turmaService.atualizar(turma);
            JOptionPane.showMessageDialog(this, "Turma atualizada com sucesso!");
            listarTurmas(null);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID deve ser um número inteiro.");
        }
    }

    private void deletarTurma(ActionEvent e) {
        String idTexto = idField.getText();
        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe o ID da turma a ser deletada.");
            return;
        }

        try {
            int id = Integer.parseInt(idTexto);
            turmaService.excluir(id);
            JOptionPane.showMessageDialog(this, "Turma excluída com sucesso!");
            limparCampos();
            listarTurmas(null);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID deve ser um número inteiro.");
        }
    }

    private void limparCampos() {
        idField.setText("");
        nomeField.setText("");
        horarioField.setText("");
        professorField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TurmaUI::new);
    }
}
