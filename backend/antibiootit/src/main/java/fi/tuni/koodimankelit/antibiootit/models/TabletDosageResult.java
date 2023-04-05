package fi.tuni.koodimankelit.antibiootit.models;

public class TabletDosageResult {
    private final Measurement dose;

    public TabletDosageResult(Measurement dose) {
        this.dose = dose;
    }

    public Measurement getDose() {
        return this.dose;
    }
}
