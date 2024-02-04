package jack.algera.agregio.reserves.application.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import jack.algera.agregio.reserves.application.configurations.AdaptersConfiguration;
import jack.algera.agregio.reserves.application.models.CreateParcRequest;
import jack.algera.agregio.reserves.domain.models.EnergyType;
import jack.algera.agregio.reserves.domain.models.Market;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ParcController.class)
@Import(AdaptersConfiguration.class)
class ParcControllerTest {

  private final ObjectMapper MAPPER = new ObjectMapper();

  @Autowired
  private MockMvc mockMvc;

  @Test
  void testCreateParc() throws Exception {
    CreateParcRequest request = new CreateParcRequest(EnergyType.SOLAR.name());

    mockMvc.perform(post("/parcs")
        .contentType(MediaType.APPLICATION_JSON)
        .content(MAPPER.writeValueAsString(request)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.energyType").value(request.energyType().toString()));
  }

  @Test
  void testCreateParcWithInvalidEnergyType() throws Exception {
    CreateParcRequest request = new CreateParcRequest("INVALID_ENERGY_TYPE");

    mockMvc.perform(post("/parcs")
        .contentType(MediaType.APPLICATION_JSON)
        .content(MAPPER.writeValueAsString(request)))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message").value("Invalid energy type: INVALID_ENERGY_TYPE"));
  }

  @Test
  void testGetParcsWithMarket() throws Exception {
    mockMvc.perform(get("/parcs")
        .param("market", Market.RESERVE_PRIMAIRE.toString())
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isNotEmpty());
  }

  @Test
  void testGetNoParcsWithMarket() throws Exception {
    mockMvc.perform(get("/parcs")
        .param("market", Market.RESERVE_SECONDAIRE.toString())
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isEmpty());
  }

  @Test
  void testGetParcsForNonExistingMarket() throws Exception {
    String market = "NON_EXISTING_MARKET";
    mockMvc.perform(get("/parcs")
        .param("market", market)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message").value(String.format("Invalid market: %s", market)));
  }
}
