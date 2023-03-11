package fi.tuni.koodimankelit.antibiootit.database.data;

import java.util.List;

/**
 * Database representation of antibiotic
 */
public class Antibiotic {
    private final String antibiotic;
    private final String format;
    private final String info;
    private final int maxDosePerDay;
    private final List<Strength> strength;
    private final String weightUnit;
    private final int days;
    private final int dosesPerDay;
    private final List<DoseMultiplier> doseMultipliers;

    /**
     * Default constructor
     * @param antibiotic antibiotic's name
     * @param format antibiotic's format
     * @param info extra info about antibiotic
     * @param maxDosePerDay max dose per day
     * @param strength list of different strengths of the antibiotic
     * @param weightUnit used weight unit
     * @param days how many days antibiotic needs to be taken
     * @param dosesPerDay how many times a day antibiotic needs to be taken
     * @param doseMultipliers info if dose needs to be multiplied on some days
     */
    public Antibiotic(String antibiotic, String format, String info, int maxDosePerDay, 
        List<Strength> strength, String weightUnit, int days, int dosesPerDay, List<DoseMultiplier> doseMultipliers) {
            this.antibiotic = antibiotic;
            this.format = format;
            this.info = info;
            this.maxDosePerDay = maxDosePerDay;
            this.strength = strength;
            this.days = days;
            this.dosesPerDay = dosesPerDay;
            this.weightUnit = weightUnit;
            this.doseMultipliers = doseMultipliers;
    }

    /**
     * Returns antibiotic's name
     * @return STring name
     */
    public String getAntibiotic() {
        return this.antibiotic;
    }

    /**
     * Returns antibiotic's format
     * @return String format
     */
    public String getFormat() {
        return this.format;
    }

    /**
     * returns antibiotic's extra info
     * @return String info
     */
    public String getInfo() {
        return this.info;
    }

    /**
     * Returns max dose per day value
     * @return int maxDosePerDay
     */
    public int getMaxDosePerDay() {
        return this.maxDosePerDay;
    }

    /**
     * Returns list of antibiotic's strengths
     * @return List<Strength> strength
     */
    public List<Strength> getStrength() {
        return this.strength;
    }

    /**
     * Returns used weight unit
     * @return String weightUnit
     */
    public String getWeightUnit() {
        return this.weightUnit;
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
}
