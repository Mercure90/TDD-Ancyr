package fr.futuretech.esportclash.player.infrastructure.persistance.jpa;

import fr.futuretech.esportclash.player.application.port.PlayerRepository;
import fr.futuretech.esportclash.player.domain.model.Player;

import java.util.Optional;

public class SQLPlayerRepository implements PlayerRepository {
    private final SQLPlayerDataAccessor sqlPlayerDataAccessor;

    public SQLPlayerRepository(SQLPlayerDataAccessor sqlPlayerDataAccessor) {
        this.sqlPlayerDataAccessor = sqlPlayerDataAccessor;
    }

    @Override
    public Optional<Player> findById(String id) {
        return sqlPlayerDataAccessor.findById(id);
    }

    @Override
    public void save(Player player) {
        sqlPlayerDataAccessor.save(player);
    }

    @Override
    public void delete(Player player) {
        sqlPlayerDataAccessor.deleteById(player.getId());
    }
}
