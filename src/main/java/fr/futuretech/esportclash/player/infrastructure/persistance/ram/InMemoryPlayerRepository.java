package fr.futuretech.esportclash.player.infrastructure.persistance.ram;

import fr.futuretech.esportclash.core.infrastructure.persistance.ram.InMemoryBaseRepository;
import fr.futuretech.esportclash.player.application.port.PlayerRepository;
import fr.futuretech.esportclash.player.domain.model.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryPlayerRepository extends InMemoryBaseRepository<Player> implements PlayerRepository {
    private final Map<String, Player> players = new HashMap<>();
}
