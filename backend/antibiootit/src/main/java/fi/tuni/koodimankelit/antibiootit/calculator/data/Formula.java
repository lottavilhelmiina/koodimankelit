package fi.tuni.koodimankelit.antibiootit.calculator.data;

public class Formula {

    private final Measurement strength;
    private final Measurement dosage;


    public Formula(Measurement strength, Measurement dosage) {
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
