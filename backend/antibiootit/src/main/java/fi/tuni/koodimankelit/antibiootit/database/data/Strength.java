package fi.tuni.koodimankelit.antibiootit.database.data;

import org.springframework.data.mongodb.core.mapping.Field;

public class Strength {
    @Field("value")
    private final int value;
    @Field("minWeight")
    private final int minWeight;
    @Field("unit")
    private final String unit;

    public Strength(int value, int minWeight, String unit) {
        this.value = value;
        this.minWeight = minWeight;
        this.unit = unit;
    }

    public int getValue() {
        return this.value;
    }

    public int getMinWeight() {
        return this.minWeight;
    }

    public String getUnit() {
        return this.unit;
    }
}
