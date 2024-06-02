package fr.futuretech.esportclash.player.application.usecases.create;

import an.awesome.pipelinr.Command;
import fr.futuretech.esportclash.player.domain.viewmodel.IdResponse;

public class CreatePlayerCommand implements Command<IdResponse> {
    private String name;


    public CreatePlayerCommand(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
