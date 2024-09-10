package ru.topjava.lunchvoter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public abstract class BaseRepository<T, ID> {
    protected final JpaRepository<T, ID> repository;

    public BaseRepository(JpaRepository<T, ID> repository) {
        this.repository = repository;
    }

    @Transactional
    public T save(T entity) {
        return repository.save(entity);
    }

    public void delete(ID id) {
        repository.deleteById(id);
    }

    public T get(ID id) {
        return repository.findById(id).orElse(null);
    }

    public T getReferenceById(ID id) {
        return repository.getReferenceById(id);
    }

    public List<T> getAll() {
        return repository.findAll();
    }
}
