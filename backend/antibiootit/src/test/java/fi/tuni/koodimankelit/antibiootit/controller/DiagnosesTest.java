package fi.tuni.koodimankelit.antibiootit.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.JsonNode;

import fi.tuni.koodimankelit.antibiootit.database.data.DiagnosisInfo;
import fi.tuni.koodimankelit.antibiootit.models.Diagnoses;

/**
 * Tests /diagnoses endpoint HTTP requests and responses
 */
public class DiagnosesTest extends AntibioticsControllerTest {

    private static final String ADDRESS = "/api/antibiotics/diagnoses";
    
    @Test
    public void diagnosesShouldReturnList() throws Exception {

        DiagnosisInfo diagnosis1 = new DiagnosisInfo(
            "Code 1", "Diagnosis 1", "Etiology 1", new ArrayList<>(), true);
        DiagnosisInfo diagnosis2 = new DiagnosisInfo(
            "Code 2", "Diagnosis 2", "Etiology 2", new ArrayList<>(), false);
        List<DiagnosisInfo> diagnosisInfos = Arrays.asList(diagnosis1, diagnosis2);
        Diagnoses diagnoses = new Diagnoses(diagnosisInfos);
        
        // Mock response
        when(service.getAllDiagnoseInfos()).thenReturn(diagnoses);

        MvcResult result = mockMvc.perform(
            // Request
            get(ADDRESS))

            // Response is ok
            .andDo(print())
            .andExpect(status().isOk())
            .andReturn();

        // Response has a list called "diagnosisInfos"
        JsonNode actualResponse = 
            jsonMapper.readTree(result.getResponse().getContentAsString());
        assertTrue(actualResponse.has("diagnosisInfos"));
        assertTrue(actualResponse.get("diagnosisInfos").isArray());

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
