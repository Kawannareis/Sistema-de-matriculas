package dao;

import java.util.List;

public interface IService<T> {
    void criar(T obj);
    List<T> listar();
    T buscarPorId(int id);
    void atualizar(T obj);
    void excluir(int id);
}