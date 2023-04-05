package fi.tuni.koodimankelit.antibiootit.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fi.tuni.koodimankelit.antibiootit.models.request.Parameters;
import fi.tuni.koodimankelit.antibiootit.services.AntibioticsService;

@SpringBootTest
public class AntibioticsServiceTest {

    private AntibioticsService antibioticsService;

    @Autowired
    public AntibioticsServiceTest(AntibioticsService antibioticsService) {
        this.antibioticsService = antibioticsService;
    }
}
