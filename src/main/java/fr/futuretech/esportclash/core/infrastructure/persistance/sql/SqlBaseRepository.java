package fr.futuretech.esportclash.core.infrastructure.persistance.sql;

import fr.futuretech.esportclash.core.domain.model.BaseEntity;
import fr.futuretech.esportclash.core.infrastructure.persistance.BaseRepository;
import jakarta.persistence.EntityManager;

import java.util.Optional;

public abstract class SqlBaseRepository <T extends BaseEntity> implements BaseRepository<T> {
    private final EntityManager entityManager;

    public SqlBaseRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(T entity) {
        entityManager.persist(entity);

    }

    @Override
    public void delete(T entity) {
        entityManager.remove(entity);

    }

    @Override
    public Optional<T> findById(String id) {
        return Optional.ofNullable(entityManager.find((Class<T>) BaseEntity.class, id));
    }
}
