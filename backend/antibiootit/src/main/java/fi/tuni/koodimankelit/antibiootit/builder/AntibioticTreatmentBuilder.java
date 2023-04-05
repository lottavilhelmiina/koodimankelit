package fi.tuni.koodimankelit.antibiootit.builder;

import fi.tuni.koodimankelit.antibiootit.database.data.Antibiotic;
import fi.tuni.koodimankelit.antibiootit.database.data.Strength;
import fi.tuni.koodimankelit.antibiootit.models.Instructions;


/**
 * Builder for antibiotic treatment. Calculates dosage based on weight
 */
public class AntibioticTreatmentBuilder {

    private final Antibiotic antibiotic;
    private final Strength strength;

    /**
     * Default constructor
     * @param antibiotic Database entity instance
     * @param weight Weight in kilograms
     */
    public AntibioticTreatmentBuilder(Antibiotic antibiotic, double weight) {

        this.antibiotic = antibiotic;
        this.strength = chooseStrength(antibiotic, weight);
    }

    public Instructions buildInstructions() {
        return new Instructions(antibiotic.getInstructions().getDays(), antibiotic.getInstructions().getDosesPerDay(), 
                antibiotic.getInstructions().getRecipeText(), antibiotic.getInstructions().getDoseMultipliers());
    }

    public Strength getStrength() {
        return this.strength;
    }
  
    /** 
     * Return antibiotic's strength
     * @param antibiotic Antibiotic instance
     * @param weight weight in kilograms
     * @return Strength antibiotic's strength
     */
    private static Strength chooseStrength(Antibiotic antibiotic, double weight) {

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
