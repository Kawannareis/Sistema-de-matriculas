package dao;

import java.sql.*;
import java.util.*;

public class ProfessorDAO implements IDAO<Professor> {

    @Override
    public void criar(Professor professor) {
        String sql = "INSERT INTO professor (nome, email) VALUES (?, ?)";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, professor.getNome());
            stmt.setString(2, professor.getEmail());
            stmt.executeUpdate();
            System.out.println("✅ Professor inserido com sucesso!");

        } catch (SQLException e) {
            System.out.println("❌ Erro ao inserir professor: " + e.getMessage());
        }
    }

    @Override
    public List<Professor> listar() {
        List<Professor> professores = new ArrayList<>();
        String sql = "SELECT * FROM professor";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Professor professor = new Professor(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("email")
                );
                professores.add(professor);
            }

        } catch (SQLException e) {
            System.out.println("❌ Erro ao listar professores: " + e.getMessage());
        }

        return professores;
    }

    @Override
    public Professor buscarPorId(int id) {
        String sql = "SELECT * FROM professor WHERE id = ?";
        Professor professor = null;

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                professor = new Professor(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("email")
                );
            }

        } catch (SQLException e) {
            System.out.println("❌ Erro ao buscar professor: " + e.getMessage());
        }

        return professor;
    }

    @Override
    public void atualizar(Professor professor) {
        String sql = "UPDATE professor SET nome = ?, email = ? WHERE id = ?";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, professor.getNome());
            stmt.setString(2, professor.getEmail());
            stmt.setInt(3, professor.getId());
            stmt.executeUpdate();

            System.out.println("✅ Professor atualizado com sucesso!");

        } catch (SQLException e) {
            System.out.println("❌ Erro ao atualizar professor: " + e.getMessage());
        }
    }

    @Override
    public void excluir(int id) {
        String sql = "DELETE FROM professor WHERE id = ?";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

            System.out.println("✅ Professor excluído com sucesso!");

        } catch (SQLException e) {
            System.out.println("❌ Erro ao excluir professor: " + e.getMessage());
        }
    }
}
