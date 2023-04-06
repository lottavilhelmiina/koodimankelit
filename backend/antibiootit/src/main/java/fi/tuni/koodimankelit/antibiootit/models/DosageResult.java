package fi.tuni.koodimankelit.antibiootit.models;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Represents the calculated antibiotic dosage result
 */
@Schema(description = "Calculated antibiotic dosage result")
public class DosageResult {
    private final Measurement dose;
    private final Measurement accurateDose;


    /**
     * Default constructor
     * @param dose one-time dosage
     */
    public DosageResult(Measurement dose, Measurement accurateDose) {
        this.dose = dose;
        this.accurateDose = accurateDose;
    }


    /**
     * Returns the rounded one-time dose
     * @return dose
     */
    public Measurement getDose() {
        return this.dose;
    }

    /**
     * Returns the more accurate one-time dose (rounded to three decimals)
     * @return unrounded dose
     */
    public Measurement getAccurateDose() {
        return this.accurateDose;
    }

}
