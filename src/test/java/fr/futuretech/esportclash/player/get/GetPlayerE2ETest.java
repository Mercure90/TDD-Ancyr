package fr.futuretech.esportclash.player.get;

import fr.futuretech.esportclash.PostgreSQLTestConfiguration;
import fr.futuretech.esportclash.player.application.port.PlayerRepository;
import fr.futuretech.esportclash.player.domain.model.Player;
import org.junit.jupiter.api.Assertions;
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
public class GetPlayerE2ETest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PlayerRepository playerRepository;


    @Test
    void ShouldGetPlayerById() throws Exception {
        var existingPlayer = new Player("123", "name");
        playerRepository.save(existingPlayer);

        var result = mockMvc
                .perform(MockMvcRequestBuilders.get(
                        "/players/" + existingPlayer.getId()))
                        .andReturn();
        var viewModel = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                Player.class
        );
        Assertions.assertEquals(existingPlayer.getId(), viewModel.getId());
        Assertions.assertEquals(existingPlayer.getName(), viewModel.getName());

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
