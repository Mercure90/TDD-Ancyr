package fr.futuretech.esportclash.player.application.usecases.get;

import an.awesome.pipelinr.Command;
import fr.futuretech.esportclash.core.domain.exceptions.NotFoundException;
import fr.futuretech.esportclash.player.application.port.PlayerRepository;
import fr.futuretech.esportclash.player.domain.viewmodel.PlayerViewModel;

public class GetPlayerByIdCommandHandler implements Command.Handler<GetPlayerByIdCommand, PlayerViewModel>{
    private final PlayerRepository playerRepository;
    public GetPlayerByIdCommandHandler(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }


    @Override
    public PlayerViewModel handle(GetPlayerByIdCommand command) {
        var player = playerRepository
                .findById(command.getId())
                .orElseThrow(
                () -> new NotFoundException("Player", command.getId())
                );
        return new PlayerViewModel(player.getId(), player.getName());
    }
}
