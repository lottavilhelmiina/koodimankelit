package fi.tuni.koodimankelit.antibiootit.builder;

import java.math.BigDecimal;
import java.math.RoundingMode;

import fi.tuni.koodimankelit.antibiootit.database.data.Antibiotic;
import fi.tuni.koodimankelit.antibiootit.database.data.Strength;
import fi.tuni.koodimankelit.antibiootit.models.AntibioticTreatment;
import fi.tuni.koodimankelit.antibiootit.models.DosageFormula;
import fi.tuni.koodimankelit.antibiootit.models.DosageResult;
import fi.tuni.koodimankelit.antibiootit.models.Instructions;


/**
 * Builder for antibiotic treatment. Calculates dosage based on weight
 */
public abstract class AntibioticTreatmentBuilder {

    private final Antibiotic antibiotic;
    protected final double weight;
    protected final Strength strength;

    /**
     * Default constructor
     * @param antibiotic Database entity instance
     * @param weight Weight in kilograms
     */
    public AntibioticTreatmentBuilder(Antibiotic antibiotic, double weight) {

        this.antibiotic = antibiotic;
        this.weight = weight;
        this.strength = getStrength(antibiotic, weight);
    }

    
    /** 
     * Build antibiotic treatment object
     * @return AntibioticTreatment generated result
     */
    public AntibioticTreatment build() {

        // Does not exceed minimum weight on any strength
        if(this.strength == null) {
            // TODO change to custom exception
            throw new RuntimeException("Selected antibiotic has no suitable strength");
        }

        Instructions instructions = 
            new Instructions(antibiotic.getInstructions().getDays(), antibiotic.getInstructions().getDosesPerDay(), 
                antibiotic.getInstructions().getRecipeText(), antibiotic.getInstructions().getDoseMultipliers());

        return new AntibioticTreatment(
            antibiotic.getFormat(),
            antibiotic.getAntibiotic(),
            instructions,
            buildFormula(),
            buildResult()
        );
    }

    protected abstract DosageFormula buildFormula();

    protected abstract DosageResult buildResult();

    /** 
     * Calculates one-time antibiotic dosage based on weight. Rounds the result to three decimals
     * @return Double one-time dosage. Unit depends on antibiotic
     */
    protected Double calculateDosageResult() {
        Double dosagePerDay = antibiotic.getDosagePerWeightPerDay() * weight;
        if(dosagePerDay > antibiotic.getMaxDosePerDay()) {
            dosagePerDay = (double) antibiotic.getMaxDosePerDay();
        }
        Double totalDosageInDay = dosagePerDay / strength.getValue();
        Double accurateResult = totalDosageInDay / antibiotic.getDosesPerDay();

        BigDecimal bd = BigDecimal.valueOf(accurateResult);
        bd = bd.setScale(3, RoundingMode.HALF_UP);
        double roundedResult = bd.doubleValue();

        return roundedResult;
    }
    
    /** 
     * Return antibiotic's strength
     * @param antibiotic Antibiotic instance
     * @param weight weight in kilograms
     * @return Strength antibiotic's strength
     */
    private static Strength getStrength(Antibiotic antibiotic, double weight) {

        // Sort by minimum weight, highest first
        antibiotic.getStrength().sort((a, b) -> Integer.valueOf(b.getMinWeight()).compareTo(Integer.valueOf(a.getMinWeight())));
        for(Strength strength : antibiotic.getStrength()) {
            if(weight >= strength.getMinWeight()) {
                return strength;
            }
        }
        return null;
    }

}
