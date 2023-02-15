package fi.tuni.koodimankelit.antibiootit.calculator.data;

public class Result {
    private final Measurement dose;


    public Result(Measurement dose) {
        this.dose = dose;
    }


    public Measurement getDose() {
        return this.dose;
    }

}
