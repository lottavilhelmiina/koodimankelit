package fi.tuni.koodimankelit.antibiootit.database.data;

import java.util.List;

public class Treatment {
    private final String choice;
    private final List<Antibiotic> antibiotics;

    public Treatment(String choice, List<Antibiotic> antibiotics) {
        this.choice = choice;
        this.antibiotics = antibiotics;
    }

    public String getChoice() {
        return this.choice;
    }

    public List<Antibiotic> getAntibiotics() {
        return this.antibiotics;
    }
}
