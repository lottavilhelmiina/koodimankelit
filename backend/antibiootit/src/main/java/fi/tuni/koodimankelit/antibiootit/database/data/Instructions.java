package fi.tuni.koodimankelit.antibiootit.database.data;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Database representation of instructions on how to use antibiotic
 */
@Schema(description = "Instructions for antibiotic use")
public class Instructions {
    private final int days;
    private final int dosesPerDay;
    private final String recipeText;
    private final List<DoseMultiplier> doseMultipliers;

    /**
     * Default constructor
     * @param days how many days antibiotic needs to be taken
     * @param dosesPerDay how many times antibiotic needs to be taken in a day
     * @param recipeText text of how many times antibiotic needs to be taken in a day
     * @param doseMultipliers info if dose needs to be multiplied on some days
     */
    public Instructions(int days, int dosesPerDay, String recipeText, List<DoseMultiplier> doseMultipliers) {
        this.days = days;
        this.dosesPerDay = dosesPerDay;
        this.recipeText = recipeText;
        this.doseMultipliers = doseMultipliers;
    }

    /**
     * Returns amount of days that antibiotic needs to be taken
     * @return int days
     */
    public int getDays() {
        return this.days;
    }

    /**
     * Returns how many doses needs to be taken per day
     * @return int dosesPerDay
     */
    public int getDosesPerDay() {
        return this.dosesPerDay;
    }

    /**
     * Returns list of dose multipliers
     * @return List<DoseMultiplier> doseMultipliers
     */
    public List<DoseMultiplier> getDoseMultipliers() {
        return this.doseMultipliers;
    }

    /**
     * Returns text of how many times antibiotic needs to be taken in a day
     * @return String recipeText
     */
    public String getRecipeText() {
        return this.recipeText;
    }
}
