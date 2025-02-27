package id.ac.ui.cs.advprog.eshop.repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public abstract class GenericRepository<T> {
    private List<T> dataList = new ArrayList<>();

    protected abstract String getId(T entity);
    protected abstract void setId(T entity, String id);

    public T create(T entity) {
        if (getId(entity) == null || getId(entity).isEmpty()) {
            setId(entity, UUID.randomUUID().toString()); // Generate UUID jika ID kosong/null
        }
        dataList.add(entity);
        return entity;
    }

    public Iterator<T> findAll() {
        return dataList.iterator();
    }

    public T findById(String id) {
        if (id == null) return null;
        return dataList.stream()
                .filter(entity -> id.equals(getId(entity)))
                .findFirst()
                .orElse(null);
    }

    public void update(String id, T updatedEntity) {
        for (int i = 0; i < dataList.size(); i++) {
            if (getId(dataList.get(i)).equals(id)) {
                // Pastikan ID tidak berubah saat update
                setId(updatedEntity, id);
                dataList.set(i, updatedEntity);
                return;
            }
        }
        throw new IllegalArgumentException("Entity with ID " + id + " not found"); // Sesuaikan dengan test
    }

    public void delete(String id) {
        if (id != null) {
            dataList.removeIf(entity -> id.equals(getId(entity)));
        }
    }
}
