package fi.tuni.koodimankelit.antibiootit.models;

import java.util.ArrayList;
import java.util.List;

public class Treatment {
    private final ArrayList<AntibioticTreatment> antibiotics;


    public Treatment() {
        this.antibiotics = new ArrayList<>();
    }

    public void addAntibiotic(AntibioticTreatment choise) {
        this.antibiotics.add(choise);
    }


    public List<AntibioticTreatment> getAntibiotics() {
        return this.antibiotics;
    }



}
