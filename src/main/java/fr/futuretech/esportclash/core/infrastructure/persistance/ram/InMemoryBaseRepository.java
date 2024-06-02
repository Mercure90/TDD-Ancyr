package fr.futuretech.esportclash.core.infrastructure.persistance.ram;

import fr.futuretech.esportclash.core.domain.model.BaseEntity;
import fr.futuretech.esportclash.core.infrastructure.persistance.BaseRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public abstract class InMemoryBaseRepository<T extends BaseEntity> implements BaseRepository<T> {
    private final Map<String, T> entities = new HashMap<>();
    @Override
    public Optional<T> findById(String id) {
        return Optional.ofNullable(entities.get(id));
    }

    @Override
    public void save(T entity) {
        entities.put(entity.getId(), entity);
    }

    @Override
    public void delete(T entity) {
        entities.remove(entity.getId());
    }
}
