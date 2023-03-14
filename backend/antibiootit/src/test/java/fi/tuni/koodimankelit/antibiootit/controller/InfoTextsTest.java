package fi.tuni.koodimankelit.antibiootit.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.JsonNode;

import fi.tuni.koodimankelit.antibiootit.database.data.InfoText;
import fi.tuni.koodimankelit.antibiootit.models.InfoTexts;

/**
 * Tests /info-texts endpoint HTTP requests and responses
 */
public class InfoTextsTest extends AntibioticsControllerTest {

    private static final String ADDRESS = "/api/antibiotics/info-texts";

    @Test
    public void infoTextsShouldReturnList() throws Exception {

        InfoText mockInfoText = new InfoText("id", "some information");
        List<InfoText> texts = Arrays.asList(mockInfoText);
        InfoTexts infoTexts = new InfoTexts(texts);

        // Mock response
        when(service.getAllInfoTexts()).thenReturn(infoTexts);

        MvcResult result = mockMvc.perform(
            // Request
            get(ADDRESS))

            // Response is ok
            .andDo(print())
            .andExpect(status().isOk())
            .andReturn();

        // Response has a list called "texts"
        JsonNode actualResponse = 
            jsonMapper.readTree(result.getResponse().getContentAsString());
        assertTrue(actualResponse.has("infoTexts"));
        assertTrue(actualResponse.get("infoTexts").isArray());
    }
    
}
