package fi.tuni.koodimankelit.antibiootit.builder;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;

import fi.tuni.koodimankelit.antibiootit.database.data.DoseMultiplier;
import fi.tuni.koodimankelit.antibiootit.database.data.Strength;
import fi.tuni.koodimankelit.antibiootit.models.AntibioticTreatment;

public abstract class BuilderTest {
    

    protected List<Strength> strengths = new ArrayList<>();
    protected List<DoseMultiplier> multipliers = new ArrayList<>();
    
    protected abstract AntibioticTreatment getTreatment(double weight);

    protected double getTreatmentStrength(double weight) {
        return getTreatment(weight).getDosageFormula().getStrength().getValue();
    }

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
}
