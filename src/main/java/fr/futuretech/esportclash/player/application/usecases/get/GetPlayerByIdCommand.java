package fr.futuretech.esportclash.player.application.usecases.get;

import an.awesome.pipelinr.Command;
import fr.futuretech.esportclash.player.domain.viewmodel.PlayerViewModel;

public class GetPlayerByIdCommand implements Command<PlayerViewModel> {
    private final String id;
    public GetPlayerByIdCommand(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }
}
