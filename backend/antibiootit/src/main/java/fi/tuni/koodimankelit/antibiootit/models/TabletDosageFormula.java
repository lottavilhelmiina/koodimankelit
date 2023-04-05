package fi.tuni.koodimankelit.antibiootit.models;

public class TabletDosageFormula {
    private final StrengthMeasurement strength;

    public TabletDosageFormula(StrengthMeasurement strength) {
        this.strength = strength;
    }


    public StrengthMeasurement getStrength() {
        return this.strength;
    }
}
