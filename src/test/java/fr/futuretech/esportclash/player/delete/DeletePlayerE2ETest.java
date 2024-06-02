package fr.futuretech.esportclash.player.delete;

import fr.futuretech.esportclash.PostgreSQLTestConfiguration;
import fr.futuretech.esportclash.player.application.port.PlayerRepository;
import fr.futuretech.esportclash.player.domain.model.Player;
import fr.futuretech.esportclash.player.infrastructure.spring.RenamePlayerDTO;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
@AutoConfigureMockMvc
@Import(PostgreSQLTestConfiguration.class)
public class DeletePlayerE2ETest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PlayerRepository playerRepository;


    @Test
    void ShouldDeletePlayer() throws Exception {
        var existingPlayer = new Player("123", "name");
        playerRepository.save(existingPlayer);

        mockMvc
                .perform(MockMvcRequestBuilders.delete(
                        "/players/" + existingPlayer.getId())
                    .contentType(MediaType.APPLICATION_JSON)                    )
                .andExpect(MockMvcResultMatchers.status().isOk())
                ;


        var playerQuery = playerRepository.findById(existingPlayer.getId());
        assertTrue(playerQuery.isEmpty());
    }

    @Test
    void whenPlayerDoesNotExists_ShouldFail() throws Exception {

        mockMvc
                .perform(MockMvcRequestBuilders.delete(
                        "/players/garbage")
                    .contentType(MediaType.APPLICATION_JSON)
                    )
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                ;

    }
}
