package dao;

import java.util.List;

public class ProfessorService implements IService<Professor> {
    private final ProfessorDAO professorDAO = new ProfessorDAO();

    @Override
    public void criar(Professor professor) {
        professorDAO.criar(professor);
    }

    @Override
    public List<Professor> listar() {
        return professorDAO.listar();
    }

    @Override
    public Professor buscarPorId(int id) {
        return professorDAO.buscarPorId(id);
    }

    @Override
    public void atualizar(Professor professor) {
        professorDAO.atualizar(professor);
    }

    @Override
    public void excluir(int id) {
        professorDAO.excluir(id);
    }
}
