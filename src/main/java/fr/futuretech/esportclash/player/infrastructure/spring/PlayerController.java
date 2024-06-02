package fr.futuretech.esportclash.player.infrastructure.spring;

import an.awesome.pipelinr.Pipeline;
import fr.futuretech.esportclash.player.application.usecases.create.CreatePlayerCommand;
import fr.futuretech.esportclash.player.application.usecases.delete.DeletePlayerCommand;
import fr.futuretech.esportclash.player.application.usecases.get.GetPlayerByIdCommand;
import fr.futuretech.esportclash.player.application.usecases.rename.RenamePlayerCommand;
import fr.futuretech.esportclash.player.domain.viewmodel.IdResponse;
import fr.futuretech.esportclash.player.domain.viewmodel.PlayerViewModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/players")
public class PlayerController {
    private final Pipeline pipeline;

    public PlayerController(Pipeline pipeline) {
        this.pipeline = pipeline;
    }

    @PostMapping
    public ResponseEntity<IdResponse> createPlayer(@RequestBody CreatePlayerDTO createPlayerDTO) {
        var result = this.pipeline.send(new CreatePlayerCommand(createPlayerDTO.getName()));
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}/name")
    public ResponseEntity<Void> changePlayerName(
            @RequestBody RenamePlayerDTO renamePlayerDTO,
            @PathVariable String id
    ) {
        var result = this.pipeline.send(new RenamePlayerCommand(id,renamePlayerDTO.getName()));

        return new ResponseEntity<>( HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayer(
            @PathVariable String id
    ) {
        var result = this.pipeline.send(new DeletePlayerCommand(id));

        return new ResponseEntity<>( HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerViewModel> getPlayer(
            @PathVariable String id
    ) {
        var result = this.pipeline.send(new GetPlayerByIdCommand(id));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
