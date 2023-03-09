package fi.tuni.koodimankelit.antibiootit.builder;

import fi.tuni.koodimankelit.antibiootit.database.data.Antibiotic;
import fi.tuni.koodimankelit.antibiootit.database.data.Strength;
import fi.tuni.koodimankelit.antibiootit.models.AntibioticTreatment;
/**
 * Builder for antibiotic treatment. Calculates dosage based on weight
 */
public abstract class AntibioticTreatmentBuilder {

    protected final double weight;
    protected final Strength strength;

    /**
     * Default constructor
     * @param antibiotic Database entity instance
     * @param weight Weight in kilograms
     */
    public AntibioticTreatmentBuilder(Antibiotic antibiotic, double weight) {

        this.weight = weight;
        this.strength = getStrength(antibiotic, weight);
    }

    
    /** 
     * Build antibiotic treatment object
     * @return AntibioticTreatment generated result
     */
    public abstract AntibioticTreatment build();

    
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
