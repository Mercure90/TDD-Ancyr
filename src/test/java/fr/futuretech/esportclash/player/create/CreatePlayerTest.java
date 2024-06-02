package fr.futuretech.esportclash.player.create;


import fr.futuretech.esportclash.player.application.usecases.create.CreatePlayerCommand;
import fr.futuretech.esportclash.player.application.usecases.create.CreatePlayerCommandHandler;
import fr.futuretech.esportclash.player.domain.model.Player;
import fr.futuretech.esportclash.player.infrastructure.persistance.ram.InMemoryPlayerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreatePlayerTest {

    @Test
    void ShouldCreatePlayer(){
        var repository = new InMemoryPlayerRepository();
        var createPlayerUseCase = new CreatePlayerCommandHandler(repository);

        var command = new CreatePlayerCommand("name");

        var result = createPlayerUseCase.handle(command);
        var expectedPlayer = new Player(result.getId(), "name");

        var actualPlayerQuery = repository.findById(expectedPlayer.getId());
        var actualPlayer = actualPlayerQuery.get();

        Assertions.assertEquals(actualPlayer.getName(), expectedPlayer.getName());

    }
}
