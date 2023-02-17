package fi.tuni.koodimankelit.antibiootit;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import fi.tuni.koodimankelit.antibiootit.calculator.CalculatorController;
import fi.tuni.koodimankelit.antibiootit.calculator.data.Parameters;
import fi.tuni.koodimankelit.antibiootit.calculator.data.DiagnosisTreatments;



@WebMvcTest(CalculatorController.class)
public class CalculatorControllerTests {
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void shouldReturnObject() throws Exception {
        this.mockMvc.perform(
            post("/calculator")
            .contentType(MediaType.APPLICATION_JSON)
            .content(
                objectMapper.writeValueAsString(
                    new Parameters("test-id", 20.0, true, false)
                )
            )
        )
        .andDo(print())
        .andExpect(status().isOk());
    }
}
