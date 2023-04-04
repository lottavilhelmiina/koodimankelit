package fi.tuni.koodimankelit.antibiootit.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fi.tuni.koodimankelit.antibiootit.database.data.Strength;
import fi.tuni.koodimankelit.antibiootit.database.data.Tablet;
import fi.tuni.koodimankelit.antibiootit.models.AntibioticTreatment;
import fi.tuni.koodimankelit.antibiootit.models.DosageFormula;
import fi.tuni.koodimankelit.antibiootit.models.Measurement;
import fi.tuni.koodimankelit.antibiootit.models.StrengthMeasurement;

/**
 * Test class for TabletBuilder
 */
public class TabletBuilderTest extends AntibioticTreatmentBuilderTest {

    private final String dosagePerWeightPerDayUnit = "dosagePerWeightPerDayUnit";
    private final int dosagePerWeightPerDay = 40;
    private final int maxDosePerDay = 3000;
    private final int tabletsPerDose = 1;

    private final Tablet tablet = new Tablet(
        antibiotic, format, info, dosagePerWeightPerDayUnit, dosagePerWeightPerDay, maxDosePerDay,
        strengths, weightUnit, days, dosesPerDay, recipeText, tabletsPerDose, doseMultipliers
    );

    @Test
    @Override
    public void testCorrectFormula() {

        // 40 kg -> Strength {1500000, 40}
        AntibioticTreatment treatment = getTreatment(40);
        DosageFormula dosageFormula = treatment.getDosageFormula();
        Measurement dosage = dosageFormula.getDosage();
        StrengthMeasurement strengthMeasurement = dosageFormula.getStrength();

        assertEquals(dosagePerWeightPerDayUnit, dosage.getUnit());
        assertEquals(40, dosage.getValue());

        assertEquals(weightUnit, strengthMeasurement.getUnit());
        assertEquals(strengthText, strengthMeasurement.getText());
        assertEquals(1500000, strengthMeasurement.getValue());

    }

    @Override
    protected AntibioticTreatment getTreatment(double weight) {
        return new TabletBuilder(tablet, weight).build();
    }

    @Override
    protected AntibioticTreatmentBuilder getBuilderWithStrengths(List<Strength> strengths, double weight) {
        return new TabletBuilder(
            new Tablet(
                antibiotic, format, info, dosagePerWeightPerDayUnit, dosagePerWeightPerDay, maxDosePerDay,
                strengths, weightUnit, days, dosesPerDay, recipeText, tabletsPerDose, doseMultipliers
            ),
            weight);
    }

    @Override
    @BeforeEach
    public void populateStrengths() {
        strengths.clear();
        strengths.add(new Strength(1000000, 30, null, null));
        strengths.add(new Strength(1500000, 40, weightUnit, strengthText));
        strengths.add(new Strength(2000000, 60, null, null));
    }

    @Override
    @Test
    public void testCorrectStrengthIsSelected() {
        assertThrows(RuntimeException.class, () -> getTreatmentStrength(0));
        assertThrows(RuntimeException.class, () -> getTreatmentStrength(29.99));
        assertEquals(1000000, getTreatmentStrength(30));
        assertEquals(1000000, getTreatmentStrength(39.99));
        assertEquals(1500000, getTreatmentStrength(40));
        assertEquals(1500000, getTreatmentStrength(59.99));
        assertEquals(2000000, getTreatmentStrength(60));
        assertEquals(2000000, getTreatmentStrength(1000));
    }

    @Override
    protected int getValidWeight() {
        return 60;
    }

}
