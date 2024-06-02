package fr.futuretech.esportclash.player.application.usecases.delete;

import an.awesome.pipelinr.Command;
import fr.futuretech.esportclash.core.domain.exceptions.NotFoundException;
import fr.futuretech.esportclash.player.application.port.PlayerRepository;

public class DeletePlayerCommandHandler implements Command.Handler<DeletePlayerCommand, Void>{
    private final PlayerRepository playerRepository;

    public DeletePlayerCommandHandler(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }
    @Override
    public Void handle(DeletePlayerCommand command) {
        var player = playerRepository
                .findById(command.getId())
                .orElseThrow(
                () -> new NotFoundException("Player", command.getId())
                );
        playerRepository.delete(player);
        return null;

    }
}
