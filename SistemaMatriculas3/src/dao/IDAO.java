package dao;

import java.util.List;

public interface IDAO<T> {
    void criar(T entidade);
    List<T> listar();
    T buscarPorId(int id);
    void atualizar(T entidade);
    void excluir(int id);
}
