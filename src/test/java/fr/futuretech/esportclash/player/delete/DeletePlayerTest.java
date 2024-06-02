package fr.futuretech.esportclash.player.delete;


import fr.futuretech.esportclash.core.domain.exceptions.NotFoundException;
import fr.futuretech.esportclash.player.application.usecases.delete.DeletePlayerCommand;
import fr.futuretech.esportclash.player.application.usecases.delete.DeletePlayerCommandHandler;
import fr.futuretech.esportclash.player.domain.model.Player;
import fr.futuretech.esportclash.player.infrastructure.persistance.ram.InMemoryPlayerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DeletePlayerTest {
    private final InMemoryPlayerRepository playerRepository = new InMemoryPlayerRepository();

    private DeletePlayerCommandHandler createHandler(){
        return new DeletePlayerCommandHandler(playerRepository);
    }


    @Test
    void ShouldDeletePlayer(){
        var player = new Player("123","name");
        playerRepository.save(player);
        var commandHandler = createHandler();

        var command = new DeletePlayerCommand(player.getId());
        commandHandler.handle(command);
        var actualPlayerQuery = playerRepository.findById(player.getId());

        Assertions.assertTrue(actualPlayerQuery.isEmpty());

    }

    @Test
    void whenPlayerDoesNotExists_ShouldThrowNotFound() {
        var command = new DeletePlayerCommand("garbage");

        var commandHandler = createHandler();

        var exception = Assertions.assertThrows(NotFoundException.class, () -> {
            commandHandler.handle(command);
        });
        Assertions.assertEquals("Player with key garbage not found",exception.getMessage());
    }
}
