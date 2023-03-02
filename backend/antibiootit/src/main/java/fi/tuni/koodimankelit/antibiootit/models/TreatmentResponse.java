package fi.tuni.koodimankelit.antibiootit.models;

import java.util.ArrayList;
import java.util.List;

public class TreatmentResponse {
    private final ArrayList<AntibioticTreatment> antibiotics;


    public TreatmentResponse() {
        this.antibiotics = new ArrayList<>();
    }

    public void addAntibiotic(AntibioticTreatment choise) {
        this.antibiotics.add(choise);
    }


    public List<AntibioticTreatment> getAntibiotics() {
        return this.antibiotics;
    }



}
