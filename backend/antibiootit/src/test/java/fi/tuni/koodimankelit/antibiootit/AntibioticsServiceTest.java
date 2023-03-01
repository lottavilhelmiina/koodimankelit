package fi.tuni.koodimankelit.antibiootit;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import fi.tuni.koodimankelit.antibiootit.models.Parameters;
import fi.tuni.koodimankelit.antibiootit.services.AntibioticsService;

public class AntibioticsServiceTest {

    private AntibioticsService antibioticsService;

    public AntibioticsServiceTest(AntibioticsService antibioticsService) {
        this.antibioticsService = antibioticsService;
    }
    
    @Test
    public void testCalculateTreatments() {

        // Test not implemented, used to verify that GitHub CI/CD pipeline works
        Parameters params = new Parameters("test-id", 20.0, false, false);
        var result = antibioticsService.calculateTreatments(params);
        assertNotNull(result);
    }
}
