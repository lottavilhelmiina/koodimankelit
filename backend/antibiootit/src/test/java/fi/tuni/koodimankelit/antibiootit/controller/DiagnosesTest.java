package fi.tuni.koodimankelit.antibiootit.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.JsonNode;

import fi.tuni.koodimankelit.antibiootit.database.data.DiagnoseInfo;
import fi.tuni.koodimankelit.antibiootit.models.Diagnoses;

/**
 * Tests /diagnoses endpoint HTTP requests and responses
 */
public class DiagnosesTest extends AntibioticsControllerTest {

    private static final String ADDRESS = "/api/antibiotics/diagnoses";
    
    @Test
    public void diagnosesShouldReturnList() throws Exception {

        DiagnoseInfo diagnose1 = new DiagnoseInfo(
            "Code 1", "Diagnose 1", "Etiology 1", new ArrayList<>());
        DiagnoseInfo diagnose2 = new DiagnoseInfo(
            "Code 2", "Diagnose 2", "Etiology 2", new ArrayList<>());
        List<DiagnoseInfo> diagnoseInfos = Arrays.asList(diagnose1, diagnose2);
        Diagnoses diagnoses = new Diagnoses(diagnoseInfos);
        
        // Mock response
        when(service.getAllDiagnoseInfos()).thenReturn(diagnoses);

        MvcResult result = mockMvc.perform(
            // Request
            get(ADDRESS))

            // Response is ok
            .andExpect(status().isOk())
            .andReturn();

        // Response has a list called "diagnoseInfos"
        JsonNode actualResponse = 
            jsonMapper.readTree(result.getResponse().getContentAsString());
        assertTrue(actualResponse.has("diagnoseInfos"));
        assertTrue(actualResponse.get("diagnoseInfos").isArray());

    }

    @Test
    public void runtimeExceptionShouldReturn500() throws Exception {

        // Mock service to throw RuntimeException
        when(service.getAllDiagnoseInfos())
            .thenThrow(RuntimeException.class);

        // Unable to connect to database
        mockMvc.perform(get(ADDRESS))
        .andExpect(status().isInternalServerError())
        .andReturn();
    }
}
