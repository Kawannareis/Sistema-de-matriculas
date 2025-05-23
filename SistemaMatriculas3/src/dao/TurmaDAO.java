package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TurmaDAO implements IDAO<Turma> {

    @Override
    public void criar(Turma turma) {
        String sql = "INSERT INTO turma (nome, professor, horario) VALUES (?, ?, ?)";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, turma.getNome());
            stmt.setString(2, turma.getProfessor());
            stmt.setString(3, turma.getHorario());  
            stmt.executeUpdate();
            System.out.println("✅ Turma inserida com sucesso!");

        } catch (SQLException e) {
            System.out.println("❌ Erro ao inserir turma: " + e.getMessage());
        }
    }

    @Override
    public List<Turma> listar() {
        List<Turma> turmas = new ArrayList<>();
        String sql = "SELECT * FROM turma";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Turma turma = new Turma(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("professor"),
                    rs.getString("horario")   
                );
                turmas.add(turma);
            }

        } catch (SQLException e) {
            System.out.println("❌ Erro ao listar turmas: " + e.getMessage());
        }

        return turmas;
    }

    @Override
    public Turma buscarPorId(int id) {
        String sql = "SELECT * FROM turma WHERE id = ?";
        Turma turma = null;

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                turma = new Turma(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("professor"),
                    rs.getString("horario")  
                );
            }

        } catch (SQLException e) {
            System.out.println("❌ Erro ao buscar turma: " + e.getMessage());
        }

        return turma;
    }

    @Override
    public void atualizar(Turma turma) {
        String sql = "UPDATE turma SET nome = ?, professor = ?, horario = ? WHERE id = ?";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, turma.getNome());
            stmt.setString(2, turma.getProfessor());
            stmt.setString(3, turma.getHorario());  
            stmt.setInt(4, turma.getId());
            stmt.executeUpdate();

            System.out.println("✅ Turma atualizada com sucesso!");

        } catch (SQLException e) {
            System.out.println("❌ Erro ao atualizar turma: " + e.getMessage());
        }
    }

    @Override
    public void excluir(int id) {
        String sql = "DELETE FROM turma WHERE id = ?";

        try (Connection conn = Conexao.getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

            System.out.println("✅ Turma excluída com sucesso!");

        } catch (SQLException e) {
            System.out.println("❌ Erro ao excluir turma: " + e.getMessage());
        }
    }
}
