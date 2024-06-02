package fr.futuretech.esportclash.player.application.usecases.rename;

import an.awesome.pipelinr.Command;
import fr.futuretech.esportclash.core.domain.exceptions.NotFoundException;
import fr.futuretech.esportclash.player.application.port.PlayerRepository;

public class RenamePlayerCommandHandler implements Command.Handler<RenamePlayerCommand, Void>{
    private final PlayerRepository playerRepository;

    public RenamePlayerCommandHandler(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public Void handle(RenamePlayerCommand command) {
        var player = playerRepository
                .findById(command.getId())
                .orElseThrow(
                        () -> new NotFoundException("Player", command.getId())
                );

        player.rename(command.getName());
        playerRepository.save(player);
        return null;
    }
}
