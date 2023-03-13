package fi.tuni.koodimankelit.antibiootit.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import fi.tuni.koodimankelit.antibiootit.authentication.ApiKeyFilter;
import fi.tuni.koodimankelit.antibiootit.models.Diagnoses;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class AuthorizationTest extends AntibioticsControllerTest {
    
    private final static String ADDRESS = "/api/antibiotics/diagnoses";
    
    @Autowired
    private WebApplicationContext context;


    @BeforeEach
    public void mockService() {
        when(service.getAllDiagnoseInfos()).thenReturn(new Diagnoses(null));
        MockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(context).addFilters(new ApiKeyFilter("apikey"));
        mockMvc = builder.build();
    }

    @Test
    public void authorizedShouldReturn200() throws Exception {

        mockMvc.perform(
            get(ADDRESS)
            .header("X-API-KEY", "apikey") 
        ).andExpect(status().isOk());
    }

    @Test
    public void unAuthorizedShouldReturn401() throws Exception {

        mockMvc.perform(
            get(ADDRESS)
            .header("X-API-KEY", "false-apikey")
        ).andDo(print())
        .andExpect(status().isUnauthorized());
    }

    @Test
    public void missingHeaderShouldReturn401() throws Exception {

        mockMvc.perform(
            get(ADDRESS)
        ).andDo(print())
        .andExpect(status().isUnauthorized());
    }

    @Test
    public void wrongAuthorizationShouldReturn401() throws Exception {

        mockMvc.perform(
            get(ADDRESS)
            .header("Authorization", "Basic someValue")
        ).andDo(print())
        .andExpect(status().isUnauthorized());
    }

}