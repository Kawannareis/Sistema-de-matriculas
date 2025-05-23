package dao;

import java.util.List;

public interface Prof<T> {
    void criar(T t);
    List<T> listar();
}

