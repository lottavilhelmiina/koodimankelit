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
import fi.tuni.koodimankelit.antibiootit.models.StrengthMeasurement;

public class MixtureBuilderTest {
    

    private List<Strength> strengths = new ArrayList<>();
    private List<DoseMultiplier> multipliers = new ArrayList<>();

    private Mixture mixture = new Mixture(
        "antibiotic", "format", "info", 300,
        strengths,
        "weightUnit", 10, 3, "resultUnit", 40, "unit",
        multipliers
    );

    @BeforeEach
    public void populateStrengths() {
        strengths.clear();
        strengths.add(new Strength(100, 0, null, null));
        strengths.add(new Strength(120, 10, null, null));
        strengths.add(new Strength(140, 20, null, null));
        strengths.add(new Strength(160, 30, null, null));
    }

    @BeforeEach
    public void populateMultipliers() {
        multipliers.clear();
        multipliers.add(new DoseMultiplier(0, 1));
        multipliers.add(new DoseMultiplier(1, 2));
    }
    
    @Test
    public void testCorrectStrengthIsSelected() {
        assertEquals(100, getTreatmentStrength(0));
        assertEquals(100, getTreatmentStrength(9.999));
        assertEquals(120, getTreatmentStrength(10));
        assertEquals(120, getTreatmentStrength(19.999));
        assertEquals(160, getTreatmentStrength(50));
    }

    @Test
    public void testNegativeWeightThrowsException() {
        assertThrows(RuntimeException.class, () -> getTreatment(-1));
    }

    @Test
    public void testEmptyStrengthListThrowsException() {
        ArrayList<Strength> emptyStrengths = new ArrayList<>();
        MixtureBuilder builder = new MixtureBuilder(
            new Mixture(null, null, null, 0, emptyStrengths, null, 0, 0, null, 0, null, multipliers),
            0);
        assertThrows(RuntimeException.class, () -> builder.build());
    }

    private AntibioticTreatment getTreatment(double weight) {
        return new MixtureBuilder(mixture, weight).build();
    }

    private double getTreatmentStrength(double weight) {
        return getTreatment(weight).getDosageFormula().getStrength().getValue();
    }

}
