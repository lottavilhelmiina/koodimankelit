package fi.tuni.koodimankelit.antibiootit.database.data;

import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Database representation of antibiotics strength options
 */
public class Strength {
    @Field("value")
    private final int value;
    @Field("minWeight")
    private final int minWeight;
    @Field("unit")
    private final String unit;
    @Field("text")
    private final String text;

    /**
     * Default constructor
     * @param value value of strength
     * @param minWeight minimum weight to use this strength
     * @param unit unit of the strength
     * @param text value of strength and unit combined as string
     */
    public Strength(int value, int minWeight, String unit, String text) {
        this.value = value;
        this.minWeight = minWeight;
        this.unit = unit;
        this.text = text;
    }

    /**
     * Returns value of strength
     * @return int value
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Returns minimum weight to use this strength
     * @return int minWeight
     */
    public int getMinWeight() {
        return this.minWeight;
    }

    /**
     * Returns unit of the strength
     * @return String unit
     */
    public String getUnit() {
        return this.unit;
    }

    /**
     * Returns text about the strength
     * @return String text
     */
    public String getText() {
        return this.text;
    }
}
