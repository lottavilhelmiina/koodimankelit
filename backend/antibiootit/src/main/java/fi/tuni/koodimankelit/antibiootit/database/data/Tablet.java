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
     * @param strength list of different strengths of the antibiotic
     * @param weightUnit used weight unit
     * @param instructions instructions on how to use antibiotic
     * @param tabletsPerDose how many tablets needs to be taken per dose
     */
    public Tablet(String antibiotic, String format, List<Strength> strength, String weightUnit, Instructions instructions, int tabletsPerDose) {
        super(antibiotic, format, strength, weightUnit, instructions);
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
