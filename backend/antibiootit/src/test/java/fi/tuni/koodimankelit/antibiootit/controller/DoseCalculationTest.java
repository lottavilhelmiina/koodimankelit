package fi.tuni.koodimankelit.antibiootit.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.ArrayList;

import fi.tuni.koodimankelit.antibiootit.database.data.DiagnosisInfo;
import fi.tuni.koodimankelit.antibiootit.exceptions.InvalidParameterException;
import fi.tuni.koodimankelit.antibiootit.models.DiagnosisResponse;
import fi.tuni.koodimankelit.antibiootit.models.request.InfectionSelection;
import fi.tuni.koodimankelit.antibiootit.models.request.Parameters;



/**
 * Tests /dose-calculation endpoint HTTP requests and responses
 */
public class DoseCalculationTest extends AntibioticsControllerTest {

    private static final Parameters mockParameters = new Parameters("J03.0", 35.5, false, new ArrayList<InfectionSelection>());
    private static final String ADDRESS = "/api/antibiotics/dose-calculation";

    @Test
    public void validParametersShouldReturn200() throws Exception {

        // Mock needed methods
        when(service.calculateTreatments(any()))
        .thenReturn(
            new DiagnosisResponse("diagnosisResponseID", "etiology")
        );

        when(service.getDiagnosisInfoByID(any()))
        .thenReturn(new DiagnosisInfo("diagnosisID", "name", "etiology", new ArrayList<>(), true));

        // Actual test
        request(mockParameters)
        .andDo(print()).andExpect(status().isOk())
        .andExpect(jsonPath("$._id").value("diagnosisResponseID"))
        .andExpect(jsonPath("$.etiology").value("etiology"))
        .andReturn();
    }

    @Test
    public void emptyIDShouldReturn400() throws Exception {

        Parameters parameters = new Parameters("", 0.0, false, new ArrayList<>());
        
        request(parameters)
        .andExpect(status().isBadRequest());
    }

    @Test
    public void nullCheckBoxesShouldReturn400() throws Exception {

        Parameters parameters = new Parameters("test", 0.0, false, null);
        
        request(parameters)
        .andExpect(status().isBadRequest());
    }

    @Test
    public void missingWeightShouldReturn400() throws Exception {

        String payload = "{\"diagnosisID\":\"J03.0\",\"penicillinAllergic\":false,\"checkBoxes\":[]}";

        request(payload)
        .andExpect(status().isBadRequest());
    }

    @Test
    public void missingAllergicShouldReturn400() throws Exception {

        String payload = "{\"diagnosisID\":\"J03.0\",\"weight\":0.0,\"checkBoxes\":[]}";

        request(payload)
        .andExpect(status().isBadRequest());
    }

    @Test
    public void validatorExceptionShouldReturn400() throws Exception {

        when(service.calculateTreatments(any()))
        .thenReturn(null);

        when(service.getDiagnosisInfoByID(any()))
        .thenReturn(new DiagnosisInfo(null, null, null, null, true));

        Mockito.doThrow(new InvalidParameterException(null)).when(validator).validate(any(), any());

        request(mockParameters)
        .andDo(print())
        .andExpect(status().isBadRequest())
        .andReturn();
    }

    @Test
    public void runtimeExceptionShouldReturn500() throws Exception {

        when(service.getDiagnosisInfoByID(any())).thenThrow(RuntimeException.class);

        request(mockParameters)
        .andDo(print())
        .andExpect(status().isInternalServerError())
        .andReturn();
    }

    @Test
    public void invalidPayloadShouldReturn400() throws Exception {

        String payload = "{\"diagnosisID\":\"J03.0\",\"weight\":0.0,\"checkBoxes\":[]"; // Missing end }

        request(payload)
        .andExpect(status().isBadRequest());
    }



    private ResultActions request(Parameters parameters) throws Exception {

        return request(jsonMapper.writeValueAsString(parameters));
    }

    private ResultActions request(String payload) throws Exception {
        return mockMvc.perform(
            post(ADDRESS)
            .headers(getHeaders())
            .contentType(MediaType.APPLICATION_JSON)
            .content(payload)
        );
    }


}
