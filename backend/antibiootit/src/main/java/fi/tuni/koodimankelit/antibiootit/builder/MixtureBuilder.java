package fi.tuni.koodimankelit.antibiootit.builder;

import fi.tuni.koodimankelit.antibiootit.database.data.Mixture;
import fi.tuni.koodimankelit.antibiootit.models.DosageFormula;
import fi.tuni.koodimankelit.antibiootit.models.DosageResult;
import fi.tuni.koodimankelit.antibiootit.models.Measurement;

public class MixtureBuilder extends AntibioticTreatmentBuilder {

    private final Mixture antibiotic;

    public MixtureBuilder(Mixture antibiotic, double weight) {
        super(antibiotic, weight);
        
        this.antibiotic = antibiotic;
    }

    @Override
    protected DosageFormula buildFormula() {
        return new DosageFormula(
            new Measurement(strength.getUnit(), strength.getValue()),
            new Measurement(antibiotic.getDosagePerWeightPerDayUnit(), antibiotic.getDosagePerWeightPerDay())
        );
    }

    @Override
    protected DosageResult buildResult() {
        return new DosageResult(
            new Measurement(antibiotic.getResultUnit(), calculateDosageResult())
        );
    }

    /** 
     * Calculates one-time antibiotic dosage based on weight
     * @return Double one-time dosage. Unit depends on antibiotic
     */
    private Double calculateDosageResult() {
        double dosagePerDay = antibiotic.getDosagePerWeightPerDay() * weight;
        double totalDosageInDay = dosagePerDay / strength.getValue();

        return roundToNearestHalf(totalDosageInDay / antibiotic.getDosesPerDay());
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
