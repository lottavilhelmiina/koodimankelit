package fi.tuni.koodimankelit.antibiootit.models;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Represents how antibiotic dosage result was calculated
 */
@Schema(description = "Antibiotic dosage result calculation formula")
public class DosageFormula extends Formula {


    private final Measurement dosage;

    /**
     * Default constructor
     * @param strength Strength of the antibiotic
     * @param dosage How much needs to be given according to weight
     */
    public DosageFormula(StrengthMeasurement strength, Measurement dosage) {
        super(strength);
        this.dosage = dosage;
    }


    /** 
     * Returns how much antibiotic needs to be given according to weight
     * @return Measurement dosage
     */
    public Measurement getDosage() {
        return this.dosage;
    }


}
