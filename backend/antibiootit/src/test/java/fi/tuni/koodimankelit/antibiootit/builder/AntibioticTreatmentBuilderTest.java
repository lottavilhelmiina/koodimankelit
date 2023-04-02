package fi.tuni.koodimankelit.antibiootit.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fi.tuni.koodimankelit.antibiootit.database.data.DoseMultiplier;
import fi.tuni.koodimankelit.antibiootit.database.data.Strength;
import fi.tuni.koodimankelit.antibiootit.models.AntibioticTreatment;
import fi.tuni.koodimankelit.antibiootit.models.Instructions;

public abstract class AntibioticTreatmentBuilderTest {
    

    protected String antibiotic =  "antibiotic";
    protected String format = "format";
    protected String info = "info";
    protected String weightUnit = "kg";
    protected String strengthText = "strengthText";

    protected int days = 10;
    protected int dosesPerDay = 3;

    protected List<Strength> strengths = new ArrayList<>();
    protected List<DoseMultiplier> doseMultipliers = new ArrayList<>();

    @BeforeEach
    public abstract void populateStrengths();

    @BeforeEach
    public void populateMultipliers() {
        doseMultipliers.clear();
        doseMultipliers.add(new DoseMultiplier(0, 1));
        doseMultipliers.add(new DoseMultiplier(1, 2));
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
        AntibioticTreatmentBuilder builder = getBuilderWithStrengths(emptyStrengths, 10);
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
    public abstract void testCorrectFormula();


    /**
     * Return simple AntiobiticTreatment object with given weight
     * @param weight patient's weight
     * @return AntibioticTreatment
     */
    protected abstract AntibioticTreatment getTreatment(double weight);

    protected abstract AntibioticTreatmentBuilder getBuilderWithStrengths(List<Strength> strengths, double weight);

    protected double getTreatmentStrength(double weight) {
        return getTreatment(weight).getDosageFormula().getStrength().getValue();
    }
}
