package id.ac.ui.cs.advprog.eshop.repository;

import java.util.Iterator;

public interface IRepository<T> {
    T create(T entity);
    Iterator<T> findAll();
    T findById(String id);
    void update(String id, T updatedEntity);
    void delete(String id);
}
