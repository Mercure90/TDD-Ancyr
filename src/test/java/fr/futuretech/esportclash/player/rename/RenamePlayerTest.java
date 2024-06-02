package fr.futuretech.esportclash.player.rename;


import fr.futuretech.esportclash.core.domain.exceptions.NotFoundException;
import fr.futuretech.esportclash.player.application.usecases.rename.RenamePlayerCommand;
import fr.futuretech.esportclash.player.application.usecases.rename.RenamePlayerCommandHandler;
import fr.futuretech.esportclash.player.domain.model.Player;
import fr.futuretech.esportclash.player.infrastructure.persistance.ram.InMemoryPlayerRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RenamePlayerTest {
    private final InMemoryPlayerRepository playerRepository = new InMemoryPlayerRepository();

    private RenamePlayerCommandHandler createHandler(){
        return new RenamePlayerCommandHandler(playerRepository);
    }


    @Test
    void ShouldRenamePlayer(){
        var player = new Player("123","name");
        playerRepository.save(player);
        var commandHandler = createHandler();

        var command = new RenamePlayerCommand(player.getId(),"newName");
        var actualPlayerQuery = playerRepository.findById(player.getId());
        var actualPlayer = actualPlayerQuery.get();

        commandHandler.handle(command);

        Assert.assertEquals(actualPlayer.getName(),command.getName());

    }

    @Test
    void whenPlayerDoesNotExists_ShouldThrowNotFound() {
        var command = new RenamePlayerCommand("garbage","newName");

        var commandHandler = createHandler();

        var exception = Assertions.assertThrows(NotFoundException.class, () -> {
            commandHandler.handle(command);
        });
        Assertions.assertEquals("Player with key garbage not found",exception.getMessage());
    }
}
