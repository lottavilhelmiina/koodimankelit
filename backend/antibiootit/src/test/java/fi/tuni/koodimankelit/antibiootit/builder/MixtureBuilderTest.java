package fi.tuni.koodimankelit.antibiootit.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        "antibiotic", "format", "info", 300,
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

    @Test
    public void testCorrectResults() {
        // 22 kg -> Strength{140, 20} should be chosen.
        // ( 22 kg * 40 mg/kg/d ) / ( 140 mg/ml ) / ( 3 times each day ) = 2.095
        // Should be rounded to 2.0
        assertEquals(2.0, getDosageResult(22));

        // 39 kg -> Strength{160, 30} should be chosen.
        // ( 39 kg * 40 mg/kg/d ) / ( 160 mg/ml ) / ( 3 times each day ) = 3.25
        // Should be rounded to 3.5
        assertEquals(3.5, getDosageResult(39));

        // 8.79 kg -> Strength{100, 0} should be chosen.
        // ( 8.79 kg * 40 mg/kg/d ) / ( 100 mg/ml ) / ( 3 times each day ) = 1.172
        // Should be rounded to 1
        assertEquals(1, getDosageResult(8.79));
    }

    /**
     * Test that result unit is correct
     */
    @Test
    public void testCorrectResultUnit() {
        assertEquals("resultUnit", getTreatment(20).getDosageResult().getDose().getUnit());
    }

    private AntibioticTreatment getTreatment(double weight) {
        return new MixtureBuilder(mixture, weight).build();
    }

    private double getTreatmentStrength(double weight) {
        return getTreatment(weight).getDosageFormula().getStrength().getValue();
    }

    private double getDosageResult(double weight) {
        AntibioticTreatment treatment = getTreatment(weight);
        DosageResult result = treatment.getDosageResult();

        return result.getDose().getValue();
    }

}
