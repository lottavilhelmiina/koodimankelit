package fi.tuni.koodimankelit.antibiootit.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import fi.tuni.koodimankelit.antibiootit.database.data.Strength;
import fi.tuni.koodimankelit.antibiootit.database.data.Tablet;
import fi.tuni.koodimankelit.antibiootit.models.AntibioticTreatment;

/**
 * Test class for TabletBuilder
 */
public class TabletBuilderTest extends AntibioticTreatmentBuilderTest {

    private String dosagePerWeightPerDayUnit = "dosagePerWeightPerDayUnit";
    private int dosagePerWeightPerDay = 40;
    private int maxDosePerDay = 3000;
    private int tabletsPerDose = 1;

    private Tablet tablet = new Tablet(
        antibiotic, format, info, dosagePerWeightPerDayUnit, dosagePerWeightPerDay, maxDosePerDay,
        strengths, weightUnit, days, dosesPerDay, tabletsPerDose, doseMultipliers
    );

    @Override
    public void testCorrectFormula() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'testCorrectFormula'");
    }

    @Override
    protected AntibioticTreatment getTreatment(double weight) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTreatment'");
    }

    @Override
    protected AntibioticTreatmentBuilder getBuilderWithStrengths(List<Strength> strengths, double weight) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getBuilderWithStrengths'");
    }

    @Override
    public void populateStrengths() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'populateStrengths'");
    }

    @Override
    public void populateMultipliers() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'populateMultipliers'");
    }

}
