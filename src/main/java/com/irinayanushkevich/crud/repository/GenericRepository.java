package com.irinayanushkevich.crud.repository;

import java.util.List;

public interface GenericRepository<T, ID> {

    T create(T entity);

    T edit(T entity);

    T getById(ID id);

    boolean delete(ID id);

    List<T> getAll();

}
