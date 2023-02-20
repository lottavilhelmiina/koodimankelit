package fi.tuni.koodimankelit.antibiootit.database.data;

import java.util.ArrayList;

public class Treatment {
    public String choice;
    public ArrayList<Antibiotic> choicesOfAntibiotics = new ArrayList<>();

    public Treatment() {}

    public Treatment(String choice, ArrayList<Antibiotic> antibiotics) {
        this.choice = choice;
        this.choicesOfAntibiotics = antibiotics;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public String getChoice() {
        return this.choice;
    }

    public void setChoicesOfAntibiotics(ArrayList<Antibiotic> antibiotics) {
        this.choicesOfAntibiotics = antibiotics;
    }

    public ArrayList<Antibiotic> getChoicesOfAntibiotics() {
        return this.choicesOfAntibiotics;
    }
}
