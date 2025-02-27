package id.ac.ui.cs.advprog.eshop.repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public abstract class GenericRepository<T> implements IRepository<T> {
    private List<T> dataList = new ArrayList<>();

    protected abstract String getId(T entity);
    protected abstract void setId(T entity, String id);

    @Override
    public T create(T entity) {
        if (getId(entity) == null || getId(entity).isEmpty()) {
            setId(entity, UUID.randomUUID().toString());
        }
        dataList.add(entity);
        return entity;
    }

    @Override
    public Iterator<T> findAll() {
        return dataList.iterator();
    }

    @Override
    public T findById(String id) {
        if (id == null) return null;
        return dataList.stream()
                .filter(entity -> id.equals(getId(entity)))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void update(String id, T updatedEntity) {
        for (int i = 0; i < dataList.size(); i++) {
            if (getId(dataList.get(i)).equals(id)) {
                setId(updatedEntity, id);
                dataList.set(i, updatedEntity);
                return;
            }
        }
        throw new IllegalArgumentException("Entity with ID " + id + " not found");
    }

    @Override
    public void delete(String id) {
        if (id != null) {
            dataList.removeIf(entity -> id.equals(getId(entity)));
        }
    }
}

