package fi.tuni.koodimankelit.antibiootit;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import fi.tuni.koodimankelit.antibiootit.controllers.AntibioticsController;
import fi.tuni.koodimankelit.antibiootit.services.AntibioticsService;



@WebMvcTest(AntibioticsController.class)
public class AntibioticsControllerTest {
    
    @Autowired
    private MockMvc mockMvc;


    @Test
    public void shouldReturnObject() throws Exception {
        
    }
}
