package dao;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {
    Optional<T> get(long id);

    List<T> getAll();

    Long create(T item);

    Long update(T item);

    void delete(T item);
}
