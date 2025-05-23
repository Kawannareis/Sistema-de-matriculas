package dao;

import java.util.List;

public class ProfessorService {
    private final ProfessorDAO dao = new ProfessorDAO();

    public void criar(Professor p) {
        dao.criar(p);
    }

    public List<Professor> listar() {
        return dao.listar();
    }
}
