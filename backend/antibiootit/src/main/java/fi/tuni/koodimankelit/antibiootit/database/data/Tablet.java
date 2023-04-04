package fi.tuni.koodimankelit.antibiootit.database.data;

import java.util.List;

/**
 * Database representation of antibiotic in a form of tablet
 */
public class Tablet extends Antibiotic {
    private final int tabletsPerDose;

    /**
     * Default constructor
     * @param antibiotic antibiotic's name
     * @param format antibiotic's format
     * @param info extra info about antibiotic
     * @param dosagePerWeightPerDayUnit unit for dosage per day value
     * @param dosagePerWeightPerDay dosage per day value
     * @param maxDosePerDay max dose per day
     * @param strength list of different strengths of the antibiotic
     * @param weightUnit used weight unit
     * @param days how many days antibiotic needs to be taken
     * @param dosesPerDay how many times a day antibiotic needs to be taken
     * @param recipeText text of how many times antibiotic needs to be taken in a day
     * @param tabletsPerDose how many tablets needs to be taken per dose
     * @param doseMultipliers info if dose needs to be multiplied on some days
     */
    public Tablet(String antibiotic, String format, String info, String dosagePerWeightPerDayUnit,
        int dosagePerWeightPerDay, int maxDosePerDay, List<Strength> strength, String weightUnit,
        int days, int dosesPerDay, String recipeText, int tabletsPerDose, List<DoseMultiplier> doseMultipliers) {
        super(antibiotic, format, info, maxDosePerDay, strength, weightUnit, days, dosesPerDay, dosagePerWeightPerDay, dosagePerWeightPerDayUnit, recipeText, doseMultipliers);
        this.tabletsPerDose = tabletsPerDose;
    }

    /**
     * Returns how many tablets needs to be taken per dose
     * @return int tabletsPerDose
     */
    public int getTabletsPerDose() {
        return this.tabletsPerDose;
    }
}
