package fi.tuni.koodimankelit.antibiootit.calculator.data;

public class DosageFormula {

    private final Measurement strength;
    private final Measurement dosage;


    public DosageFormula(Measurement strength, Measurement dosage) {
        this.strength = strength;
        this.dosage = dosage;
    }


    public Measurement getStrength() {
        return this.strength;
    }


    public Measurement getDosage() {
        return this.dosage;
    }


}
