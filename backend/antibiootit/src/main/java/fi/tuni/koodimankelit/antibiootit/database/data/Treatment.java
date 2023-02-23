package fi.tuni.koodimankelit.antibiootit.database.data;

import java.util.ArrayList;

public class Treatment {
    private final String choice;
    private final ArrayList<Antibiotic> choicesOfAntibiotics;

    public Treatment(String choice, ArrayList<Antibiotic> antibiotics) {
        this.choice = choice;
        this.choicesOfAntibiotics = antibiotics;
    }

    public String getChoice() {
        return this.choice;
    }

    public ArrayList<Antibiotic> getChoicesOfAntibiotics() {
        return this.choicesOfAntibiotics;
    }
}
