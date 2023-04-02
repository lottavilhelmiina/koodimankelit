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

public abstract class BuilderTest {
    

    protected List<Strength> strengths = new ArrayList<>();
    protected List<DoseMultiplier> multipliers = new ArrayList<>();

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
        AntibioticTreatmentBuilder builder = getEmptyBuilderWithStrengths(emptyStrengths, 10);
        assertThrows(RuntimeException.class, () -> builder.build());
    }


    /**
     * Return simple AntiobiticTreatment object with given weight
     * @param weight patient's weight
     * @return AntibioticTreatment
     */
    protected abstract AntibioticTreatment getTreatment(double weight);

    protected abstract AntibioticTreatmentBuilder getEmptyBuilderWithStrengths(List<Strength> strengths, double weight);

    protected double getTreatmentStrength(double weight) {
        return getTreatment(weight).getDosageFormula().getStrength().getValue();
    }
}
