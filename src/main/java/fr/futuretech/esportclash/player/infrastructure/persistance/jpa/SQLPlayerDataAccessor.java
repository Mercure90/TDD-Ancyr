package fr.futuretech.esportclash.player.infrastructure.persistance.jpa;

import fr.futuretech.esportclash.player.domain.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SQLPlayerDataAccessor extends JpaRepository<Player, String> {
}
