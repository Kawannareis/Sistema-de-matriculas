package dao;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.*;

public class TurmaUI extends JFrame {
    private final TurmaService turmaService = new TurmaService();

    private JTextField idField, nomeField, horarioField, professorField;
    private JTextArea displayArea;

    public TurmaUI() {
        setTitle("Cadastro de Turmas");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(5, 2));

        inputPanel.add(new JLabel("ID:"));
        idField = new JTextField();
        inputPanel.add(idField);

        inputPanel.add(new JLabel("Nome:"));
        nomeField = new JTextField();
        inputPanel.add(nomeField);

        inputPanel.add(new JLabel("Horário:"));
        horarioField = new JTextField();
        inputPanel.add(horarioField);

        inputPanel.add(new JLabel("Professor:"));
        professorField = new JTextField();
        inputPanel.add(professorField);

        JButton salvarButton = new JButton("Salvar");
        salvarButton.addActionListener(this::salvarTurma);
        inputPanel.add(salvarButton);

        JButton listarButton = new JButton("Listar");
        listarButton.addActionListener(this::listarTurmas);
        inputPanel.add(listarButton);

        add(inputPanel, BorderLayout.NORTH);

        displayArea = new JTextArea();
        displayArea.setEditable(false);
        add(new JScrollPane(displayArea), BorderLayout.CENTER);

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
            limparCampos();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID deve ser um número inteiro.");
        }
    }

    private void listarTurmas(ActionEvent e) {
        List<Turma> turmas = turmaService.listar();
        displayArea.setText("");
        for (Turma t : turmas) {
            displayArea.append(t.toString() + "\n");
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
