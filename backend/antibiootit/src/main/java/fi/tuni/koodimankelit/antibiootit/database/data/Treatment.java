package fi.tuni.koodimankelit.antibiootit.database.data;

import java.util.List;

/**
 * Database representation of diagnosis treatment option
 */
public class Treatment {
    private final int choice;
    private final List<Antibiotic> antibiotics;

    /**
     * Default constructor
     * @param choice treatment option identifier
     * @param antibiotics list of antibiotics for treatment
     */
    public Treatment(int choice, List<Antibiotic> antibiotics) {
        this.choice = choice;
        this.antibiotics = antibiotics;
    }

    /**
     * Returns treatments option identifier
     * @return int choice
     */
    public int getChoice() {
        return this.choice;
    }

    /**
     * Returns list of antibiotics for treatment
     * @return List<Antibiotic> list of antibiotics
     */
    public List<Antibiotic> getAntibiotics() {
        return this.antibiotics;
    }
}
