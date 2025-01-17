package fi.tuni.koodimankelit.antibiootit.builder;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fi.tuni.koodimankelit.antibiootit.database.data.Strength;
import fi.tuni.koodimankelit.antibiootit.database.data.Tablet;
import fi.tuni.koodimankelit.antibiootit.models.AntibioticTreatment;
import fi.tuni.koodimankelit.antibiootit.models.Formula;
import fi.tuni.koodimankelit.antibiootit.models.StrengthMeasurement;

/**
 * Test class for TabletBuilder
 */
public class TabletBuilderTest extends AntibioticTreatmentBuilderTest {

    private final int tabletsPerDose = 1;

    private final Tablet tablet = new Tablet(antibiotic, format, strengths, instructions, tabletsPerDose);

    @Override
    @BeforeEach
    public void populateStrengths() {
        strengths.clear();
        strengths.add(new Strength(1000000, 30, null, null));
        strengths.add(new Strength(1500000, 40, strengthUnit, strengthText));
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
    @Test
    public void testCorrectFormula() {

        // 40 kg -> Strength {1500000, 40}
        AntibioticTreatment treatment = getTreatment(40);
        Formula dosageFormula = treatment.getFormula();
        StrengthMeasurement strengthMeasurement = dosageFormula.getStrength();

        assertEquals(strengthUnit, strengthMeasurement.getUnit());
        assertEquals(strengthText, strengthMeasurement.getText());
        assertEquals(1500000, strengthMeasurement.getValue());

    }

    @Test
    public void testResult() {

        // Result should be constant
        assertEquals((double) tabletsPerDose, getTreatment(30).getDosageResult().getDose().getValue());
        assertEquals((double) tabletsPerDose, getTreatment(50).getDosageResult().getDose().getValue());
        assertEquals((double) tabletsPerDose, getTreatment(80).getDosageResult().getDose().getValue());

    }

    @Test
    public void testTooSmallWeightResult() {
        // Smallest strength has min 30 kg
        assertThrows(RuntimeException.class, () -> getTreatment(0));
        assertThrows(RuntimeException.class, () -> getTreatment(29.99));
        assertDoesNotThrow(() -> getTreatment(30));
    }

    @Test
    public void testCorrectResultUnit() {
        assertEquals("kpl", getTreatment(getValidWeight()).getDosageResult().getDose().getUnit());
    }

    @Override
    protected AntibioticTreatment getTreatment(double weight) {
        return new TabletBuilder(tablet, weight).build();
    }

    @Override
    protected AntibioticTreatmentBuilder getBuilderWithStrengths(List<Strength> strengths, double weight) {
        return new TabletBuilder(
            new Tablet(antibiotic, format, strengths, instructions, tabletsPerDose),
            weight);
    }

    @Override
    protected int getValidWeight() {
        return 60;
    }

}
