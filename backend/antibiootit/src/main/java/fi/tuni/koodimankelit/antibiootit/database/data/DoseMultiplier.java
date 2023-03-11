package fi.tuni.koodimankelit.antibiootit.database.data;

import org.springframework.data.mongodb.core.mapping.Field;

public class DoseMultiplier {
    @Field("id")
    private final int id;
    private final int multiplier;

    public DoseMultiplier(int id, int multiplier) {
        this.id = id;
        this.multiplier = multiplier;
    }

    public int getId() {
        return this.id;
    }

    public int getMultiplier() {
        return this.multiplier;
    }
}
