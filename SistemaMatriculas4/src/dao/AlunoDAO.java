package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {
    public void criar(Aluno aluno) {
        String sql = "INSERT INTO aluno (nome, email, matricula) VALUES (?, ?, ?)";
        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getEmail());
            stmt.setString(3, aluno.getMatricula());

            stmt.executeUpdate();
            System.out.println("✅ Aluno inserido com sucesso!");
        } catch (SQLException e) {
            System.err.println("❌ Erro ao inserir aluno: " + e.getMessage());
        }
    }

    public List<Aluno> listar() {
        List<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT * FROM aluno";

        try (Connection conn = Conexao.getConexao();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Aluno aluno = new Aluno(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("matricula")
                );
                alunos.add(aluno);
            }

        } catch (SQLException e) {
            System.err.println("❌ Erro ao listar alunos: " + e.getMessage());
        }

        return alunos;
    }

    public Aluno buscarPorId(int id) {
        String sql = "SELECT * FROM aluno WHERE id = ?";
        Aluno aluno = null;

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                aluno = new Aluno(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("matricula")
                );
            }

        } catch (SQLException e) {
            System.err.println("❌ Erro ao buscar aluno: " + e.getMessage());
        }

        return aluno;
    }
}