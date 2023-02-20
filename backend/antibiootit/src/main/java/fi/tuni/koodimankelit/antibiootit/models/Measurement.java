package fi.tuni.koodimankelit.antibiootit.models;


public class Measurement {

    private final String unit;
    private final double value;


    public Measurement(String unit, double value) {
        this.unit = unit;
        this.value = value;
    }


    public String getUnit() {
        return this.unit;
    }


    public double getValue() {
        return this.value;
    }

}
