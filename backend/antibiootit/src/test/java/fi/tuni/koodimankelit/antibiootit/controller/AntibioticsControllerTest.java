package fi.tuni.koodimankelit.antibiootit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import fi.tuni.koodimankelit.antibiootit.controllers.AntibioticsController;
import fi.tuni.koodimankelit.antibiootit.services.AntibioticsService;
import fi.tuni.koodimankelit.antibiootit.validator.CheckBoxValidator;


/**
 * Base class for Controller tests. Mocks needed services and provides headers.
 * Used to test for example HTTP codes and HTTP response content 
 */
@WebMvcTest(AntibioticsController.class)
@PropertySource(value = "classpath:secrets.properties", ignoreResourceNotFound = true)
public abstract class AntibioticsControllerTest {
    
    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    protected AntibioticsService service;

    @MockBean
    protected CheckBoxValidator validator;

    protected ObjectMapper jsonMapper = new ObjectMapper();


    // Attributes

    @Value("${apikey}")
    private String API_KEY;

    private String API_KEY_HEADER = "X-API-KEY";

    protected HttpHeaders getHeaders() {
        HttpHeaders headers =  new HttpHeaders();
        headers.add(API_KEY_HEADER, API_KEY);
        
        return headers;
    }
}
