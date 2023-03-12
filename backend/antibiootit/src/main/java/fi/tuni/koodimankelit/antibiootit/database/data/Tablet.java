package fi.tuni.koodimankelit.antibiootit.database.data;

import java.util.List;

/**
 * Database representation of antibiotic in a form of tablet
 */
public class Tablet extends Antibiotic {
    private final int tabletsPerDose;
    private final int dosagePerDay;
    private final String dosagePerDayUnit;

    /**
     * Default constructor
     * @param antibiotic antibiotic's name
     * @param format antibiotic's format
     * @param info extra info about antibiotic
     * @param dosagePerDayUnit unit for dosage per day value
     * @param dosagePerDay dosage per day value
     * @param maxDosePerDay max dose per day
     * @param strength list of different strengths of the antibiotic
     * @param weightUnit used weight unit
     * @param days how many days antibiotic needs to be taken
     * @param dosesPerDay how many times a day antibiotic needs to be taken
     * @param tabletsPerDose how many tablets needs to be taken per dose
     * @param doseMultipliers info if dose needs to be multiplied on some days
     */
    public Tablet(String antibiotic, String format, String info, String dosagePerDayUnit,
        int dosagePerDay, int maxDosePerDay, List<Strength> strength, String weightUnit,
        int days, int dosesPerDay, int tabletsPerDose, List<DoseMultiplier> doseMultipliers) {
        super(antibiotic, format, info, maxDosePerDay, strength, weightUnit, days, dosesPerDay, doseMultipliers);
        this.dosagePerDayUnit = dosagePerDayUnit;
        this.dosagePerDay = dosagePerDay;
        this.tabletsPerDose = tabletsPerDose;
    }

    /**
     * Returns how many tablets needs to be taken per dose
     * @return int tabletsPerDose
     */
    public int getTabletsPerDose() {
        return this.tabletsPerDose;
    }


    /**
     * Returns dosage per day value
     * @return int dosagePerDay
     */
    public int getDosagePerDay() {
        return this.dosagePerDay;
    }


    /**
     * Returns unit for dosage per day value
     * @return String dosagePerDayUnit
     */
    public String getDosagePerDayUnit() {
        return this.dosagePerDayUnit;
    }

}
