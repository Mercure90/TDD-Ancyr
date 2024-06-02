package fr.futuretech.esportclash.player.infrastructure.spring;

import fr.futuretech.esportclash.player.application.port.PlayerRepository;
import fr.futuretech.esportclash.player.application.usecases.create.CreatePlayerCommandHandler;
import fr.futuretech.esportclash.player.application.usecases.delete.DeletePlayerCommandHandler;
import fr.futuretech.esportclash.player.application.usecases.get.GetPlayerByIdCommandHandler;
import fr.futuretech.esportclash.player.application.usecases.rename.RenamePlayerCommandHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PlayerUseCaseConfiguration {
    @Bean
    public CreatePlayerCommandHandler createPlayerUseCase(PlayerRepository playerRepository) {
        return new CreatePlayerCommandHandler(playerRepository);
    }
    @Bean
    public RenamePlayerCommandHandler renamePlayerUseCase(PlayerRepository playerRepository) {
        return new RenamePlayerCommandHandler(playerRepository);
    }
    @Bean
    public DeletePlayerCommandHandler deletePlayerUseCase(PlayerRepository playerRepository) {
        return new DeletePlayerCommandHandler(playerRepository);
    }
    @Bean
    public GetPlayerByIdCommandHandler getPlayerUseCase(PlayerRepository playerRepository) {
        return new GetPlayerByIdCommandHandler(playerRepository);
    }

}
