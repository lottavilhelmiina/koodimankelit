package fi.tuni.koodimankelit.antibiootit.models;

/**
 * Represents the calculated antibiotic dosage result
 */
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
     * Returns the one-time dose
     * @return dose
     */
    public Measurement getDose() {
        return this.dose;
    }

}
