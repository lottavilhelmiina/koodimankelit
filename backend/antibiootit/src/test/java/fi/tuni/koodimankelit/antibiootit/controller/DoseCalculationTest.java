package fi.tuni.koodimankelit.antibiootit.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.ArrayList;

import fi.tuni.koodimankelit.antibiootit.database.data.DiagnoseInfo;
import fi.tuni.koodimankelit.antibiootit.exceptions.InvalidParameterException;
import fi.tuni.koodimankelit.antibiootit.models.DiagnoseResponse;
import fi.tuni.koodimankelit.antibiootit.models.request.InfectionSelection;
import fi.tuni.koodimankelit.antibiootit.models.request.Parameters;



/**
 * Tests /dose-calculation endpoint HTTP requests and responses
 */
public class DoseCalculationTest extends AntibioticsControllerTest {

    private static final Parameters mockParameters = new Parameters("J03.0", 35.5, false, new ArrayList<InfectionSelection>());

    @Test
    public void shouldReturnDiagnoseResponse() throws Exception {

        // Mock needed methods
        when(service.calculateTreatments(any()))
        .thenReturn(
            new DiagnoseResponse("diagnosisResponseID", "etiology")
        );

        when(service.getDiagnoseInfoByID(any()))
        .thenReturn(new DiagnoseInfo("diagnosisID", "name", "etiology", new ArrayList<>()));

        // Actual test
        mockMvc.perform(
            // Request
            post("/api/antibiotics/dose-calculation")
            .headers(getHeaders())
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonMapper.writeValueAsString(mockParameters))

        // Response
        ).andDo(print()).andExpect(status().isOk())
        .andExpect(jsonPath("$._id").value("diagnosisResponseID"))
        .andExpect(jsonPath("$.etiology").value("etiology"))
        .andReturn();
    }

    @Test
    public void failedValidationShouldReturn400() throws Exception {

        when(service.calculateTreatments(any()))
        .thenReturn(null);

        when(service.getDiagnoseInfoByID(any()))
        .thenReturn(new DiagnoseInfo(null, null, null, null));

        Mockito.doThrow(new InvalidParameterException(null)).when(validator).validate(any(), any());

        mockMvc.perform(
            // Request
            post("/api/antibiotics/dose-calculation")
            .headers(getHeaders())
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonMapper.writeValueAsString(mockParameters))

        // Response
        ).andDo(print())
        .andExpect(status().isBadRequest())
        .andReturn();
    }
}
