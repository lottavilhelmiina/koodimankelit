package fi.tuni.koodimankelit.antibiootit.models;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Represents how antibiotic dosage result was calculated
 */
@Schema(description = "Antibiotic dosage result calculation formula")
public class DosageFormula {

    private final StrengthMeasurement strength;
    private final Measurement dosage;


    /**
     * Default constructor
     * @param strength strength of the antibiotic
     * @param dosage How much needs to be given according to weight
     */
    public DosageFormula(StrengthMeasurement strength, Measurement dosage) {
        this.strength = strength;
        this.dosage = dosage;
    }


    
    /** 
     * Returns the strength of the antibiotic
     * @return Measurement strength
     */
    public StrengthMeasurement getStrength() {
        return this.strength;
    }


    
    /** 
     * Returns how much antibiotic needs to be given according to weight
     * @return Measurement dosage
     */
    public Measurement getDosage() {
        return this.dosage;
    }


}
