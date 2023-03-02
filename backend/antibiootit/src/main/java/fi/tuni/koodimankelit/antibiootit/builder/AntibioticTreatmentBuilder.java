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
    private final Integer strength;

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
            new Measurement(antibiotic.getUnit(), this.strength),
            new Measurement(antibiotic.getUnit(), antibiotic.getDosagePerDay())
        );
        DosageResult dosageResult = new DosageResult(
            new Measurement("Tähän pitäisi saada yksiköt tietokannasta", calculateDosageResult(antibiotic, weight))
        );

        return new AntibioticTreatment(
            antibiotic.getFormat(),
            antibiotic.getInfo(),
            antibiotic.getName(),
            instructions,
            dosageFormula,
            dosageResult
        );
        
    }

    
    /** 
     * Return antibiotic's strength. Unit is defined in {@link Strength#getValue()} (Change to getUnit when it exists)
     * @param antibiotic Antibiotic instance
     * @param weight weight in kilograms
     * @return Integer antibiotic's strength
     */
    private static Integer getStrength(Antibiotic antibiotic, double weight) {

        // Sort by minimum weight, highest first
        antibiotic.getStrengths().sort((a, b) -> Integer.valueOf(b.getWeight()).compareTo(Integer.valueOf(a.getWeight())));
        for(Strength strength : antibiotic.getStrengths()) {
            if(weight <= strength.getWeight()) {
                return strength.getValue();
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
        Integer strength = getStrength(antibiotic, weight);
        if(strength == null) {
            throw new RuntimeException("Can not calculate dosage because strength is null");
        }
        double totalDosageInDay = dosagePerDay / strength;
        return totalDosageInDay / antibiotic.getDosesPerDay();
    }

}
