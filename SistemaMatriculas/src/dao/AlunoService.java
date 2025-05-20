package dao;

import java.util.List;

public class AlunoService implements IService<Aluno> {
    private final AlunoDAO alunoDAO = new AlunoDAO();

    @Override
    public void criar(Aluno aluno) {
        alunoDAO.criar(aluno);
    }

    @Override
    public List<Aluno> listar() {
        return alunoDAO.listar();
    }

    @Override
    public Aluno buscarPorId(int id) {
        return alunoDAO.buscarPorId(id);
    }

    @Override
    public void atualizar(Aluno aluno) {
        // não implementado ainda
    }

    @Override
    public void excluir(int id) {
        // não implementado ainda
    }
}