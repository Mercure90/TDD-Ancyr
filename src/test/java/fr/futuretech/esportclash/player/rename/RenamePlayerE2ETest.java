package fr.futuretech.esportclash.player.rename;

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


@SpringBootTest
@AutoConfigureMockMvc
@Import(PostgreSQLTestConfiguration.class)
public class RenamePlayerE2ETest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PlayerRepository playerRepository;


    @Test
    void ShouldRenamePlayer() throws Exception {
        var existingPlayer = new Player("123", "name");
        playerRepository.save(existingPlayer);

        var dto = new RenamePlayerDTO("new name");

        mockMvc
                .perform(MockMvcRequestBuilders.patch(
                        "/players/" + existingPlayer.getId()+"/name")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(dto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();


        var playerQuery = playerRepository.findById(existingPlayer.getId());
        var player = playerQuery.get();
        Assert.assertEquals(dto.getName(), player.getName());
    }

    @Test
    void whenPlayerDoesNotExists_ShouldFail() throws Exception {
        var dto = new RenamePlayerDTO("new name");

        mockMvc
                .perform(MockMvcRequestBuilders.patch(
                        "/players/garbage/name")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(dto)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();

    }
}
