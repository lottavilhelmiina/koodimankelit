package fi.tuni.koodimankelit.antibiootit.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DiagnoseResponse {

    @JsonProperty("_id")
    private final String id;
    private final String etiology;
    private final ArrayList<AntibioticTreatment> treatments;


    public DiagnoseResponse(String id, String etiology) {
        this.id = id;
        this.etiology = etiology;
        this.treatments = new ArrayList<>();
    }

    public void addTreatment(AntibioticTreatment treatment) {
        this.treatments.add(treatment);
    }


    public String getId() {
        return this.id;
    }


    public String getEtiology() {
        return this.etiology;
    }


    public List<AntibioticTreatment> getTreatments() {
        return this.treatments;
    }



}
