package fi.tuni.koodimankelit.antibiootit.database.data;

import java.util.List;

public class Treatment {
    private final int choice;
    private final List<Antibiotic> antibiotics;

    public Treatment(int choice, List<Antibiotic> antibiotics) {
        this.choice = choice;
        this.antibiotics = antibiotics;
    }

    public int getChoice() {
        return this.choice;
    }

    public List<Antibiotic> getAntibiotics() {
        return this.antibiotics;
    }
}
