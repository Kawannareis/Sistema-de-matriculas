package dao;

import java.util.List;

public class TurmaService implements IService<Turma> {
    private final TurmaDAO turmaDAO = new TurmaDAO();

    @Override
    public void criar(Turma turma) {
        turmaDAO.criar(turma);
    }

    @Override
    public List<Turma> listar() {
        return turmaDAO.listar();
    }

    @Override
    public Turma buscarPorId(int id) {
        return turmaDAO.buscarPorId(id);
    }

    @Override
    public void atualizar(Turma turma) {
        turmaDAO.atualizar(turma);
    }

    @Override
    public void excluir(int id) {
        turmaDAO.excluir(id);
    }
}

