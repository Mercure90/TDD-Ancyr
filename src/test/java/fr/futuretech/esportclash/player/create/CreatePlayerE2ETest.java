package fr.futuretech.esportclash.player.create;

import fr.futuretech.esportclash.PostgreSQLTestConfiguration;
import fr.futuretech.esportclash.player.application.port.PlayerRepository;
import fr.futuretech.esportclash.player.domain.viewmodel.IdResponse;
import fr.futuretech.esportclash.player.infrastructure.spring.CreatePlayerDTO;
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
public class CreatePlayerE2ETest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PlayerRepository playerRepository;


    @Test
    void ShouldCreatePlayer() throws Exception {
        var createPlayerDTO = new CreatePlayerDTO("player");

        var response = mockMvc
                .perform(MockMvcRequestBuilders.post("/players")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(createPlayerDTO)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        var idResponse = objectMapper.readValue(
                response.getResponse().getContentAsString(),
                IdResponse.class
        );

        var playerQuery = playerRepository.findById(idResponse.getId());
        var player = playerQuery.get();

        Assert.assertNotNull(player);
        Assert.assertEquals(createPlayerDTO.getName(), player.getName());

    }
}
