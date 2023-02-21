package fi.tuni.koodimankelit.antibiootit;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import fi.tuni.koodimankelit.antibiootit.models.Parameters;
import fi.tuni.koodimankelit.antibiootit.services.AntibioticsService;

public class AntibioticsServiceTest {

    private static AntibioticsService calculator = new AntibioticsService();
    
    @Test
    public void testCalculateTreatments() {

        // Test not implemented, used to verify that GitHub CI/CD pipeline works
        Parameters params = new Parameters("test-id", 20.0, false, false);
        var result = calculator.calculateTreatments(params);
        assertNotNull(result);
    }
}
