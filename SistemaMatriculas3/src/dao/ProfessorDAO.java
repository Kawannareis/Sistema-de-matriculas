package dao;

import java.sql.*;
import java.util.*;

public class ProfessorDAO implements IDAO<Professor> {

    private final Connection conn;

    public ProfessorDAO() {
        this.conn = ConexaoDB.getInstance().getConnection();
    }

    @Override
    public void criar(Professor professor) {
        String sql = "INSERT INTO professores (nome, email, disciplina) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, professor.getNome());
            stmt.setString(2, professor.getEmail());
            stmt.setString(3, professor.getDisciplina());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir professor", e);
        }
    }

    @Override
    public List<Professor> listar() {
        List<Professor> lista = new ArrayList<>();
        String sql = "SELECT * FROM professores";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Professor p = new Professor(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("disciplina")
                );
                lista.add(p);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar professores", e);
        }
        return lista;
    }
}
