package fi.tuni.koodimankelit.antibiootit.database.data;

/**
 * Database representation of mixture's dosage information
 */
public class Dosage {
    private final int maxDosePerDay;
    private final int dosagePerWeightPerDay;
    private final String dosagePerWeightPerDayUnit;

    /**
     * Default constructor
     * @param maxDosePerDay max dose per day for a day
     * @param dosagePerWeightPerDay mixture's dosage per day per weight
     * @param dosagePerWeightPerDayUnit unit for mixture's dosage per day per weight
     */
    public Dosage(int maxDosePerDay, int dosagePerWeightPerDay, String dosagePerWeightPerDayUnit) {
        this.maxDosePerDay = maxDosePerDay;
        this.dosagePerWeightPerDay = dosagePerWeightPerDay;
        this.dosagePerWeightPerDayUnit = dosagePerWeightPerDayUnit;
    }

    /**
     * Returns mixture's maximum dose for one day
     * @return int max dose for one day
     */
    public int getMaxDosePerDay() {
        return this.maxDosePerDay;
    }

    /**
     * Returns mixture's dosage per weight for one day
     * @return int dosage per weight for one day
     */
    public int getDosagePerWeightPerDay() {
        return this.dosagePerWeightPerDay;
    }

    /**
     * Returns unit of mixture's dosage per weight per day 
     * @return String unit of dosage
     */
    public String getDosagePerWeightPerDayUnit() {
        return this.dosagePerWeightPerDayUnit;
    }
}
