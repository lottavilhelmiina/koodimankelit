package fi.tuni.koodimankelit.antibiootit.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * API representation of diagnosis response with treatments
 */
@Schema(description = "API diagnosis response with treatments")
public class DiagnosisResponse {

    @JsonProperty("_id")
    private final String id;
    private final String etiology;
    private final String info;
    private final ArrayList<AntibioticTreatment> treatments;

    /**
     * Default constructor
     * @param id ICD-10 identifier
     * @param etiology common cause for diagnosis
     * @param info additional info about diagnosis
     */
    public DiagnosisResponse(String id, String etiology, String info) {
        this.id = id;
        this.etiology = etiology;
        this.info = info;
        this.treatments = new ArrayList<>();
    }

    
    /** 
     * Adds a new treatment
     * @param treatment Antibiotic treatment
     */
    public void addTreatment(AntibioticTreatment treatment) {
        this.treatments.add(treatment);
    }


    
    /** 
     * Returns ICD-10 identifier
     * @return String id
     */
    public String getId() {
        return this.id;
    }


    
    /** 
     * Returns common cause
     * @return String etiology
     */
    public String getEtiology() {
        return this.etiology;
    }

    /**
     * Returns additional info about diagnosis
     * @return String info
     */
    public String getInfo() {
        return this.info;
    }
    
    /** 
     * Returns all treatments
     * @return List<AntibioticTreatment> treatments
     */
    public List<AntibioticTreatment> getTreatments() {
        return this.treatments;
    }



}
