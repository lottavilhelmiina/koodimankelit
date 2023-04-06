package fi.tuni.koodimankelit.antibiootit.models;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Represents the antibiotic dosage result
 */
@Schema(description = "Antibiotic dosage result")
public class DosageResult {
    private final Measurement dose;


    /**
     * Default constructor
     * @param dose one-time dosage
     */
    public DosageResult(Measurement dose) {
        this.dose = dose;
    }


    /**
     * Returns the rounded one-time dose
     * @return dose
     */
    public Measurement getDose() {
        return this.dose;
    }

}
