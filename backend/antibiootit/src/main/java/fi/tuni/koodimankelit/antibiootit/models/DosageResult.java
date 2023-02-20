package fi.tuni.koodimankelit.antibiootit.models;

public class DosageResult {
    private final Measurement dose;


    public DosageResult(Measurement dose) {
        this.dose = dose;
    }


    public Measurement getDose() {
        return this.dose;
    }

}
