package fi.tuni.koodimankelit.antibiootit.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.core.JsonProcessingException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
    private static final String ADDRESS = "/api/antibiotics/dose-calculation";

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
        request(mockParameters)
        .andDo(print()).andExpect(status().isOk())
        .andExpect(jsonPath("$._id").value("diagnosisResponseID"))
        .andExpect(jsonPath("$.etiology").value("etiology"))
        .andReturn();
    }

    @Test
    public void validatorExceptionShouldReturn400() throws Exception {

        when(service.calculateTreatments(any()))
        .thenReturn(null);

        when(service.getDiagnoseInfoByID(any()))
        .thenReturn(new DiagnoseInfo(null, null, null, null));

        Mockito.doThrow(new InvalidParameterException(null)).when(validator).validate(any(), any());

        request(mockParameters)
        .andDo(print())
        .andExpect(status().isBadRequest())
        .andReturn();
    }

    @Test
    public void runtimeExceptionShouldReturn500() throws Exception {

        when(service.getDiagnoseInfoByID(any())).thenThrow(RuntimeException.class);

        request(mockParameters)
        .andDo(print())
        .andExpect(status().isInternalServerError())
        .andReturn();
    }

    @Test
    public void failedValidationShouldReturn400() throws Exception {

    }

    private ResultActions request(Parameters parameters) throws Exception {

        return mockMvc.perform(
            post(ADDRESS)
            .headers(getHeaders())
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonMapper.writeValueAsString(parameters))
        );
    }


}
