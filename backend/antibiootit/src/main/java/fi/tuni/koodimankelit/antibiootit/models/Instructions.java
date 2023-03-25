package fi.tuni.koodimankelit.antibiootit.models;

import java.util.List;

import fi.tuni.koodimankelit.antibiootit.database.data.DoseMultiplier;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Represents when to take the antibiotic
 */
@Schema(description = "Instructions for antibiotic use")
public class Instructions {
    private final int days;
    private final int dosesPerDay;
    private final List<DoseMultiplier> doseMultipliers;


    /**
     * Default constructor
     * @param days amount how many days the treatment lasts
     * @param dosesPerDay amount how many times the antibiotic needs to be taken in a day
     */
    public Instructions(int days, int dosesPerDay, List<DoseMultiplier> doseMultipliers) {
        this.days = days;
        this.dosesPerDay = dosesPerDay;
        this.doseMultipliers = doseMultipliers;
    }


    
    /** 
     * Returns how many days the treatment lasts
     * @return int days
     */
    public int getDays() {
        return this.days;
    }


    
    /** 
     * Returns how many times a day the antibiotic needs to be taken
     * @return int times in a day
     */
    public int getDosesPerDay() {
        return this.dosesPerDay;
    }


    /**
     * Returns list of dose multiplications
     * @return List<DoseMultiplier> List of dose multiplications
     */
    public List<DoseMultiplier> getDoseMultipliers() {
        return this.doseMultipliers;
    }
}
