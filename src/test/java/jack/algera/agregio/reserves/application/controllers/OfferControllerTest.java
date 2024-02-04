package jack.algera.agregio.reserves.application.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.util.List;
import jack.algera.agregio.reserves.application.configurations.AdaptersConfiguration;
import jack.algera.agregio.reserves.application.models.CreateOfferRequest;
import jack.algera.agregio.reserves.domain.models.Market;
import jack.algera.agregio.reserves.fixtures.Fixtures;
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

@WebMvcTest(OfferController.class)
@Import(AdaptersConfiguration.class)
class OfferControllerTest {

  private final ObjectMapper MAPPER = new ObjectMapper().registerModule(new JavaTimeModule());

  @Autowired
  private MockMvc mockMvc;

  @Test
  void testCreateOffer() throws Exception {
    CreateOfferRequest request = CreateOfferRequest.builder()
        .market(Market.RESERVE_PRIMAIRE.toString())
        .offerBlocks(List.of(Fixtures.OFFER_BLOCK_DTO_1))
        .build();

    mockMvc.perform(post("/offers")
        .contentType(MediaType.APPLICATION_JSON)
        .content(MAPPER.writeValueAsString(request)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$").isNotEmpty());
  }

  @Test
  void testCreateOfferWithInvalidMarket() throws Exception {
    String market = "NON_EXISTING_MARKET";
    CreateOfferRequest request = CreateOfferRequest.builder()
        .market(market)
        .offerBlocks(List.of())
        .build();

    mockMvc.perform(post("/offers")
        .contentType(MediaType.APPLICATION_JSON)
        .content(MAPPER.writeValueAsString(request)))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message").value(String.format("Invalid market: %s", market)));
  }

  @Test
  void testGetOffers() throws Exception {
    mockMvc.perform(get("/offers")
        .param("market", Market.RESERVE_PRIMAIRE.toString())
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isNotEmpty());
  }

  @Test
  void testGetNoOffers() throws Exception {
    mockMvc.perform(get("/offers")
        .param("market", Market.RESERVE_SECONDAIRE.toString())
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isEmpty());
  }

  @Test
  void testGetOffersForNonExistingMarket() throws Exception {
    String market = "NON_EXISTING_MARKET";
    mockMvc.perform(get("/offers")
        .param("market", market)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message").value(String.format("Invalid market: %s", market)));
  }
}
