package fi.tuni.koodimankelit.antibiootit.builder;

import java.math.BigDecimal;
import java.math.RoundingMode;

import fi.tuni.koodimankelit.antibiootit.database.data.Mixture;
import fi.tuni.koodimankelit.antibiootit.database.data.Strength;
import fi.tuni.koodimankelit.antibiootit.models.MixtureDosageFormula;
import fi.tuni.koodimankelit.antibiootit.models.MixtureDosageResult;
import fi.tuni.koodimankelit.antibiootit.models.MixtureTreatment;
import fi.tuni.koodimankelit.antibiootit.models.Measurement;
import fi.tuni.koodimankelit.antibiootit.models.StrengthMeasurement;

public class MixtureBuilder {

    private final Mixture antibiotic;
    private final double weight;
    private final AntibioticTreatmentBuilder antibioticBuilder;

    public MixtureBuilder(Mixture antibiotic, double weight) {
        this.antibiotic = antibiotic;
        this.weight = weight;
        this.antibioticBuilder = new AntibioticTreatmentBuilder(antibiotic, weight);
    }
  
    /** 
     * Build mixture treatment object
     * @return MixtureTreatment generated result
     */
    public MixtureTreatment buildMixture() {
        // Does not exceed minimum weight on any strength
        if(antibioticBuilder.getStrength() == null) {
             // TODO change to custom exception
            throw new RuntimeException("Selected antibiotic has no suitable strength");
        }

        return new MixtureTreatment(
            antibiotic.getFormat(),
            antibiotic.getAntibiotic(),
            antibioticBuilder.buildInstructions(),
            buildMixtureFormula(),
            buildMixtureResult()
        );
    }

    protected MixtureDosageFormula buildMixtureFormula() {
        Strength strength = antibioticBuilder.getStrength();
        return new MixtureDosageFormula(
            new StrengthMeasurement(strength.getUnit(), strength.getValue(), strength.getText()),
            new Measurement(antibiotic.getDosage().getDosagePerWeightPerDayUnit(), antibiotic.getDosage().getDosagePerWeightPerDay())
        );
    }

    /** 
     * Calculates one-time mixture dosage based on weight. Rounds the result to three decimals
     * @return Double one-time dosage. Unit depends on antibiotic
     */
    protected Double calculateDosageResult() {
        Double dosagePerDay = antibiotic.getDosage().getDosagePerWeightPerDay() * weight;
        if(dosagePerDay > antibiotic.getDosage().getMaxDosePerDay()) {
            dosagePerDay = (double) antibiotic.getDosage().getMaxDosePerDay();
        }
        Double totalDosageInDay = dosagePerDay / antibioticBuilder.getStrength().getValue();
        Double accurateResult = totalDosageInDay / antibiotic.getInstructions().getDosesPerDay();

        BigDecimal bd = BigDecimal.valueOf(accurateResult);
        bd = bd.setScale(3, RoundingMode.HALF_UP);
        double roundedResult = bd.doubleValue();

        return roundedResult;
    }

    protected MixtureDosageResult buildMixtureResult() {
        Double dosageResult = calculateDosageResult();
        Double roundedResult = roundToNearestHalf(dosageResult);
        String resultUnit = antibiotic.getResultUnit();

        return new MixtureDosageResult(
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
