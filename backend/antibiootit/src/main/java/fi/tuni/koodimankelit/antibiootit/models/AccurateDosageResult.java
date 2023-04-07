package fi.tuni.koodimankelit.antibiootit.models;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Represents antibiotic dosage result with non-rounded result
 */
@Schema(description = "Antibiotic dosage result with non-rounded result")
public class AccurateDosageResult extends DosageResult {
    private final Measurement accurateDose;

    /**
     * Default constructor
     * @param dose one-time dosage
     */
    public AccurateDosageResult(Measurement dose, Measurement accurateDose) {
        super(dose);
        this.accurateDose = accurateDose;
    }

    /**
     * Returns the more accurate one-time dose (rounded to three decimals)
     * @return unrounded dose
     */
    public Measurement getAccurateDose() {
        return this.accurateDose;
    }
}
