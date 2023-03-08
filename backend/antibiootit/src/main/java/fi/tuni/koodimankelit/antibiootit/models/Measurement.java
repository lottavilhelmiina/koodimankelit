package fi.tuni.koodimankelit.antibiootit.models;


/**
 * Representation of numerical measurement
 */
public class Measurement {

    private final String unit;
    private final double value;


    /**
     * Default constructor
     * @param unit unit of measurement
     * @param value value of measurement
     */
    public Measurement(String unit, double value) {
        this.unit = unit;
        this.value = value;
    }


    
    /** 
     * Returns unit
     * @return String unit
     */
    public String getUnit() {
        return this.unit;
    }


    
    /** 
     * Returns value
     * @return double value
     */
    public double getValue() {
        return this.value;
    }

}
