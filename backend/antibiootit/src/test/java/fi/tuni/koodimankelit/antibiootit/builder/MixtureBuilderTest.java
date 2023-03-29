package fi.tuni.koodimankelit.antibiootit.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fi.tuni.koodimankelit.antibiootit.database.data.DoseMultiplier;
import fi.tuni.koodimankelit.antibiootit.database.data.Mixture;
import fi.tuni.koodimankelit.antibiootit.database.data.Strength;
import fi.tuni.koodimankelit.antibiootit.models.AntibioticTreatment;
import fi.tuni.koodimankelit.antibiootit.models.DosageFormula;
import fi.tuni.koodimankelit.antibiootit.models.DosageResult;
import fi.tuni.koodimankelit.antibiootit.models.Instructions;
import fi.tuni.koodimankelit.antibiootit.models.Measurement;
import fi.tuni.koodimankelit.antibiootit.models.StrengthMeasurement;

/**
 * Test class for MixtureBuilder
 */
public class MixtureBuilderTest {
    

    private List<Strength> strengths = new ArrayList<>();
    private List<DoseMultiplier> multipliers = new ArrayList<>();

    private Mixture mixture = new Mixture(
        "antibiotic", "format", "info", 3000,
        strengths,
        "weightUnit", 10, 3, "resultUnit", 40, "dosagePerWeightPerDayUnit",
        multipliers
    );

    @BeforeEach
    public void populateStrengths() {
        strengths.clear();
        strengths.add(new Strength(100, 0, null, null));
        strengths.add(new Strength(120, 10, "strengthUnit", "strengthText"));
        strengths.add(new Strength(140, 20, null, null));
        strengths.add(new Strength(160, 30, null, null));
    }

    @BeforeEach
    public void populateMultipliers() {
        multipliers.clear();
        multipliers.add(new DoseMultiplier(0, 1));
        multipliers.add(new DoseMultiplier(1, 2));
    }
    
    /**
     * Test that correct strength is selected from populated list
     */
    @Test
    public void testCorrectStrengthIsSelected() {
        assertEquals(100, getTreatmentStrength(0));
        assertEquals(100, getTreatmentStrength(9.999));
        assertEquals(120, getTreatmentStrength(10));
        assertEquals(120, getTreatmentStrength(19.999));
        assertEquals(160, getTreatmentStrength(50));
    }

    /**
     * Test that negative weight results to exception
     */
    @Test
    public void testNegativeWeightThrowsException() {
        assertThrows(RuntimeException.class, () -> getTreatment(-1));
    }

    /**
     * Test that empty strength list results to exception with valid weight
     */
    @Test
    public void testEmptyStrengthListThrowsException() {
        ArrayList<Strength> emptyStrengths = new ArrayList<>();
        MixtureBuilder builder = new MixtureBuilder(
            new Mixture(null, null, null, 0, emptyStrengths, null, 0, 0, null, 0, null, multipliers),
            10);
        assertThrows(RuntimeException.class, () -> builder.build());
    }

    /**
     * Test that result has correct fields which do not require calculation
     */
    @Test
    public void testCorrectLabels() {
        AntibioticTreatment treatment = getTreatment(10);

        assertEquals("antibiotic", treatment.getAntibiotic());
        assertEquals("format", treatment.getFormat());
        assertEquals("info", treatment.getDescription());
    }

    /**
     * Test that instructions are correct
     */
    @Test
    public void testCorrectInstructions() {
        AntibioticTreatment treatment = getTreatment(10);
        Instructions instructions = treatment.getInstructions();

        assertEquals(10, instructions.getDays());
        assertEquals(3, instructions.getDosesPerDay());

        List<DoseMultiplier> multipliers = instructions.getDoseMultipliers();
        assertEquals(2, multipliers.size());

        assertEquals(0, multipliers.get(0).getId());
        assertEquals(1, multipliers.get(0).getMultiplier());
        assertEquals(1, multipliers.get(1).getId());
        assertEquals(2, multipliers.get(1).getMultiplier());
    }

    /**
     * Test that formula is correct
     */
    @Test
    public void testCorrectFormula() {

        // 10 kg -> Strength{120, 10} should be chosen.
        AntibioticTreatment treatment = getTreatment(10); 
        DosageFormula dosageFormula = treatment.getDosageFormula();
        Measurement dosage = dosageFormula.getDosage();
        StrengthMeasurement strengthMeasurement = dosageFormula.getStrength();

        assertEquals("dosagePerWeightPerDayUnit", dosage.getUnit());
        assertEquals(40, dosage.getValue());

        assertEquals("strengthUnit", strengthMeasurement.getUnit());
        assertEquals("strengthText", strengthMeasurement.getText());
        assertEquals(120, strengthMeasurement.getValue());
    }

    /**
     * Test that the rounded result is correct
     */
    @Test
    public void testRoundedResults() {
        // 22 kg -> Strength{140, 20} should be chosen.
        // ( 22 kg * 40 mg/kg/d ) / ( 140 mg/ml ) / ( 3 times each day ) = 2.095
        // Should be rounded to 2.0
        assertEquals(2.0, getRoundedResult(22));

        // 39 kg -> Strength{160, 30} should be chosen.
        // ( 39 kg * 40 mg/kg/d ) / ( 160 mg/ml ) / ( 3 times each day ) = 3.25
        // Should be rounded to 3.5
        assertEquals(3.5, getRoundedResult(39));

        // 8.79 kg -> Strength{100, 0} should be chosen.
        // ( 8.79 kg * 40 mg/kg/d ) / ( 100 mg/ml ) / ( 3 times each day ) = 1.172
        // Should be rounded to 1
        assertEquals(1, getRoundedResult(8.79));
    }

    /**
     * Test that the non-rounded result is correct (3 decimals)
     */
    @Test
    public void testAccurateResults() {
        // Explanation in the test above
        assertEquals(2.095, getAccurateResult(22));

        assertEquals(3.250, getAccurateResult(39));

        assertEquals(1.172, getAccurateResult(8.79));
    }

    /**
     * Test that the rounding works correctly
     */
    @Test
    public void testResultRounding() {

        // Custom mixture with simple values
        // ( x kg * 100 mg/kg/d ) / ( 100 mg/ml ) / ( 1 time each day ) = x ml
        List<Strength> s = new ArrayList<>();
        s.add(new Strength(100, 0, null, null));
        Mixture m = new Mixture(null, null, null, 1000, s, null, 1, 1, null, 100, null, null);

        DosageResult result;
        // 1.000 is rounded to 1.0
        result = new MixtureBuilder(m, 1).build().getDosageResult();
        assertEquals(1.000, result.getAccurateDose().getValue());
        assertEquals(1.0, result.getDose().getValue());

        // 1.001 is rounded to 1.0
        result = new MixtureBuilder(m, 1.001).build().getDosageResult();
        assertEquals(1.001, result.getAccurateDose().getValue());
        assertEquals(1.0, result.getDose().getValue());

        // 1.249 is rounded to 1.0
        result = new MixtureBuilder(m, 1.249).build().getDosageResult();
        assertEquals(1.249, result.getAccurateDose().getValue());
        assertEquals(1.0, result.getDose().getValue());

        // 1.250 is rounded to 1.5
        result = new MixtureBuilder(m, 1.250).build().getDosageResult();
        assertEquals(1.250, result.getAccurateDose().getValue());
        assertEquals(1.5, result.getDose().getValue());

        // 1.500 is rounded to 1.5
        result = new MixtureBuilder(m, 1.5).build().getDosageResult();
        assertEquals(1.500, result.getAccurateDose().getValue());
        assertEquals(1.5, result.getDose().getValue());

        // 1.750 is rounded to 2.0
        result = new MixtureBuilder(m, 1.750).build().getDosageResult();
        assertEquals(1.750, result.getAccurateDose().getValue());
        assertEquals(2.0, result.getDose().getValue());
    }

    /**
     * Test that result unit is correct
     */
    @Test
    public void testCorrectResultUnit() {
        assertEquals("resultUnit", getTreatment(20).getDosageResult().getDose().getUnit());
    }

    /**
     * Test max dose limit for the antibiotic
     */
    @Test
    public void testMaxLimit() {
        // Max dose is 3000 mg/d. With 40 mg/kg/d dosage the max weight is 75 kg
        // After that weight limit, the dose should be calculated using 3000 mg/d regardless of weight

        // Selected strength is 160 mg/ml with all weights over 30 kg
        // When limit is reached, ( 3000 mg/d ) / ( 160 mg/ml ) / ( 3 times a day ) = 6.25

        assertTrue(getAccurateResult(60) < 6.25);
        assertTrue(getAccurateResult(74.9) < 6.25);
        assertEquals(6.25, getAccurateResult(75));
        assertEquals(6.25, getAccurateResult(75.1));
        assertEquals(6.25, getAccurateResult(80));
        assertEquals(6.25, getAccurateResult(100000));
    }

    private AntibioticTreatment getTreatment(double weight) {
        return new MixtureBuilder(mixture, weight).build();
    }

    private double getTreatmentStrength(double weight) {
        return getTreatment(weight).getDosageFormula().getStrength().getValue();
    }

    private double getRoundedResult(double weight) {
        AntibioticTreatment treatment = getTreatment(weight);
        DosageResult result = treatment.getDosageResult();

        return result.getDose().getValue();
    }

    private double getAccurateResult(double weight) {
        AntibioticTreatment treatment = getTreatment(weight);
        DosageResult result = treatment.getDosageResult();

        return result.getAccurateDose().getValue();
    }

}
