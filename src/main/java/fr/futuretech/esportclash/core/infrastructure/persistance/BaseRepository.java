package fr.futuretech.esportclash.core.infrastructure.persistance;

import fr.futuretech.esportclash.core.domain.model.BaseEntity;

import java.util.Optional;

public interface BaseRepository<T extends BaseEntity> {
    public void save(T entity);
    public void delete(T entity);
    public Optional<T> findById(String id);
}
