package fi.tuni.koodimankelit.antibiootit.builder;

import fi.tuni.koodimankelit.antibiootit.database.data.Antibiotic;
import fi.tuni.koodimankelit.antibiootit.database.data.Strength;
import fi.tuni.koodimankelit.antibiootit.models.AntibioticTreatment;
import fi.tuni.koodimankelit.antibiootit.models.DosageFormula;
import fi.tuni.koodimankelit.antibiootit.models.DosageResult;
import fi.tuni.koodimankelit.antibiootit.models.Instructions;
import fi.tuni.koodimankelit.antibiootit.models.Measurement;

/**
 * Builder for antibiotic treatment. Calculates dosage based on weight
 */
public class AntibioticTreatmentBuilder {

    private final Antibiotic antibiotic;
    private final double weight;
    private final Strength strength;

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
            return null;
        }

        Instructions instructions = new Instructions(antibiotic.getDays(), antibiotic.getDosesPerDay());
        DosageFormula dosageFormula = new DosageFormula(
            new Measurement(strength.getUnit(), strength.getValue()),
            new Measurement(antibiotic.getDosagePerDayUnit(), antibiotic.getDosagePerDay())
        );
        DosageResult dosageResult = new DosageResult(
            new Measurement(antibiotic.getResultUnit(), calculateDosageResult(antibiotic, weight))
        );

        return new AntibioticTreatment(
            antibiotic.getFormat(),
            antibiotic.getInfo(),
            antibiotic.getAntibiotic(),
            instructions,
            dosageFormula,
            dosageResult
        );
        
    }

    
    /** 
     * Return antibiotic's strength. Unit is defined in {@link Strength#getValue()} (Change to getUnit when it exists)
     * @param antibiotic Antibiotic instance
     * @param weight weight in kilograms
     * @return Strength antibiotic's strength
     */
    private static Strength getStrength(Antibiotic antibiotic, double weight) {

        // Sort by minimum weight, highest first
        antibiotic.getStrength().sort((a, b) -> Integer.valueOf(b.getMinWeight()).compareTo(Integer.valueOf(a.getMinWeight())));
        for(Strength strength : antibiotic.getStrength()) {
            if(weight <= strength.getMinWeight()) {
                return strength;
            }
        }
        return null;
    }

    
    /** 
     * Calculates one-time antibiotic dosage based on weight
     * @param antibiotic antibiotic instance
     * @param weight weight in kilograms
     * @return Double one-time dosage. Unit depends on antibiotic
     */
    private static Double calculateDosageResult(Antibiotic antibiotic, double weight) {
        double dosagePerDay = antibiotic.getDosagePerDay() * weight;
        Strength strength = getStrength(antibiotic, weight);
        if(strength == null) {
            throw new RuntimeException("Can not calculate dosage because strength is null");
        }
        double totalDosageInDay = dosagePerDay / strength.getValue();
        return totalDosageInDay / antibiotic.getDosesPerDay();
    }

}
