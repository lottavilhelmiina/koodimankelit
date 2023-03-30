package fi.tuni.koodimankelit.antibiootit.builder;

import fi.tuni.koodimankelit.antibiootit.database.data.Mixture;
import fi.tuni.koodimankelit.antibiootit.models.DosageFormula;
import fi.tuni.koodimankelit.antibiootit.models.DosageResult;
import fi.tuni.koodimankelit.antibiootit.models.Measurement;
import fi.tuni.koodimankelit.antibiootit.models.StrengthMeasurement;

public class MixtureBuilder extends AntibioticTreatmentBuilder {

    private final Mixture antibiotic;

    public MixtureBuilder(Mixture antibiotic, double weight) {
        super(antibiotic, weight);
        
        this.antibiotic = antibiotic;
    }

    @Override
    protected DosageFormula buildFormula() {
        return new DosageFormula(
            new StrengthMeasurement(strength.getUnit(), strength.getValue(), strength.getText()),
            new Measurement(antibiotic.getDosagePerWeightPerDayUnit(), antibiotic.getDosagePerWeightPerDay())
        );
    }

    @Override
    protected DosageResult buildResult() {
        Double dosageResult = calculateDosageResult();
        Double roundedResult = roundToNearestHalf(dosageResult);
        String resultUnit = antibiotic.getResultUnit();

        return new DosageResult(
            new Measurement(resultUnit, roundedResult),
            new Measurement(resultUnit, dosageResult)
        );
    }

    /**
     * Rounds a value to nearest half
     * @param value Double to round
     * @return Double Rounded value
     */
    private Double roundToNearestHalf(Double value) {
        Double roundedValue = ((int)(value * 2 + 0.5)) / 2.0;
        return roundedValue;
    }
}
