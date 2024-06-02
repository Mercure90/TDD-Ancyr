package fr.futuretech.esportclash.player.infrastructure.spring;


import fr.futuretech.esportclash.player.application.port.PlayerRepository;
import fr.futuretech.esportclash.player.infrastructure.persistance.jpa.SQLPlayerDataAccessor;
import fr.futuretech.esportclash.player.infrastructure.persistance.jpa.SQLPlayerRepository;
import fr.futuretech.esportclash.player.infrastructure.persistance.ram.InMemoryPlayerRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PlayerConfiguration {
    @Bean
    public PlayerRepository playerRepository(SQLPlayerDataAccessor sqlPlayerDataAccessor) {
        return new SQLPlayerRepository(sqlPlayerDataAccessor);
    }
}
