package fi.tuni.koodimankelit.antibiootit.models;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Represents what strength was used
 */
@Schema(description = "Antibiotic strength formula")
public class Formula {
    
    private final StrengthMeasurement strength;

    /**
     * Default constructor
     * @param strength strength of the antibiotic
     */
    public Formula(StrengthMeasurement strength) {
        this.strength = strength;
    }


    /** 
     * Returns the strength of the antibiotic
     * @return Measurement strength
     */
    public StrengthMeasurement getStrength() {
        return this.strength;
    }
}
