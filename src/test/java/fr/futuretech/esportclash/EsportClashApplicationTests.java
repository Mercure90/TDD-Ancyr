package fr.futuretech.esportclash;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(PostgreSQLTestConfiguration.class)
class EsportClashApplicationTests {

    @Test
    void contextLoads() {
    }

}
