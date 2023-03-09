package fi.tuni.koodimankelit.antibiootit.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
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
            get("/api/antibiotics/diagnoses")
            .contentType(MediaType.APPLICATION_JSON)
            )

            // Response is HTTP 200
            .andExpect(status().isOk())
            .andReturn();

        // Response has a list called "diagnoseInfos"
        JsonNode actualResponse = 
            jsonMapper.readTree(result.getResponse().getContentAsString());
        assertTrue(actualResponse.has("diagnoseInfos"));
        assertTrue(actualResponse.get("diagnoseInfos").isArray());

    }
}
