package fi.tuni.koodimankelit.antibiootit.database.data;

import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Database representation of dose multiplier
 */
public class DoseMultiplier {
    @Field("id")
    private final int id;
    private final int multiplier;

    /**
     * Default constructor
     * @param id id of multiplier, also tells the day when multiplier needs to be used
     * @param multiplier multiplier value
     */
    public DoseMultiplier(int id, int multiplier) {
        this.id = id;
        this.multiplier = multiplier;
    }

    /**
     * Returns multipliers id
     * @return int id
     */
    public int getId() {
        return this.id;
    }

    /**
     * Returns value of multiplier
     * @return int multiplier
     */
    public int getMultiplier() {
        return this.multiplier;
    }
}
