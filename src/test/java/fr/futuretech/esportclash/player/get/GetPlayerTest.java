package fr.futuretech.esportclash.player.get;


import fr.futuretech.esportclash.core.domain.exceptions.NotFoundException;
import fr.futuretech.esportclash.player.application.usecases.get.GetPlayerByIdCommand;
import fr.futuretech.esportclash.player.application.usecases.get.GetPlayerByIdCommandHandler;
import fr.futuretech.esportclash.player.domain.model.Player;
import fr.futuretech.esportclash.player.infrastructure.persistance.ram.InMemoryPlayerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GetPlayerTest {
    private final InMemoryPlayerRepository playerRepository = new InMemoryPlayerRepository();

    private GetPlayerByIdCommandHandler createHandler(){
        return new GetPlayerByIdCommandHandler(playerRepository);
    }


    @Test
    void ShouldGetPlayerById(){
        var player = new Player("123","name");
        playerRepository.save(player);
        var commandHandler = createHandler();

        var command = new GetPlayerByIdCommand(player.getId());
        var actualPlayer = commandHandler.handle(command);

        Assertions.assertEquals(player.getId(),actualPlayer.getId());
        Assertions.assertEquals(player.getName(),actualPlayer.getName());


    }

    @Test
    void whenPlayerDoesNotExists_ShouldThrowNotFound() {
        var command = new GetPlayerByIdCommand("garbage");

        var commandHandler = createHandler();

        var exception = Assertions.assertThrows(NotFoundException.class, () -> {
            commandHandler.handle(command);
        });
        Assertions.assertEquals("Player with key garbage not found",exception.getMessage());
    }
}
